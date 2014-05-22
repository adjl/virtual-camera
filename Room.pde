class Room {

  float width;
  float height;
  float depth;

  Room() {
    width = 200;
    height = 100;
    depth = 200;
  }

  boolean within(PVector position) {
    return (position.x >= -width / 2) && (position.x < width / 2) &&
           (position.y >= 0) && (position.y < height) &&
           (position.z >= -depth / 2) && (position.z < depth / 2);
  }

  void draw() {
    pushMatrix();
    translate(0, 0, 0);
    box(width, height, depth);
    popMatrix();
  }
}