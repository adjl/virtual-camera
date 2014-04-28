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
    eyeX--;
    centreX--;
  }

  void moveBackward() {
    eyeZ++;
    centreZ++;
  }

  void moveRight() {
    eyeX++;
    centreX++;
  }

  void set() {
    camera(eyeX, eyeY, eyeZ, centreX, centreY, centreZ, upX, upY, upZ);
  }
}