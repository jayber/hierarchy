import javafx.beans.value.{ObservableValue, ChangeListener}
import javafx.scene.layout.{HBox, VBox, Pane}
import javafx.scene.paint.Color
import scala.collection.mutable

class HierarchyBox(height: Double, minWidth: Double, layers: Int, employeeIndependence: Double, firstColor: Color) extends VBox {
  val width: Double = math.pow(2, layers) * minWidth
  this.setPrefWidth(width)

  val manager = new Manager(Color.rgb(255, 255, 255), employeeIndependence)
  val box: HBox = new HBox()
  box.setPrefHeight(height)
  this.getChildren.add(box)
  bind(manager, box)

  createChildren(this, layers - 2, manager)
  manager.color = firstColor


  def createChild(manager: Manager) = {

    val box: VBox = new VBox()
    box.setPrefWidth(10000)
    val child: Manager = new Manager(Color.rgb(255, 255, 255), employeeIndependence)

    bind(child, box)

    manager.addChild(child)

    (box, child)
  }

  def createChildren(root: Pane, layers: Int, managers: Manager*) {
    val box: HBox = new HBox()
    box.setPrefHeight(height)

    val children = mutable.MutableList[Manager]()
    for (manager <- managers) {
      val child1 = createChild(manager)
      box.getChildren.add(child1._1)
      children += child1._2
      val child2 = createChild(manager)
      box.getChildren.add(child2._1)
      children += child2._2
    }

    root.getChildren.add(box)
    if (layers > 0) {
      createChildren(root, layers - 1, children: _*)
    }
  }

  def bind(manager: Manager, box: Pane) {
    manager.colorProperty.addListener(new ChangeListener[Color] {
      def changed(p1: ObservableValue[_ <: Color], p2: Color, p3: Color) {
        val style: String = s"-fx-background-color: rgb(${p3.getRed * 100}%,${p3.getGreen * 100}%,${p3.getBlue * 100}%);"
        box.setStyle(style)
      }
    })
  }
}
