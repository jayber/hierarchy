
import javafx.beans.property.ObjectPropertyBase
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.ColorPicker
import javafx.scene.layout.Pane
import javafx.scene.paint.Color

class ColorPane extends Pane {
  this.setPrefSize(100, 100)
  private val colorPicker: ColorPicker = new ColorPicker()
  colorPicker.setOpacity(0)
  colorPicker.setPrefHeight(100)
  colorPicker.setPrefWidth(100)
  colorPicker.setOnAction(new EventHandler[ActionEvent] {
    def handle(p1: ActionEvent) = {
      color = colorPicker.getValue
    }
  })
  getChildren.add(colorPicker)

  var colour = Color.rgb(0, 0, 0)

  def color = colour

  def color_=(color: Color) = {
    colour = color
    val style: String = s"-fx-background-color: rgb(${color.getRed * 100}%,${color.getGreen * 100}%,${color.getBlue * 100}%);"
    setStyle(style)
  }

  var colorProperty =
    new ObjectPropertyBase[Color]() {
      def getBean: AnyRef = colour

      def getName: String = "color"
    }
}
