import java.awt.AWTException;
import java.awt.Robot;

class Mouse {

  Robot robot;

  Mouse() {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      println("Unable to instantiate Robot");
      exit();
    }
  }

  int x() {
    return mouseX;
  }

  int y() {
    return (height / 2) - mouseY;
  }

  void moved() {
    if (mouseX == width - 1) {
      robot.mouseMove(0, mouseY);
    } else if (mouseX == 0) {
      robot.mouseMove(width - 1, mouseY);
    }
  }
}