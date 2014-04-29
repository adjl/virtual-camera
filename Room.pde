class Room {

  Camera camera;
  float width, height, depth;

  Room(Camera camera) {
    this.camera = camera;
    width = depth = 200;
    height = 100;
  }

  void draw() {
    pushMatrix();
    translate(0, 0, 0);
    box(width, height, depth);
    popMatrix();
  }
}