class Camera {

  float eyeX;
  float eyeY;
  float eyeZ;

  float centreX;
  float centreY;
  float centreZ;

  float upX;
  float upY;
  float upZ;

  float angle;

  Camera(int width, int height) {
    eyeX = 0;
    eyeY = 0;
    eyeZ = 0;

    centreX = 0;
    centreY = 0;
    centreZ = -1;

    upX = 0;
    upY = 1;
    upZ = 0;

    angle = 0;
  }

  float getEyeX() {
    return eyeX;
  }

  float getEyeY() {
    return eyeY;
  }

  float getEyeZ() {
    return eyeZ;
  }

  void moveForward() {
    eyeZ--;
    centreZ--;
  }

  void moveLeft() {
    angle -= PI / 180;
  }

  void moveBackward() {
    eyeZ++;
    centreZ++;
  }

  void moveRight() {
    angle += PI / 180;
  }

  void set() {
    beginCamera();
    camera(eyeX, eyeY, eyeZ, centreX, centreY, centreZ, upX, upY, upZ);
    rotateY(angle);
    endCamera();
  }
}