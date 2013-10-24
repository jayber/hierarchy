import javafx.scene.paint.Color
import scala.actors.Actor

object CanvasActor extends Actor {


  def act() {
    while (true) {
      receive {
        case ("changeColor", id: Long, color: Color, companion: CanvasCell, x: Int, y: Int) => {
          companion.changeColor(id, color, x, y)
        }
        case ("infect", id: Long, color: Color, companion: CanvasCell, x: Int, y: Int) => {
          companion.infect(id, color, x, y)
        }
      }
    }
  }

}
