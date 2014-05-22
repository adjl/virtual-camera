final int width = 1366;
final int height = 768;
final color black = color(0, 0, 0);

Mouse mouse;
Camera camera;
Room room;

void setup() {
  size(width, height, P3D);
  noCursor();
  mouse = new Mouse();
  camera = new Camera(mouse, width, height);
  room = new Room();
}

void draw() {
  background(black);
  if (mouse.centred()) mouse.moved();
  else mouse.centre();
  camera.set();
  room.draw();
}

void keyPressed() {
  switch (key) {
    case 'w':
      if (withinRoom(camera.simulateMoveForward())) camera.moveForward();
      break;
    case 'a':
      if (withinRoom(camera.simulateStrafeLeft())) camera.strafeLeft();
      break;
    case 's':
      if (withinRoom(camera.simulateMoveBackward())) camera.moveBackward();
      break;
    case 'd':
      if (withinRoom(camera.simulateStrafeRight())) camera.strafeRight();
      break;
    case 'q':
      exit();
      break;
  }
}

boolean withinRoom(float[] cameraPosition) {
  float x = cameraPosition[0];
  float z = cameraPosition[1];
  return (x > -100 && x < 100) && (z > -100 && z < 100);
}