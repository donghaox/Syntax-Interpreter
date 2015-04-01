package ui

import scala.util.parsing.combinator._
import expressions._
import values._
/*
 * NAME:HAOXUAN DONG
 * DATE:NOV 27 2014
 */
class WookieParsers extends RegexParsers {
   def boole:Parser[Boole] = ("true" | "false")^^
   {
     case x => new Boole(x.toBoolean)

   }
   def identifier: Parser[Identifier] = """[a-zA-Z][0-9a-zA-Z]*""".r ^^
   {
     case x => new Identifier(x)

   }
   def number: Parser[Number] = ("""(\+|-)?[0-9]+(\.[0-9]+)?""".r)^^
   {
     case x => new Number(x.toDouble)
   }
   
   def literal:Parser[Expression] = boole | number
   
   def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid expression")

   // def declaration, conditional, disjunction, and other parsers
   def declaration: Parser[Declaration] = ("def" ~> (identifier <~ "=")  ~ expression)^^
   {
     case id ~ exp => Declaration(id,exp);
   }
   

   def conditional:Parser[Conditional] = ("if" ~> "(" ~>( expression <~ ")") ~ expression ~ opt("else" ~> expression))^^
   {
     case exp1 ~ exp2 ~ Some(exp3) =>Conditional(exp1, exp2,exp3)
     case exp1 ~ exp2 ~ None =>Conditional(exp1,exp2)
   }

   def disjunction :Parser[Expression] = (conjunction ~ rep("||" ~> conjunction))^^
   {
     case c ~ Nil => c
     case c ~ c2 =>Disjunction(c::c2)
   }
   
   def conjunction:Parser[Expression] = (equality ~ rep("&&" ~> equality))^^
   {
     case c ~ Nil => c
     case c ~ c2 => Conjunction(c::c2)
   }
   def equality:Parser[Expression] = (inequality ~ rep("==" ~> inequality))^^
   {
     case exp ~ Nil => exp
     case exp ~ expList => FunCall(Identifier("equals"), exp::expList)
   }
   def inequality:Parser[Expression] = (sum ~ rep("<" ~> sum))^^
   {
     case sum ~ Nil => sum
     case sum ~ c2 => FunCall(Identifier("less"), sum::c2)
   }
   def sum : Parser[Expression] = product ~ rep(("+"|"-") ~ product ^^
   {
     case "+" ~ s => s
     case "-" ~ s => negate(s)
   })^^
   {
     case p ~ Nil => p
     case p ~ rest => FunCall(Identifier("add"),p::rest)
   }
   def product : Parser[Expression] = funcall ~ rep(("*"|"/") ~ funcall ^^
   {
     case "*" ~ f => f
     case "/" ~ f => div(f)
   })^^
   {
     case p ~ Nil => p
     case p ~ rest => FunCall(Identifier("mul"), p::rest)
   }
   def term : Parser[Expression]= lambda | block | literal | identifier | "("~> expression <~ ")"
   def operands: Parser[List[Expression]] = ("(" ~> opt(expression ~ rep( "," ~> expression)) <~ ")")^^
   {
     case None  => null 
     case Some(exp ~ Nil) => List(exp)
     case Some(exp ~ expList) => exp::expList
     
   }
    def funcall:Parser[Expression] = (term ~ opt(operands))^^
   {
     case term ~ None => term
     case term ~ Some(operands) => FunCall(term.asInstanceOf[Expression], operands)
   }

   def negate(exp: Expression): Expression = {
		   val sub = Identifier("sub")
		   val zero = new Number(0)
		   FunCall(sub, List(zero, exp))
    }
   def div(exp:Expression):Expression = {
     val div = Identifier("div")
     val zero = new Number(1)
     FunCall(div, List(zero, exp))
     
   }
   //Wookie Parsers
    def lambda : Parser[Expression] = "lambda" ~> parameters ~ expression ^^
   {
      case p ~ exp => Lambda(p,exp)
   }
   
   def parameters: Parser[List[Identifier]] =  "(" ~> opt(identifier ~ rep(","~>identifier))<~")" ^^
  {
  case None => Nil 
  case Some(e ~ Nil) => List(e) 
  case Some(e ~ exps) => e::exps 
  case _ => Nil
  }
   
   def block : Parser[Expression] = "{" ~> expression ~  rep(";" ~> expression) <~ "}" ^^
   {
     case exp ~ Nil => exp
     case exp ~ list => Block(exp :: list)
   }
  
   
   
}