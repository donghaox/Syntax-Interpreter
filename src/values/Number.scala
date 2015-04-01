package values
import expressions.Literal

 class Number (val value:Double) extends Literal {
	var num = value
	def +(other:Number) = new Number(num + other.num)
	def -(other:Number) = new Number(num - other.num)
	def *(other:Number) = new Number(num * other.num)
	def /(other:Number) = new Number(num / other.num)
	def <(other:Number) = new Boole(num < other.num)
	def ==(other:Number) = new Boole(num == other.num)
	def >(other:Number) = new Boole(num > other.num)
	override def toString = num.toString
	def Number(v:String){
	  num = v.toDouble
	}
	override def execute(env:Environment):Value = this
}

object Number {
    def test(){
      val n1 = new Number(10)
      val n2 = new Number(3.14)
      println(n1 + n2)
    }
}