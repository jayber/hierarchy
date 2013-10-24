
import javafx.application.Platform
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.ColorPicker
import javafx.scene.layout.Pane
import javafx.scene.paint.Color

class ColorPane(prefSize: Double = 3) extends Pane {

  val runner = new PaneCompanion3(this)

  this.setPrefSize(prefSize, prefSize)
  private val colorPicker: ColorPicker = new ColorPicker()
  colorPicker.setOpacity(0)
  colorPicker.setPrefHeight(prefSize)
  colorPicker.setPrefWidth(prefSize)
  colorPicker.setOnAction(new EventHandler[ActionEvent] {
    def handle(p1: ActionEvent) = {
      runner.changeColor(System.currentTimeMillis(), colorPicker.getValue)
    }
  })
  getChildren.add(colorPicker)

  var colour = Color.rgb(0, 0, 0)

  def color = colour

  def neighbours = runner.neighbours

  def neighbours_=(them: Iterable[ColorPane]) {
    runner.neighbours = them.map {
      _.runner
    }
  }

  def color_=(color: Color) {
    if (color != colour) {
      colour = color
      val style: String = s"-fx-background-color: rgb(${color.getRed * 100}%,${color.getGreen * 100}%,${color.getBlue * 100}%);"
      Platform.runLater(new Runnable() {
        def run() {
          styleProperty().setValue(style)
        }
      })
    }
  }

}
