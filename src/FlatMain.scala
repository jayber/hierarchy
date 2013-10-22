import javafx.application.Application
import javafx.scene.{Scene, Parent}
import javafx.stage.Stage

object FlatMain {
  def main(args: Array[String]) {
    Application.launch(classOf[FlatMain], args: _*)
  }
}

class FlatMain extends Application {

  def start(primaryStage: Stage) {
    primaryStage.setTitle("Colour Grid")
    val root: Parent = new ColorGrid(2)
    primaryStage.setScene(new Scene(root))
    primaryStage.show()
  }

}
