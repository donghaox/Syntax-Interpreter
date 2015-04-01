package expressions
import values._
import ui._
case class FunCall(val operator: Expression, val operands: List[Expression] = Nil,useStatic:Boolean = false) extends Expression {
  def execute(env: Environment): Value = {
    val args: List[Value] = operands.map(_.execute(env))
    //1. check to see if operator is operator.execute(env) is a closure, if so, apply it
    //2. if undefined exception is thrown and operator is an identifier, catch it and try system.execute
   try{
     if(useStatic){
        val f = operator.execute(env)
     if(!f.isInstanceOf[Closure]) throw new TypeException("Should be Closure")
     val foo =  f.asInstanceOf[Closure]
     foo(args,env)
     }else{
     val f = operator.execute(env)
     if(!f.isInstanceOf[Closure]) throw new TypeException("Should be Closure")
     val foo =  f.asInstanceOf[Closure]
     foo(args)
     }
   } catch{
     case e:TypeException => system.execute(operator.asInstanceOf[Identifier],args)
   }
  }    
}
/*case class FunCall(op:Identifier, args:List[Expression] ) extends Expression{
	def execute(env:Environment):Value = {try {
	  system.execute(op,args.map(_.execute(env)));
	}catch{
	  case _ :Throwable => Notification.UNKNOWN
	}
	}
}*/
	/*
	 * def execute(env):Value = {
	 * val args = 
	 * try{
	 * val f = operator.execute(env)
	 * if(!f isInstanceOf[Closure]) trhow new TypeExeption
	 * val foo =  f.asInstanceOf[Closure]
	 * f(args)
	 * catch {
	 * case e : UndefinedException => call system.execute
	 * }
	 */

