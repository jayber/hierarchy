import javafx.fxml.FXML
import javafx.scene.control.{TextField, ColorPicker}
import javafx.scene.Scene
import javafx.stage.Stage

class ManagerGraphController {
  @FXML var colorPicker: ColorPicker = null
  @FXML var layers: TextField = null
  @FXML var independence: TextField = null

  @FXML def doHierarchyDisplay() {
    val height: Double = 50
    val minWidth: Int = 10
    val root = new HierarchyBox(height, minWidth, layers.getText.toInt, independence.getText.toDouble, colorPicker.getValue)

    val dialog: Stage = new Stage
    dialog.setTitle("How much does the top look like the bottom?")
    dialog.setScene(new Scene(root))
    dialog.show()
  }
}