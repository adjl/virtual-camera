package com.adjl.virtualcamera.demo;

import com.adjl.virtualcamera.SimpleWorld;
import com.adjl.virtualcamera.VirtualCamera;
import com.adjl.virtualcamera.VirtualWorld;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * {@link VirtualCamera} demo.
 *
 * Processing sketch demonstrating a virtual camera.
 *
 * @author Helena Josol
 */
public class Camera extends PApplet {

    private final float mStrokeWeight;
    private final int mBackground;
    private final int mFill;
    private final int mStroke;

    private VirtualWorld mWorld;
    private VirtualCamera mCamera;

    /**
     * Constructs the {@link VirtualCamera} demo.
     */
    public Camera() {
        mStrokeWeight = 2.0f;
        mBackground = color(0, 0, 0);
        mFill = color(0, 0, 0);
        mStroke = color(255, 255, 255);
    }

    @Override
    public void setup() {
        size(displayWidth, displayHeight, PConstants.P3D);
        rectMode(PConstants.CENTER);
        noCursor();
        fill(mFill);
        stroke(mStroke);
        strokeWeight(mStrokeWeight);
        mWorld = new SimpleWorld(this, 200.0f, 100.0f, 200.0f);
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

    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", com.adjl.virtualcamera.demo.Camera.class.getName() });
    }
}
