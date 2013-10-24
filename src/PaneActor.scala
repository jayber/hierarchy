import javafx.scene.paint.Color
import scala.actors.Actor

object PaneActor extends Actor {


  def act() {
    while (true) {
      receive {
        case ("changeColor", id: Long, color: Color, companion: PaneCompanion3) => {
          companion.changeColor(id, color)
        }
        case ("infect", id: Long, color: Color, companion: PaneCompanion3) => {
          companion.infect(id, color)
        }
      }
    }
  }

}
