package values
import expressions._
 class Boole(val value:Boolean) extends Literal{
	var num = value
	def &&(other:Boole) = new Boole(num && other.num)
	def ||(other:Boole) = new Boole(num || other.num)
	def ! = new Boole(!num)
	override def toString = num.toString
	def this(str:String){
	  this(str.toBoolean)
	}
	override def execute(env:Environment) = this
	
}

object Boole{
  def test(){
  val n1 = new Boole(true)
  val n2 = new Boole(false)
  println(n1 && n2)
  println(n1 || n2)
  println(n1 !)
  }
}