package com.adjl.virtualcamera.demo;

import com.adjl.virtualcamera.VirtualCamera;
import com.adjl.virtualcamera.VirtualWorld;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * {@link VirtualCamera} demo.
 *
 * Processing sketch demonstrating a virtual camera.
 *
 * @author Helena Josol
 */
public class Camera extends PApplet {

    private final int mBackground;
    private final int mStroke;
    private final int mStrokeWeight;

    private VirtualWorld mWorld;
    private VirtualCamera mCamera;

    /**
     * Constructs the {@link VirtualCamera} demo.
     */
    public Camera() {
        mBackground = color(0, 0, 0);
        mStroke = color(255, 255, 255);
        mStrokeWeight = 2;
    }

    @Override
    public void setup() {
        size(displayWidth, displayHeight, PConstants.P3D);
        noCursor();
        noFill();
        stroke(mStroke);
        strokeWeight(mStrokeWeight);
        mWorld = new Room(this);
        mCamera = new VirtualCamera(this, mWorld, 50.0f, 3.0f);
    }

    @Override
    public void draw() {
        background(mBackground);
        mWorld.draw();
        mCamera.set();
    }

    @Override
    public void keyPressed() {
        mCamera.move(key);
    }

    private class Room implements VirtualWorld {

        private final PApplet mSketch;
        private final float mWidth;
        private final float mHeight;
        private final float mDepth;

        Room(PApplet sketch) {
            mSketch = sketch;
            mWidth = 200.0f;
            mHeight = 100.0f;
            mDepth = 200.0f;
        }

        @Override
        public float getHeight() {
            return mHeight;
        }

        @Override
        public boolean isWithin(PVector position) {
            return (position.x >= -mWidth / 2.0f) && (position.x < mWidth / 2.0f)
                    && (position.y >= 0.0f) && (position.y < mHeight)
                    && (position.z >= -mDepth / 2.0f) && (position.z < mDepth / 2.0f);
        }

        @Override
        public void draw() {
            mSketch.box(mWidth, mHeight, mDepth);
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", com.adjl.virtualcamera.demo.Camera.class.getName() });
    }
}
