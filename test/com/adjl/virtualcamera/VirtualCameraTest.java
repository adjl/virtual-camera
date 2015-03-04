package com.adjl.virtualcamera;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Tests {@link VirtualCamera}.
 *
 * @author Adei Josol
 */
public class VirtualCameraTest {

    private static final float DELTA = 1.0e-6f;

    private PApplet mSketch;
    private TestVirtualWorld mWorld;
    private VirtualCamera mCamera;

    @Before
    public void setUp() {
        mSketch = new PApplet();
        mSketch.init(); // Needed when PApplet is run by other code; see PApplet#sketchPath(String)
        mWorld = new TestVirtualWorld(true);
        mCamera = new VirtualCamera(mSketch, mWorld, 50.0f, 3.0f);
    }

    @Test
    public void testMove_GivenKeyCanMoveForward() {
        mCamera.move('w');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(3.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(2.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCannotMoveForward() {
        mWorld.setContains(false);
        mCamera.move('w');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCanMoveLeft() {
        mCamera.move('a');

        PVector eye = mCamera.getEye();
        assertEquals(3.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(3.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCannotMoveLeft() {
        mWorld.setContains(false);
        mCamera.move('a');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCanMoveBackward() {
        mCamera.move('s');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(-3.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-4.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCannotMoveBackward() {
        mWorld.setContains(false);
        mCamera.move('s');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCanMoveRight() {
        mCamera.move('d');

        PVector eye = mCamera.getEye();
        assertEquals(-3.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(-3.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCannotMoveRight() {
        mWorld.setContains(false);
        mCamera.move('d');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCanMoveUp() {
        mCamera.move('r');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(53.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(53.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCannotMoveUp() {
        mWorld.setContains(false);
        mCamera.move('r');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCanMoveDown() {
        mCamera.move('f');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCannotMoveDown() {
        mWorld.setContains(false);
        mCamera.move('f');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyCannotMoveDownBelowHeight() {
        mCamera.move('f');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    @Test
    public void testMove_GivenKeyNoDefaultMovement() {
        mCamera.move('z');

        PVector eye = mCamera.getEye();
        assertEquals(0.0f, eye.x, DELTA);
        assertEquals(50.0f, eye.y, DELTA);
        assertEquals(0.0f, eye.z, DELTA);

        PVector centre = mCamera.getCentre();
        assertEquals(0.0f, centre.x, DELTA);
        assertEquals(50.0f, centre.y, DELTA);
        assertEquals(-1.0f, centre.z, DELTA);
    }

    private class TestVirtualWorld implements VirtualWorld {

        private boolean mContains;

        TestVirtualWorld(boolean contains) {
            mContains = contains;
        }

        void setContains(boolean contains) {
            mContains = contains;
        }

        @Override
        public float getHeight() {
            return 0.0f;
        }

        @Override
        public boolean isWithin(PVector position) {
            return mContains;
        }

        @Override
        public void draw() { // Purposely empty
        }
    }
}
