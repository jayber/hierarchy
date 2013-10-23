
import javafx.beans.property.ObjectPropertyBase
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.ColorPicker
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import scala.actors.Actor

class ColorPane(prefSize: Double = 5) extends Pane with Actor {

  this.setPrefSize(prefSize, prefSize)
  private val colorPicker: ColorPicker = new ColorPicker()
  colorPicker.setOpacity(0)
  colorPicker.setPrefHeight(prefSize)
  colorPicker.setPrefWidth(prefSize)
  colorPicker.setOnAction(new EventHandler[ActionEvent] {
    def handle(p1: ActionEvent) = {
      infect(System.currentTimeMillis(), colorPicker.getValue)
    }
  })
  getChildren.add(colorPicker)

  var colour = Color.rgb(0, 0, 0)
  var neighbs: Iterable[ColorPane] = null
  var ids: Set[Long] = Set()

  def color = colour

  def neighbours = neighbs

  def neighbours_=(them: Iterable[ColorPane]) {
    neighbs = them
  }

  def act() {
    val realSteel = Actor.self
    while (true) {
      receive {
        case (id: Long, color: Color) => {
          Actor.actor {
            Thread.sleep(1)
            realSteel !(id, color, true)
          }
        }
        case (id: Long, color: Color, true) => {
          infect(id, color)
        }
      }
    }
  }

  def infect(id: Long, color: Color) {
    synchronized {
      if (!ids.contains(id)) {
        ids += id

        this.color = color

        neighbours.foreach({
          _ !(id, color)
        })

      }
    }
  }

  def color_=(color: Color) {
    if (color != colour) {
      colour = color
      val style: String = s"-fx-background-color: rgb(${color.getRed * 100}%,${color.getGreen * 100}%,${color.getBlue * 100}%);"
      setStyle(style)
    }
  }

  var colorProperty =
    new ObjectPropertyBase[Color]() {
      def getBean: AnyRef = colour

      def getName: String = "color"
    }
}
