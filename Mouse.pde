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
    return mouseX - prevX;
  }

  void move(int x, int y) {
    robot.mouseMove(x, y);
  }

  void moved() {
    prevX = mouseX;
  }
}