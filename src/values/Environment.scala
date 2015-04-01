package values
import expressions._
import collection.mutable.HashMap
case class Environment(nextEnv:Environment = null) extends 
HashMap[Identifier,Value] with Value {
	def put(names:List[Identifier], values:List[Value]){
	  for(i <- 0 until names.size){
	    super.put(names(i), values(i))
	  }
	}
	
	def find(i:Identifier):Value = {
	  try{
		if(contains(i)) {
		  get(i).get
		}
		else{
		  if(nextEnv == null) Notification("Undefined Identifier: " + i)
		  else nextEnv.find(i)
		}
	  }catch{
	    case _ :Throwable => Notification("Undefined Identifier: " + i)
	  }
	}

}