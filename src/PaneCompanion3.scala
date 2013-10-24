import javafx.scene.paint.Color
import scala.actors.Actor

class PaneCompanion3(pane: ColorPane) {

  var ids: Set[Long] = Set()

  var neighbs: Iterable[PaneCompanion3] = null

  def neighbours = neighbs

  def neighbours_=(them: Iterable[PaneCompanion3]) {
    neighbs = them
  }

  def changeColor(id: Long, color: Color) {
    synchronized {
      if (!ids.contains(id)) {
        ids += id
        pane.color = color

        Actor.actor {
          Thread.sleep(20)
          PaneActor !("infect", id, color, PaneCompanion3.this)
        }
      }
    }
  }

  def infect(id: Long, color: Color) {
    neighbours.foreach(companion => {
      PaneActor !("changeColor", id, color, companion)
    })
  }
}
