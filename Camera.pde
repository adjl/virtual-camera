class Camera {

  Mouse mouse;
  float eyeX, eyeY, eyeZ;
  float centreX, centreY, centreZ;
  float upX, upY, upZ;
  float verticalAngle, horizontalAngle;

  Camera(Mouse mouse, int width, int height) {
    this.mouse = mouse;
    eyeX = eyeY = eyeZ = 0;
    centreX = centreY = 0;
    centreZ = -1;
    upX = upZ = 0;
    upY = 1;
    verticalAngle = horizontalAngle = 0;
    perspective(PI * 3 / 8, 4 / 3.075, 0.1, 1000);
  }

  void moveForward() {
    float distanceX = sin(horizontalAngle);
    float distanceZ = cos(horizontalAngle);
    eyeX += distanceX;
    centreX += distanceX;
    eyeZ -= distanceZ;
    centreZ -= distanceZ;
  }

  void moveBackward() {
    float distanceX = sin(horizontalAngle);
    float distanceZ = cos(horizontalAngle);
    eyeX -= distanceX;
    centreX -= distanceX;
    eyeZ += distanceZ;
    centreZ += distanceZ;
  }

  void strafeLeft() {
    float distanceX = sin(horizontalAngle + PI / 2);
    float distanceZ = cos(horizontalAngle + PI / 2);
    eyeX -= distanceX;
    centreX -= distanceX;
    eyeZ += distanceZ;
    centreZ += distanceZ;
  }

  void strafeRight() {
    float distanceX = sin(horizontalAngle + PI / 2);
    float distanceZ = cos(horizontalAngle + PI / 2);
    eyeX += distanceX;
    centreX += distanceX;
    eyeZ -= distanceZ;
    centreZ -= distanceZ;
  }

  void set() {
    verticalAngle -= mouse.dy() * PI / (height - 2);
    horizontalAngle += mouse.dx() * PI / (width - 2);
    beginCamera();
    camera(eyeX, eyeY, eyeZ, centreX, centreY, centreZ, upX, upY, upZ);
    translate(eyeX, eyeY, eyeZ);
    rotateX(verticalAngle);
    rotateY(horizontalAngle);
    translate(-centreX, -centreY, -centreZ);
    endCamera();
  }
}