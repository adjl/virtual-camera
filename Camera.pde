class Camera {

  float eyeX, eyeY, eyeZ;
  float centreX, centreY, centreZ;
  float upX, upY, upZ;
  float angle;

  Camera(int width, int height) {
    eyeX = eyeY = eyeZ = 0;
    centreX = centreY = 0;
    centreZ = -1;
    upX = upZ = 0;
    upY = 1;
    angle = 0;
    perspective(PI * 3 / 8, 4 / 3.075, 0.1, 1000);
  }

  void moveForward() {
    float distanceX = sin(angle);
    float distanceZ = cos(angle);
    eyeX += distanceX;
    centreX += distanceX;
    eyeZ -= distanceZ;
    centreZ -= distanceZ;
  }

  void moveBackward() {
    float distanceX = sin(angle);
    float distanceZ = cos(angle);
    eyeX -= distanceX;
    centreX -= distanceX;
    eyeZ += distanceZ;
    centreZ += distanceZ;
  }

  void strafeLeft() {
    float distanceX = sin(angle + PI / 2);
    float distanceZ = cos(angle + PI / 2);
    eyeX -= distanceX;
    centreX -= distanceX;
    eyeZ += distanceZ;
    centreZ += distanceZ;
  }

  void strafeRight() {
    float distanceX = sin(angle + PI / 2);
    float distanceZ = cos(angle + PI / 2);
    eyeX += distanceX;
    centreX += distanceX;
    eyeZ -= distanceZ;
    centreZ -= distanceZ;
  }

  void set() {
    angle = (mouseX - (width / 2)) * PI / 180;
    beginCamera();
    camera(eyeX, eyeY, eyeZ, centreX, centreY, centreZ, upX, upY, upZ);
    translate(eyeX, eyeY, eyeZ);
    rotateY(angle);
    translate(-centreX, -centreY, -centreZ);
    endCamera();
  }
}