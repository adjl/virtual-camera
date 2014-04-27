class Room {

  float wallSize = 200;

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
    translate((width / 2.0) - (wallSize / 2),
              (height / 2.0) - (wallSize / 2),
              (height / 2.0) / tan(PI * 30 / 180) - wallSize);
    scale(wallSize);
    wall();
    popMatrix();
  }
}