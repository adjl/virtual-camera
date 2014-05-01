final int screenWidth = 1366;
final int screenHeight = 768;
final color black = color(0, 0, 0);

Camera camera;
Room room;

void setup() {
  size(screenWidth, screenHeight, P3D);
  camera = new Camera(width, height);
  room = new Room(camera);
}

void draw() {
  background(black);
  camera.set();
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