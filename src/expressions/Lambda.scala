package expressions
import values._
case class Lambda(ids:List[Identifier],exp:Expression) extends SpecialForm{
	def execute(env:Environment):Closure = {
	  new Closure(ids, exp,env)
	}
}