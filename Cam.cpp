isoCamera->setDeltaTheta(toRadians(xClick - event->x());
isoCamera->setDeltaPhi(toRadians(event->y() - yClick));
isoCamera->updateTheta(dTheta);
isoCamera->updatePhi(dPhi);