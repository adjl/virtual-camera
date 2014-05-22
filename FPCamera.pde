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
  camera = new Camera(mouse);
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
      if (room.within(camera.forwardPosition())) {
        camera.moveForward();
      }
      break;
    case 'a':
      if (room.within(camera.leftPosition())) {
        camera.strafeLeft();
      }
      break;
    case 's':
      if (room.within(camera.backwardPosition())) {
        camera.moveBackward();
      }
      break;
    case 'd':
      if (room.within(camera.rightPosition())) {
        camera.strafeRight();
      }
      break;
    case 'q':
      exit();
      break;
  }
}