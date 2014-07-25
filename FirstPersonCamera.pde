final float WORLD_WIDTH = 200.0;
final float WORLD_HEIGHT = 100.0;
final float WORLD_DEPTH = 200.0;
final float CAMERA_HEIGHT = 50.0;
final float CAMERA_SPEED = 3.0;

World world;
Camera camera;

void setup() {
  size(displayWidth, displayHeight, P3D);
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
  camera.moveDirection(world, key);
}
