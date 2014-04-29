void GLWidget::mouseMoveEvent(QMouseEvent *event)
{
  if (event->buttons() & Qt::LeftButton) {
    switch (activeViewport) { // Move cameras in viewports accordingly
      case ISO:   isoCamera->setDeltaTheta(toRadians(xClick - event->x() + (width / 2)));
                  isoCamera->setDeltaPhi(toRadians(event->y() - yClick));
                  break;
    }
  }
}

void GLWidget::mouseReleaseEvent(QMouseEvent *event)
{
  if (event->button() == Qt::LeftButton) {
    switch (activeViewport) {
      case ISO:   isoCamera->updateTheta(isoCamera->getDeltaTheta());
                  isoCamera->updatePhi(isoCamera->getDeltaPhi());
                  break;
    }
  }
}