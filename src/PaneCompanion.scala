import javafx.scene.paint.Color
import scala.actors.Actor

class PaneCompanion(pane: ColorPane) extends Actor {

  var ids: Set[Long] = Set()

  var neighbs: Iterable[PaneCompanion] = null

  def neighbours = neighbs

  def neighbours_=(them: Iterable[PaneCompanion]) {
    neighbs = them
  }

  def act() {
    while (true) {
      receive {
        case ("changeColor", id: Long, color: Color) => {
          changeColor(id, color)
        }
        case ("infect", id: Long, color: Color) => {
          infect(id, color)
        }
      }
    }
  }

  def changeColor(id: Long, color: Color) {
    synchronized {
      if (!ids.contains(id)) {
        ids += id
        pane.color = color

        Actor.actor {
          Thread.sleep(10)
          this !("infect", id, color)
        }
      }
    }
  }

  def infect(id: Long, color: Color) {
    neighbours.foreach({
      _ !("changeColor", id, color)
    })
  }
}
