package expressions
import values._
import ui._
case class Conjunction(ops:List[Expression])extends SpecialForm {
	def execute(env:Environment):Value = {
	var result = true
	var i = 0;
	while(result && i < ops.length){
	  if(ops.length <= 0) throw new TypeException("ops should greater than 0")
	  val v = ops(i).execute(env)
	  if(!v.isInstanceOf[Boole]) throw new TypeException("input must be Boole")
	  val b = v.asInstanceOf[Boole]
	  i += 1
	  result = b.value
	}
	new Boole(result)
	}
}