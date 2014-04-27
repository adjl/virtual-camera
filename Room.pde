class Room {

  final float centreX = width / 2.0;
  final float centreY = height / 2.0;
  final float centreZ = (height / 2.0) / tan(PI * 30 / 180);

  Camera camera;
  float wallSize;

  Room(Camera camera) {
    this.camera = camera;
    wallSize = 200;
  }

  void wall() {
    beginShape(QUADS);
    vertex(0, 0, 0);
    vertex(1, 0, 0);
    vertex(1, 1, 0);
    vertex(0, 1, 0);
    endShape(CLOSE);
  }

  void draw() {
    pushMatrix();
    translate(centreX - (wallSize / 2), centreY - (wallSize / 2), centreZ - wallSize);
    scale(wallSize);
    wall();
    popMatrix();
  }
}