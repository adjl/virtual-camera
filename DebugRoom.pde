final int screenWidth = 1366;
final int screenHeight = 768;
final color black = color(0, 0, 0);

void setup() {
  size(screenWidth, screenHeight, P3D);
}

void draw() {
  background(black);
}

void keyPressed() {
  switch (key) {
    case 'q': // Quit
      exit();
      break;
  }
}