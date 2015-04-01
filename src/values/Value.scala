package values
import java.io.Serializable
trait Value extends Serializable {
	def execute(env:Environment):Value  = this
}