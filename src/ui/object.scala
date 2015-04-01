package ui
import expressions._
import scala.util.control.Breaks._
import values._
object system{
  def execute(opcode:Identifier, args:List[Value]):Value={
    opcode.name match{
      case "add" => add(args)
      case "sub" =>sub(args)
      case "mul" => mul(args)
      case "div" => div(args)
      case "equals" => equals(args)
      case "less" => less(args)
      case "not" => not(args)
      case "val" => contentdd(args)
      case "var" => makeVar(args)
     // case "exp" => exp(args)
      //case "compose" => compose(args)
      case _ => throw new UndefinedException(opcode.name)
    }
  }
  
  private def exp(vals:List[Value]):Value = {
    val base = vals.head.asInstanceOf[Number]
    var num = vals(1).asInstanceOf[Number]
    var sum = new Number(1)
    while(num.value > 0){
      sum = sum * base
      num = num - new Number(1)
    }
    sum
  }
  private def makeVar(vals:List[Value]):Value ={
    if(vals.isEmpty) throw new TypeException("need at least one input")
    new  Variable(vals.head)
  }
  private def compose(vals:List[Value]):Value = {
    val f1 = vals(0).asInstanceOf[Closure]
    val f2 = vals(1).asInstanceOf[Closure]
    
    f1(List(f2))
  }
  private def contentdd(vals:List[Value]):Value = {
     if(vals.isEmpty) throw new TypeException("need at least one input")
     if(!vals.head.isInstanceOf[Variable]) throw new TypeException("Variable Expected")
     vals.head.asInstanceOf[Variable].content
  }
  private def add(vals:List[Value]):Value={
    if(vals.isEmpty) throw new TypeException("addtion expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if(ok.length < vals.length) {throw new TypeException("all addition inputs"+
        "must be numebrs")}
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_+_)
    
  }
  
  private def sub(vals:List[Value]):Value={
    if(vals.isEmpty)throw new TypeException("sub expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if(ok.length < vals.length) throw new TypeException("all inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_-_)
  }
  private def mul(vals:List[Value]):Value={
    if(vals.isEmpty)throw new TypeException("mul expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if(ok.length < vals.length) throw new TypeException("all inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_*_)
  }
  private def div(vals:List[Value]):Value={
    if(vals.isEmpty)throw new TypeException("div expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if(ok.length < vals.length) throw new TypeException("all inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_/_)
  }
  
  private def equals(vals:List[Value]):Value={
    if(vals.isEmpty)throw new TypeException("equals expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if(ok.length < vals.length) throw new TypeException("all inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
   //new Boole( args2.tail.map(_ == args2.head).filter(_ != new Boole(true)).length == 0 )
    //args2.map(_ == args2.head).reduce(_ && _)
    var result = true;
    breakable{
      for(i <- 0 until args2.length - 1){
        if(!((args2(i) == args2(i+1)).value)){
          result  = false
          break
        }
      }
    }
    new Boole(result)
  }
  private def less(vals:List[Value]):Value={
    if(vals.isEmpty)throw new TypeException("less expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if(ok.length < vals.length) throw new TypeException("all inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    //new Boole( args2.zip(args2.tail).map(x => x._1 == x._2).filter(_ != new Boole(true) ).length == 0 )
    var result = true
    breakable {
    for(i <- 0 until args2.length - 1){
      if(!(args2(i) < args2(i + 1)).value){
        result = false
        break
      }
    }}
    new Boole(result)
  }
  
  private def not(vals:List[Value]):Value={
    if(vals.isEmpty)throw new TypeException("not expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Boole])
    if(ok.length < vals.length) throw new TypeException("all inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Boole])
    args2(0) !
  }  
}