import javafx.application.{Platform, Application}
import javafx.event.EventHandler
import javafx.scene.{Scene, Parent}
import javafx.stage.{WindowEvent, Stage}

object FlatMain {
  def main(args: Array[String]) {
    Application.launch(classOf[FlatMain], args: _*)
  }
}

class FlatMain extends Application {

  def start(primaryStage: Stage) {
    Platform.setImplicitExit(true)
    primaryStage.setTitle("Colour Grid")
    val root: Parent = new CanvasGrid(500, 1)
    CanvasActor.start()
    primaryStage.setScene(new Scene(root))
    primaryStage.show()
    primaryStage.setOnCloseRequest(new EventHandler[WindowEvent] {
      def handle(p1: WindowEvent) = System.exit(0)
    })
  }


}
