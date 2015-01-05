package com.adjl.firstpersoncamera;

import processing.core.PApplet;
import processing.core.PVector;

class Room implements World {

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
    public boolean contains(PVector position) {
        return (position.x >= -mWidth / 2.0f) && (position.x < mWidth / 2.0f)
                && (position.y >= 0.0f) && (position.y < mHeight) && (position.z >= -mDepth / 2.0f)
                && (position.z < mDepth / 2.0f);
    }

    @Override
    public void draw() {
        mSketch.pushMatrix();
        mSketch.translate(0.0f, -mHeight / 2.0f, 0.0f);
        mSketch.box(mWidth, mHeight, mDepth);
        mSketch.popMatrix();
    }
}
