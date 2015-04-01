package expressions
import values._
/*
 * Literals are values such as
 * -> 2
 * 2
 * ->true
 * true
 */
case class Literal() extends Expression with Serializable with Value {
	override def execute(env:Environment):Value = this
}