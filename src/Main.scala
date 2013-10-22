import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

object Main {
  def main(args: Array[String]) {
    Application.launch(classOf[Main], args: _*)
  }
}

class Main extends Application {

  def start(primaryStage: Stage) {
    primaryStage.setTitle("Manager")
    val root: Parent = FXMLLoader.load(getClass.getResource("controlPanel.fxml"))
    primaryStage.setScene(new Scene(root))
    primaryStage.show()
  }
}