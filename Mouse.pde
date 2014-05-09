import java.awt.AWTException;
import java.awt.Robot;

class Mouse {

  Robot robot;
  int prevX;

  Mouse(int x) {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      println("Unable to instantiate Robot");
      exit();
    }
    prevX = x;
  }

  int dx() {
    if (mouseX == 0) return 1 - prevX;
    return mouseX - prevX;
  }

  void moved() {
    if (mouseX == width - 1) {
      robot.mouseMove(1, mouseY);
      prevX = 1;
    } else if (mouseX <= 1) {
      robot.mouseMove(width - 1, mouseY);
      prevX = width - 1;
    } else {
      prevX = mouseX;
    }
  }
}