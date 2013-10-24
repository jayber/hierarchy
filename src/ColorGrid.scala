import javafx.scene.layout.GridPane
import scala.collection.mutable

class ColorGrid(size: Int) extends GridPane {

  val grid = Array.ofDim[ColorPane](size, size)

  for (x <- 0 until size) {
    for (y <- 0 until size) {
      val pane: ColorPane = new ColorPane()
      grid(x)(y) = pane
      add(pane, x, y)
    }
  }

  def neighbours(x: Int, y: Int, grid: Array[Array[ColorPane]]) = {
    def inRange(x: Int, y: Int) = {
      x > -1 && y > -1 && x < grid.length && y < grid(x).length
    }

    val neighbours = mutable.MutableList[ColorPane]()
    for (xoffset <- -1 to 1) {
      val neighbourX = x + xoffset
      for (yoffset <- -1 to 1) {
        val neighbourY = y + yoffset
        if ((!(xoffset == 0 && yoffset == 0)) && inRange(neighbourX, neighbourY)) {
          neighbours += grid(neighbourX)(neighbourY)
        }
      }
    }
    neighbours
  }

  for (x <- 0 until size) {
    for (y <- 0 until size) {
      grid(x)(y).neighbours = neighbours(x, y, grid)
    }
  }
}
