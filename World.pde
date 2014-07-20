class World {

  float width;
  float height;
  float depth;

  World(float width, float height, float depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  boolean contains(PVector position) {
    return (position.x >= -width / 2) && (position.x < width / 2) &&
           (position.y >= 0) && (position.y < height) &&
           (position.z >= -depth / 2) && (position.z < depth / 2);
  }

  void draw() {
    pushMatrix();
    translate(0, -height / 2, 0);
    box(width, height, depth);
    popMatrix();
  }
}
