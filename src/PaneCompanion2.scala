import javafx.scene.paint.Color
import scala.actors.Actor

class PaneCompanion2(pane: ColorPane) extends PaneCompanion(pane) {


  override def changeColor(id: Long, color: Color) {
    if (!ids.contains(id)) {
      ids += id
      pane.color = color

      Actor.actor {
        Thread.sleep(100)
        infect(id, color)
      }
    }

  }

  /*
    override def infect(id: Long, color: Color) {
      neighbours.foreach(companion => {
        Actor.actor {
        companion.changeColor(id, color)
        }
      })
    }*/
}
