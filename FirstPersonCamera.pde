final int width = 1366;
final int height = 768;
final float worldWidth = 200.0f;
final float worldHeight = 100.0f;
final float worldDepth = 200.0f;

World world;
Camera camera;

void setup() {
  size(width, height, P3D);
  noCursor();
  world = new World(worldWidth, worldHeight, worldDepth);
  camera = new Camera();
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