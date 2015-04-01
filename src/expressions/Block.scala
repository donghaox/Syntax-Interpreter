package expressions
import values._
case class Block(locals:List[Expression]) extends SpecialForm{
	def execute(env:Environment) = {
	  var temp = new Environment(env)
	  for(i <- locals){
	      i.execute(temp)
	  }
	  
	  locals.last.execute(temp)
	  
	}
}