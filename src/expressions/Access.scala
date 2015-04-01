package expressions
import values._
import ui._
case class Access(obj: Expression, field: Identifier) extends SpecialForm 
{
  def execute(env:Environment):Value = {
    if(!obj.execute(env).isInstanceOf[Environment])
      throw new TypeException("Object expected")
    obj.execute(env).asInstanceOf[Environment].find(field)
  }
}


