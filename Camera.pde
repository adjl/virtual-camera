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
    eyeX = width / 2.0;
    eyeY = height / 2.0;
    eyeZ = (height / 2.0) / tan(PI * 30 / 180);

    centreX = width / 2.0;
    centreY = height / 2.0;
    centreZ = 0;

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

  void set() {
    camera(eyeX, eyeY, eyeZ, centreX, centreY, centreZ, upX, upY, upZ);
  }
}