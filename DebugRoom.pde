final int screenWidth = 1366;
final int screenHeight = 768;
final color black = color(0, 0, 0);

Room room;

void setup() {
  size(screenWidth, screenHeight, P3D);
  room = new Room();
}

void draw() {
  background(black);
  room.draw();
}

void keyPressed() {
  switch (key) {
    case 'q': // Quit
      exit();
      break;
  }
}