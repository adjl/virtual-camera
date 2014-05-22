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

  float[] simulateMoveForward() {
    float distanceX = sin(horizontalAngle);
    float distanceZ = cos(horizontalAngle);
    return new float[] { eyeX + distanceX, eyeZ - distanceZ };
  }

  float[] simulateMoveBackward() {
    float distanceX = sin(horizontalAngle);
    float distanceZ = cos(horizontalAngle);
    return new float[] { eyeX - distanceX , eyeZ + distanceZ };
  }

  float[] simulateStrafeLeft() {
    float distanceX = sin(horizontalAngle + PI / 2);
    float distanceZ = cos(horizontalAngle + PI / 2);
    return new float[] { eyeX - distanceX, eyeZ + distanceZ };
  }

  float[] simulateStrafeRight() {
    float distanceX = sin(horizontalAngle + PI / 2);
    float distanceZ = cos(horizontalAngle + PI / 2);
    return new float[] { eyeX + distanceX, eyeZ - distanceZ };
  }

  void set() {
    verticalAngle = mouse.y() * PI * 3 / (height - 1) / 4;
    horizontalAngle = mouse.x() * PI * 2 / (width - 1);
    beginCamera();
    camera(eyeX, eyeY, eyeZ, centreX, centreY, centreZ, upX, upY, upZ);
    translate(eyeX, eyeY, eyeZ);
    rotateX(verticalAngle);
    rotateY(horizontalAngle);
    translate(-centreX, -centreY, -centreZ);
    endCamera();
  }
}