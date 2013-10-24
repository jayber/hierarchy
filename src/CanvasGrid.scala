import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.canvas.Canvas
import javafx.scene.control.ColorPicker
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane

class CanvasGrid(size: Int, cellSize: Int) extends Pane {

  private val canvasSize = size * cellSize
  private val canvas: Canvas = new Canvas(canvasSize, canvasSize)
  getChildren.add(canvas)

  val cell = new CanvasCell(canvas.getGraphicsContext2D, cellSize, size)

  private val colorPicker: ColorPicker = new ColorPicker()
  colorPicker.setOpacity(0)
  colorPicker.setPrefHeight(canvasSize)
  colorPicker.setPrefWidth(canvasSize)

  var cellX: Int = 0
  var cellY: Int = 0
  colorPicker.setOnMouseClicked(new EventHandler[MouseEvent] {
    def handle(p1: MouseEvent) {
      cellX = p1.getX.toInt / cellSize
      cellY = p1.getY.toInt / cellSize
    }
  })
  colorPicker.setOnAction(new EventHandler[ActionEvent] {
    def handle(p1: ActionEvent) = {
      CanvasActor !("changeColor", System.currentTimeMillis(), colorPicker.getValue, cell, cellX, cellY)
    }
  })
  getChildren.add(colorPicker)

}
