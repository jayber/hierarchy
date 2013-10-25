import javafx.application.Platform
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import scala.actors.Actor
import scala.collection.mutable

class CanvasCell(gc: GraphicsContext, cellSize: Int, size: Int) {

  var ids: Map[Long, mutable.Set[(Int, Int)]] = Map()
  var colour = Color.WHITE

  def setColor(color: Color, x: Int, y: Int) {

    val startX: Int = cellSize * x
    val startY: Int = cellSize * y

    Platform.runLater(new Runnable() {
      def run() {
        gc.setFill(color)
        gc.fillRect(startX, startY, cellSize, cellSize)
      }
    })

  }

  def changeColor(id: Long, color: Color, x: Int, y: Int) {
    var set: mutable.Set[(Int, Int)] =
      if (ids.contains(id)) ids(id)
      else {
        val newSet: mutable.Set[(Int, Int)] = mutable.Set()
        ids += id -> newSet
        newSet
      }

    if (!set.contains((x, y))) {
      set += ((x, y))

      setColor(color, x, y)

      Actor.actor {
        Thread.sleep(0, 10)
        CanvasActor !("infect", id, color, CanvasCell.this, x, y)
      }
    }
  }

  def infect(id: Long, color: Color, x: Int, y: Int) {
    neighbours(x, y, (nx, ny) => {
      CanvasActor !("changeColor", id, color, CanvasCell.this, nx, ny)
    })
  }


  def neighbours(x: Int, y: Int, funct: (Int, Int) => Unit) = {
    def inRange(x: Int, y: Int) = {
      x > -1 && y > -1 && x < size && y < size
    }

    def crossPart(xoffset: Int, yoffset: Int, x: Int, y: Int) = {
      (math.abs(xoffset) < 2 && math.abs(yoffset) < 2) ||
        (xoffset == 2 && (y / 4) % 8 == 0)
    }

    for (xoffset <- -2 to 2) {
      val neighbourX = x + xoffset
      for (yoffset <- -2 to 2) {
        val neighbourY = y + yoffset
        if ((!(xoffset == 0 && yoffset == 0)) && inRange(neighbourX, neighbourY) && crossPart(xoffset, yoffset, x, y)) {
          funct(neighbourX, neighbourY)
        }
      }
    }
  }

}
