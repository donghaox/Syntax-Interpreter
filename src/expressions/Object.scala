package expressions
import values._
import ui._
case class Object(exps: List[Expression], ext: Identifier = null) extends SpecialForm {
  	def execute(env:Environment):Environment = {
  	  var temp = new Environment(env)
  	  if(ext != null && ext.execute(env).isInstanceOf[Environment])
	  temp = new Environment(ext.execute(env).asInstanceOf[Environment])
	 
  	  for(i <- exps){
	      i.execute(temp)
	  }
	  
	  temp
	  
	}
}
