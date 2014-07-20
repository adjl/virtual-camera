final int WIDTH = 1366;
final int HEIGHT = 768;
final float WORLD_WIDTH = 200.0;
final float WORLD_HEIGHT = 100.0;
final float WORLD_DEPTH = 200.0;
final float CAMERA_HEIGHT = 50.0;
final float CAMERA_SPEED = 3.0;

World world;
Camera camera;

void setup() {
  size(WIDTH, HEIGHT, P3D);
  stroke(#FFFFFF);
  strokeWeight(2);
  noCursor();
  noFill();
  world = new World(WORLD_WIDTH, WORLD_HEIGHT, WORLD_DEPTH);
  camera = new Camera(CAMERA_HEIGHT, CAMERA_SPEED);
}

void draw() {
  background(#000000);
  world.draw();
  camera.set();
}

void keyPressed() {
  switch (key) {
    case 'w': // Move forward
      if (world.contains(camera.forwardPosition())) {
        camera.moveForward();
      }
      break;
    case 'a': // Strafe left
      if (world.contains(camera.leftPosition())) {
        camera.strafeLeft();
      }
      break;
    case 's': // Move backward
      if (world.contains(camera.backwardPosition())) {
        camera.moveBackward();
      }
      break;
    case 'd': // Strafe right
      if (world.contains(camera.rightPosition())) {
        camera.strafeRight();
      }
      break;
    case 'r': // Fly up
      if (world.contains(camera.upPosition())) {
        camera.flyUp();
      }
      break;
    case 'f': // Fly down
      PVector position = camera.downPosition();
      if (world.contains(position) && camera.aboveHeight(position)) {
        camera.flyDown();
      }
      break;
    case 'q': // Quit
      exit();
      break;
  }
}
