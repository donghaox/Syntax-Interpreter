package values

case class Notification (msg:String) extends Value{
	override def toString = msg
}

object Notification {
  val UNKNOWN = new Notification("Unknown")
  val OK =  new Notification("OK")
  val UPADATED = new Notification("Variable Updated")
  val ERROR = new Notification("Error")
  val BINDING = new Notification("Binding Created");
}