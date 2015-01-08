package com.adjl.virtualcamera;

import java.awt.AWTException;
import java.awt.Robot;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * First-person camera.
 *
 * Java implementation of a first-person camera for use in Processing sketches. Allows you to
 * control the camera with the keyboard and mouse, using WASD(RF)-style movement.
 *
 * @author adjl
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
     * Constructs a Camera for the given sketch and world.
     *
     * @param sketch {@link PApplet} (Processing sketch) the camera is used in.
     * @param world World (environment) the camera is used in.
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

    public PVector getForwardDirection() {
        return new PVector(-PApplet.sin(mAngle.x) * mSpeed, 0.0f, PApplet.cos(mAngle.x) * mSpeed);
    }

    public PVector getBackwardDirection() {
        return new PVector(PApplet.sin(mAngle.x) * mSpeed, 0.0f, -PApplet.cos(mAngle.x) * mSpeed);
    }

    public PVector getLeftDirection() {
        return new PVector(PApplet.sin(mAngle.x + PConstants.HALF_PI) * mSpeed, 0.0f,
                -PApplet.cos(mAngle.x + PConstants.HALF_PI) * mSpeed);
    }

    public PVector getRightDirection() {
        return new PVector(-PApplet.sin(mAngle.x + PConstants.HALF_PI) * mSpeed, 0.0f,
                PApplet.cos(mAngle.x + PConstants.HALF_PI) * mSpeed);
    }

    public PVector getUpDirection() {
        return new PVector(0.0f, mSpeed, 0.0f);
    }

    public PVector getDownDirection() {
        return new PVector(0.0f, -mSpeed, 0.0f);
    }

    public PVector getPosition(PVector direction) {
        PVector position = mEye.get();
        position.add(direction);
        return position;
    }

    public void move(PVector direction) {
        mEye.add(direction);
        mCentre.add(direction);
    }

    /**
     * Moves the camera in the direction specified by the given key.
     *
     * Uses WASD(RF)-movement; press R to fly up and F to fly down. Directions are relative to where
     * the camera is facing, as controlled by the mouse.
     *
     * @param key Key specifying the direction to move the camera.
     */
    public void move(char key) {
        switch (key) {
            case 'w': // Move forward
                if (mWorld.contains(getPosition(getForwardDirection()))) {
                    move(getForwardDirection());
                }
                break;
            case 'a': // Strafe left
                if (mWorld.contains(getPosition(getLeftDirection()))) {
                    move(getLeftDirection());
                }
                break;
            case 's': // Move backward
                if (mWorld.contains(getPosition(getBackwardDirection()))) {
                    move(getBackwardDirection());
                }
                break;
            case 'd': // Strafe right
                if (mWorld.contains(getPosition(getRightDirection()))) {
                    move(getRightDirection());
                }
                break;
            case 'r': // Fly up
                if (mWorld.contains(getPosition(getUpDirection()))) {
                    move(getUpDirection());
                }
                break;
            case 'f': // Fly down
                PVector position = getPosition(getDownDirection());
                if (mWorld.contains(position) && position.y >= mHeight) {
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
     * Sets the camera for the current frame ({@link PApplet#draw()} call).
     *
     * Should only be called after the World is drawn.
     */
    public void set() {
        if (mMouse.isCentred()) {
            mMouse.move();
        } else {
            mMouse.centre();
        }
        mAngle.x = mMouse.getX() * PConstants.TAU / (mSketch.width - 1);
        mAngle.y = mMouse.getY() * PConstants.QUARTER_PI * 3.0f / (mSketch.height - 1);
        mSketch.beginCamera();
        mSketch.camera(mEye.x, mEye.y, mEye.z, mCentre.x, mCentre.y, mCentre.z, mUp.x, mUp.y, mUp.z);
        mSketch.translate(mCentre.x, mCentre.y + mHeight - mWorld.getHeight(), mCentre.z);
        mSketch.rotateX(mAngle.y);
        mSketch.rotateY(mAngle.x);
        mSketch.translate(mEye.x, mEye.y, mEye.z);
        mSketch.endCamera();
    }

    @VisibleForTesting
    static class Pointer {

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

        @VisibleForTesting
        void setWrapped(boolean wrapped) {
            mWrapped = wrapped;
        }

        @VisibleForTesting
        boolean isWrapped() {
            return mWrapped;
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
}
