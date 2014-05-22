class Room {

  float width;
  float height;
  float depth;

  Room() {
    width = 200.0f;
    height = 100.0f;
    depth = 200.0f;
  }

  void draw() {
    pushMatrix();
    translate(0, 0, 0);
    box(width, height, depth);
    popMatrix();
  }
}