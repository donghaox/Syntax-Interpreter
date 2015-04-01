package expressions
import ui._
import values._
case class Assignment(x:Identifier, update:Expression) extends SpecialForm {
	def execute(env:Environment) = {
	  val v = x.execute(env)
	  val u = update.execute(env)
	  if(!(v.isInstanceOf[Variable]))
	    throw new SyntaxException()
	  v.asInstanceOf[Variable].content = u 
	  Notification("done")
	}
	
}