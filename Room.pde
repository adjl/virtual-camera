class Room {

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
    translate(camera.getEyeX() - (wallSize / 2), camera.getEyeY() - (wallSize / 2), camera.getEyeZ() - wallSize);
    scale(wallSize);
    wall();
    popMatrix();
  }
}