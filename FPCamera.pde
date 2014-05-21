final int screenWidth = 1366;
final int screenHeight = 768;
final color black = color(0, 0, 0);

Camera camera;
Mouse mouse;
Room room;

void setup() {
  size(screenWidth, screenHeight, P3D);
  noCursor();
  mouse = new Mouse();
  camera = new Camera(mouse, width, height);
  room = new Room(camera);
}

void draw() {
  background(black);
  if (mouse.centred()) mouse.moved();
  else mouse.centre();
  camera.set();
  room.draw();
  println(camera.eyeX + " " + camera.eyeZ);
}

void keyPressed() {
  switch (key) {
    case 'w':
      if (withinRoom()) camera.moveForward();
      break;
    case 'a':
      if (withinRoom()) camera.strafeLeft();
      break;
    case 's':
      if (withinRoom()) camera.moveBackward();
      break;
    case 'd':
      if (withinRoom()) camera.strafeRight();
      break;
    case 'q':
      exit();
      break;
  }
}

boolean withinRoom() {
  return (camera.eyeX > -90) && (camera.eyeX < 90) && (camera.eyeZ > -90) && (camera.eyeZ < 90);
}