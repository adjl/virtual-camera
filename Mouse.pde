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
    if (width % 2 == 0 && mouseX == 0) return 1 - prevX;
    return mouseX - prevX;
  }

  void move(int x, int y) {
    robot.mouseMove(x, y);
  }

  void moved() {
    if (mouseX == width - 1) {
      robot.mouseMove(1, mouseY);
      prevX = 1;
    } else if ((width % 2 == 0 && mouseX == 1) || mouseX == 0) {
      robot.mouseMove(width - 1, mouseY);
      prevX = width - 1;
    } else {
      prevX = mouseX;
    }
  }
}