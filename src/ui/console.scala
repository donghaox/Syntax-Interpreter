package ui
import values._
/*
 * HW: Parser Ewok
 * Name: Haoxuan Dong
 * ID: 008837595
 * Date: Nov 20 2014
 */
import expressions._
import scala.util.parsing._
object console {
   val parsers = new SithParsers // for now
   val globalEnv = new Environment()

   def execute(cmmd: String): String = {
      val tree = parsers.parseAll(parsers.expression, cmmd)
      tree match {
         case t: parsers.Failure => throw new SyntaxException(t)
         case _ => "" + tree.get.execute(globalEnv)
      }
   }
   
    def repl {
      var more = true;
      // declare locals
      while(more) {
         try {
            // read/execute/print
      println("-> ")
      val cmmd = readLine()
      if (cmmd == "quit") {
          println("Bye")
          more = false
      } else {
         println(execute(cmmd))
         } // match
      } // else
         
         catch {
            case e: SyntaxException => {
              println("error")
              println(e.msg)
               println(e.result.msg)
               println("line # = " + e.result.next.pos.line)
               println("column # = " + e.result.next.pos.column)
               println("token = " + e.result.next.first)
            }
            // handle other types of exceptions
         } finally {
            Console.flush 
         }
      }
   }
    
   def main(args: Array[String]): Unit = { repl }
}