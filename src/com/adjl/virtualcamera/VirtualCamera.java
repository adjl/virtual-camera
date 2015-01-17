package com.adjl.virtualcamera;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * Virtual camera.
 *
 * Java implementation of a virtual (3D first-person) camera for use in Processing sketches. Allows
 * you to control the camera with the keyboard and mouse, using WASD-style movement.
 *
 * @author Helena Josol
 */
public class VirtualCamera {

    private final PApplet mSketch;
    private final VirtualWorld mWorld;
    private final Pointer mMouse;
    private final PVector mEye;
    private final PVector mCentre;
    private final PVector mUp;
    private final PVector mAngle;
    private final float mFovy;
    private final float mAspect;
    private final float mZNear;
    private final float mZFar;
    private final float mHeight;
    private final float mSpeed;

    /**
     * Constructs a VirtualCamera for the given PApplet and {@link VirtualWorld}.
     *
     * @param sketch PApplet the camera is used in.
     * @param world {@link VirtualWorld} the camera is used in.
     * @param height Height of the camera.
     * @param speed Speed of the camera.
     */
    public VirtualCamera(PApplet sketch, VirtualWorld world, float height, float speed) {
        mSketch = sketch;
        mWorld = world;
        mHeight = height;
        mSpeed = speed;
        mMouse = new Pointer(mSketch, 3);
        mEye = new PVector(0.0f, mHeight, 0.0f);
        mCentre = new PVector(0.0f, mHeight, -1.0f);
        mUp = new PVector(0.0f, 1.0f, 0.0f);
        mAngle = new PVector();
        mFovy = PConstants.HALF_PI * 3.0f / 4.0f;
        mAspect = 4.0f / 3.075f;
        mZNear = 0.1f;
        mZFar = 10000.0f;
        mSketch.perspective(mFovy, mAspect, mZNear, mZFar);
    }

    @VisibleForTesting
    PVector getEye() {
        return mEye;
    }

    @VisibleForTesting
    PVector getCentre() {
        return mCentre;
    }

    private PVector getForwardDirection() {
        return new PVector(-PApplet.sin(mAngle.x) * mSpeed, 0.0f, PApplet.cos(mAngle.x) * mSpeed);
    }

    private PVector getBackwardDirection() {
        return new PVector(PApplet.sin(mAngle.x) * mSpeed, 0.0f, -PApplet.cos(mAngle.x) * mSpeed);
    }

    private PVector getLeftDirection() {
        return new PVector(PApplet.sin(mAngle.x + PConstants.HALF_PI) * mSpeed, 0.0f,
                -PApplet.cos(mAngle.x + PConstants.HALF_PI) * mSpeed);
    }

    private PVector getRightDirection() {
        return new PVector(-PApplet.sin(mAngle.x + PConstants.HALF_PI) * mSpeed, 0.0f,
                PApplet.cos(mAngle.x + PConstants.HALF_PI) * mSpeed);
    }

    private PVector getUpDirection() {
        return new PVector(0.0f, mSpeed, 0.0f);
    }

    private PVector getDownDirection() {
        return new PVector(0.0f, -mSpeed, 0.0f);
    }

    private PVector getPosition(PVector direction) {
        PVector position = mEye.get();
        position.add(direction);
        return position;
    }

    private void move(PVector direction) {
        mEye.add(direction);
        mCentre.add(direction);
    }

    /**
     * Moves the VirtualCamera in the direction specified by the given key.
     *
     * Uses WASD-style movement; press R to fly up and F to fly down. Directions are relative to
     * where the camera is facing, as controlled by the mouse.
     *
     * @param key Key specifying the direction to move the camera.
     */
    public void move(char key) {
        switch (key) {
            case 'w': // Move forward
                if (mWorld.isWithin(getPosition(getForwardDirection()))) {
                    move(getForwardDirection());
                }
                break;
            case 'a': // Strafe left
                if (mWorld.isWithin(getPosition(getLeftDirection()))) {
                    move(getLeftDirection());
                }
                break;
            case 's': // Move backward
                if (mWorld.isWithin(getPosition(getBackwardDirection()))) {
                    move(getBackwardDirection());
                }
                break;
            case 'd': // Strafe right
                if (mWorld.isWithin(getPosition(getRightDirection()))) {
                    move(getRightDirection());
                }
                break;
            case 'r': // Fly up
                if (mWorld.isWithin(getPosition(getUpDirection()))) {
                    move(getUpDirection());
                }
                break;
            case 'f': // Fly down
                PVector position = getPosition(getDownDirection());
                if (mWorld.isWithin(position) && position.y >= mHeight) {
                    move(getDownDirection());
                }
                break;
            case 'q': // Quit
                mSketch.exit();
                break;
            default:
                break;
        }
    }

    /**
     * Sets the VirtualCamera for the current frame (PApplet.draw() call).
     *
     * Should be called after the {@link VirtualWorld} is drawn.
     */
    public void set() {
        if (mMouse.isCentred()) {
            mMouse.move();
        } else {
            mMouse.centre();
        }
        mAngle.x = mMouse.getX() * PConstants.TAU / (mSketch.width - 1);
        mAngle.y = mMouse.getY() * PConstants.HALF_PI / (mSketch.height - 1);
        mSketch.beginCamera();
        mSketch.camera(mEye.x, mEye.y, mEye.z, mCentre.x, mCentre.y, mCentre.z, mUp.x, mUp.y, mUp.z);
        mSketch.translate(mEye.x, mEye.y, mEye.z);
        mSketch.rotateX(mAngle.y);
        mSketch.rotateY(mAngle.x);
        mSketch.translate(mEye.x, mEye.y, mEye.z);
        mSketch.endCamera();
    }

    private static class Pointer {

        private final PApplet mSketch;

        private Robot mRobot;
        private boolean mCentred;
        private boolean mWrapped;
        private int mAttempts;

        Pointer(PApplet sketch, int attempts) {
            mSketch = sketch;
            try {
                mRobot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
                mSketch.exit();
            }
            mCentred = false;
            mWrapped = false;
            mAttempts = attempts;
        }

        int getX() {
            return mSketch.mouseX;
        }

        int getY() {
            return (mSketch.height / 2) - mSketch.mouseY;
        }

        boolean isCentred() {
            return mCentred;
        }

        void centre() {
            mRobot.mouseMove(mSketch.width / 2, mSketch.height / 2);
            if (--mAttempts == 0) { // Cursor centering works on the final centre() call
                mCentred = true;
            }
        }

        void move() { // Wrap cursor horizontally
            if (mWrapped && mSketch.mouseX > 0 && mSketch.mouseX < mSketch.width - 1) {
                mWrapped = false;
            } else if (!mWrapped && mSketch.mouseX == 0) {
                mWrapped = true;
                mRobot.mouseMove(mSketch.width - 1, mSketch.mouseY);
            } else if (!mWrapped && mSketch.mouseX == mSketch.width - 1) {
                mWrapped = true;
                mRobot.mouseMove(0, mSketch.mouseY);
            }
        }
    }

    private static @interface VisibleForTesting { // Purposely empty
    }
}
