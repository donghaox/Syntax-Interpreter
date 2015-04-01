package expressions
import values._
import ui._
case class Iteration(condition:Expression, body:Expression) extends SpecialForm{
	def execute(env:Environment) = {
	  if(!condition.execute(env).isInstanceOf[Boole]) throw new SyntaxException()
	  while(condition.execute(env).asInstanceOf[Boole].value){
	    body.execute(env)
	  }
	  Notification("done")
	  
	}
}