final int screenWidth = 1366;
final int screenHeight = 768;
final color black = color(0, 0, 0);

Camera camera;
Mouse mouse;
Room room;

void setup() {
  size(screenWidth, screenHeight, P3D);
  // noCursor();
  mouse = new Mouse(width / 2);
  camera = new Camera(mouse, width, height);
  room = new Room(camera);
  mouse.move(width / 2, height / 2);
}

void draw() {
  background(black);
  camera.set();
  mouse.moved();
  room.draw();
}

void keyPressed() {
  switch (key) {
    case 'w':
      camera.moveForward();
      break;
    case 'a':
      camera.strafeLeft();
      break;
    case 's':
      camera.moveBackward();
      break;
    case 'd':
      camera.strafeRight();
      break;
    case 'q':
      exit();
      break;
  }
}