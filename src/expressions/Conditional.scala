package expressions
import values._
import ui._
case class Conditional(exp1: Expression, exp2:Expression, opt:Expression = null)extends SpecialForm {
	def execute(env:Environment):Value = {
	  val v = exp1.execute(env) 
	  if(v.asInstanceOf[Boole].value ){
	    exp2.execute(env)
	  }else if(!(v.asInstanceOf[Boole].value) && opt != null){
	    opt.execute(env)
	  }else Notification.UNKNOWN
	}
}