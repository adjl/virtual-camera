Camera::Camera(qreal x, qreal y, qreal z) :
  x(x), y(y), z(z)
{
}

void Camera::setX(qreal x)
{
  this->x = x;
}

void Camera::setY(qreal y)
{
  this->y = y;
}

void Camera::setZ(qreal z)
{
  this->z = z;
}

qreal Camera::getX(void)
{
  return x;
}

qreal Camera::getY(void)
{
  return y;
}

qreal Camera::getZ(void)
{
  return z;
}

IsoCamera::IsoCamera(qreal x, qreal y, qreal z) :
  Camera(x, y, z)
{
}

void IsoCamera::setX0(qreal x0)
{
  this->x0 = x0;
}

void IsoCamera::setY0(qreal y0)
{
  this->y0 = y0;
}

void IsoCamera::setZ0(qreal z0)
{
  this->z0 = z0;
}

void IsoCamera::updateTheta(qreal dTheta)
{
  this->theta += dTheta;
}

void IsoCamera::updatePhi(qreal dPhi)
{
  this->phi += dPhi;
}

void IsoCamera::setDeltaTheta(qreal dTheta)
{
  this->dTheta = dTheta;
}

void IsoCamera::setDeltaPhi(qreal dPhi)
{
  this->dPhi = dPhi;
}

void IsoCamera::setRadius(qreal radius)
{
  this->radius = radius;
}

qreal IsoCamera::getX0(void)
{
  return x0;
}

qreal IsoCamera::getY0(void)
{
  return y0;
}

qreal IsoCamera::getZ0(void)
{
  return z0;
}

qreal IsoCamera::getTheta(void)
{
  return theta;
}

qreal IsoCamera::getPhi(void)
{
  return phi;
}

qreal IsoCamera::getDeltaTheta(void)
{
  return dTheta;
}

qreal IsoCamera::getDeltaPhi(void)
{
  return dPhi;
}

qreal IsoCamera::getRadius(void)
{
  return radius;
}

qreal IsoCamera::calculateRadius(void)
{
  QVector3D currentTarget = targets[target];
  return qSqrt(qPow(x - currentTarget.x(), 2) + qPow(y - currentTarget.y(), 2) + qPow(z - currentTarget.z(), 2));
}

void GLWidget::drawIsoScene(IsoCamera *camera)
{
  gluLookAt(camera->getX(),       camera->getY(),       camera->getZ(),
            camera->getTargetX(), camera->getTargetY(), camera->getTargetZ(),
            0.0,                  1.0,                  0.0);

  glRotatef(xAngle, 1.0, 0.0, 0.0);
  glRotatef(yAngle, 0.0, 1.0, 0.0);
  glRotatef(zAngle, 0.0, 0.0, 1.0);

  glCallList(grid);
  if (cubeVisible) glCallList(cube);
  glCallList(axes);

  spline->drawSpline();
}

void GLWidget::mouseMoveEvent(QMouseEvent *event)
{
  if (event->buttons() & Qt::LeftButton) {
    switch (activeViewport) { // Move cameras in viewports accordingly
      case TOP:   topCamera->setDeltaHorizontal(smooth(event->x() - xClick));
                  topCamera->setDeltaVertical(smooth(event->y() - yClick));
                  topCamera->setX(topCamera->getHorizontal() + topCamera->getDeltaHorizontal());
                  topCamera->setZ(topCamera->getVertical() + topCamera->getDeltaVertical());
                  break;
      case FRONT: frontCamera->setDeltaHorizontal(smooth(event->x() - xClick));
                  frontCamera->setDeltaVertical(smooth(yClick - event->y() + (height / 2)));
                  frontCamera->setX(frontCamera->getHorizontal() + frontCamera->getDeltaHorizontal());
                  frontCamera->setY(frontCamera->getVertical() + frontCamera->getDeltaVertical());
                  break;
      case SIDE:  sideCamera->setDeltaHorizontal(smooth(xClick - event->x() + (width / 2)));
                  sideCamera->setDeltaVertical(smooth(yClick - event->y() + (height / 2)));
                  sideCamera->setZ(sideCamera->getHorizontal() + sideCamera->getDeltaHorizontal());
                  sideCamera->setY(sideCamera->getVertical() + sideCamera->getDeltaVertical());
                  break;
      case ISO:   isoCamera->setDeltaTheta(toRadians(xClick - event->x() + (width / 2)));
                  isoCamera->setDeltaPhi(toRadians(event->y() - yClick));
                  isoCamera->setX(isoCamera->getRadius() * qCos(isoCamera->getTheta() + isoCamera->getDeltaTheta())
                                  * qSin(isoCamera->getPhi() + isoCamera->getDeltaPhi()) + isoCamera->getTargetX());
                  isoCamera->setY(isoCamera->getRadius() * qCos(isoCamera->getPhi() + isoCamera->getDeltaPhi())
                                  + isoCamera->getTargetY());
                  isoCamera->setZ(isoCamera->getRadius() * qSin(isoCamera->getTheta() + isoCamera->getDeltaTheta())
                                  * qSin(isoCamera->getPhi() + isoCamera->getDeltaPhi()) + isoCamera->getTargetZ());
                  break;
    }
  } else if (event->buttons() & Qt::RightButton) {
    switch (activeViewport) { // Zoom in viewports accordingly
      case TOP:   topCamera->setZoom(max(topCamera->getZoom0() + smooth(event->y() - zoomClick), MIN_ZOOM));
                  break;
      case FRONT: frontCamera->setZoom(max(frontCamera->getZoom0() + smooth(event->y() - zoomClick), MIN_ZOOM));
                  break;
      case SIDE:  sideCamera->setZoom(max(sideCamera->getZoom0() + smooth(event->y() - zoomClick), MIN_ZOOM));
                  break;
      case ISO:   qreal t = smooth(zoomClick - event->y());
                  isoCamera->setX(isoCamera->getX0() + t * (isoCamera->getTargetX() - isoCamera->getX0()));
                  isoCamera->setY(isoCamera->getY0() + t * (isoCamera->getTargetY() - isoCamera->getY0()));
                  isoCamera->setZ(isoCamera->getZ0() + t * (isoCamera->getTargetZ() - isoCamera->getZ0()));
                  break;
    }
  }

  updateGL();
}

void GLWidget::mouseReleaseEvent(QMouseEvent *event)
{
  if (event->button() == Qt::LeftButton) {
    switch (activeViewport) {
      case TOP:   topCamera->updateHorizontal(topCamera->getDeltaHorizontal());
                  topCamera->updateVertical(topCamera->getDeltaVertical());
                  break;
      case FRONT: frontCamera->updateHorizontal(frontCamera->getDeltaHorizontal());
                  frontCamera->updateVertical(frontCamera->getDeltaVertical());
                  break;
      case SIDE:  sideCamera->updateHorizontal(sideCamera->getDeltaHorizontal());
                  sideCamera->updateVertical(sideCamera->getDeltaVertical());
                  break;
      case ISO:   isoCamera->updateTheta(isoCamera->getDeltaTheta());
                  isoCamera->updatePhi(isoCamera->getDeltaPhi());
                  isoCamera->setX0(isoCamera->getX());
                  isoCamera->setY0(isoCamera->getY());
                  isoCamera->setZ0(isoCamera->getZ());
                  break;
    }
  } else if (event->button() == Qt::RightButton) {
    switch (activeViewport) {
      case TOP:   topCamera->setZoom0(topCamera->getZoom());
                  break;
      case FRONT: frontCamera->setZoom0(frontCamera->getZoom());
                  break;
      case SIDE:  sideCamera->setZoom0(sideCamera->getZoom());
                  break;
      case ISO:   isoCamera->setX0(isoCamera->getX());
                  isoCamera->setY0(isoCamera->getY());
                  isoCamera->setZ0(isoCamera->getZ());
                  isoCamera->setRadius(isoCamera->calculateRadius());
                  break;
    }
  }
}