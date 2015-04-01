package values
import expressions._
/*
 * closure means function
 * lambda = make closure
 */
class Closure(params: List[Identifier], body: Expression, defEnv: Environment) extends Value {
   def apply(args: List[Value],  callEnv:Environment = null): Value = {
     if(callEnv != null){

     callEnv.put(params, args)
     body.execute(callEnv)
     }else {
     var tempEnv: Environment = new Environment(defEnv)
     tempEnv.put(params,args)
     body.execute(tempEnv)
     }
   }
}