import javafx.scene.layout.GridPane

class ColorGrid(size: Int) extends GridPane {

  val grid = Array.ofDim[ColorPane](size, size)

  for (x <- 0 until size) {
    for (y <- 0 until size) {
      val pane: ColorPane = new ColorPane()
      grid(x)(y) = pane
      add(pane, x, y)
    }
  }
}
