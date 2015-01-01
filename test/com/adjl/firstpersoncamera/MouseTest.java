package com.adjl.firstpersoncamera;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.adjl.firstpersoncamera.Camera.Mouse;

import org.junit.Before;
import org.junit.Test;

import processing.core.PApplet;

/**
 * Tests {@link Mouse}.
 *
 * @author adjl
 */
public class MouseTest {

    private PApplet mSketch;
    private Mouse mMouse;

    @Before
    public void setUp() {
        mSketch = new PApplet();
        mMouse = new Mouse(mSketch, 2);
    }

    @Test
    public void testGetX_ZeroAtStart() {
        assertEquals(0, mMouse.getX());
    }

    @Test
    public void testGetX_ChangedAtRun() {
        mSketch.mouseX = 1;
        assertEquals(1, mMouse.getX());
    }

    @Test
    public void testGetY_HalfHeighAtStart() {
        mSketch.height = 200;
        assertEquals(100, mMouse.getY());
    }

    @Test
    public void testGetY_HalfHeightMinusChangeAtRun() {
        mSketch.height = 200;
        mSketch.mouseY = 50;
        assertEquals(50, mMouse.getY());
    }

    @Test
    public void testIsCentred_NotCentredAtStart() {
        assertFalse(mMouse.isCentred());
    }

    @Test
    public void testIsCentred_NotCentredIfNotEnoughAttempts() {
        mMouse.centre();
        assertFalse(mMouse.isCentred());
    }

    @Test
    public void testIsCentred_CentredIfEnoughAttempts() {
        mMouse.centre();
        mMouse.centre();
        assertTrue(mMouse.isCentred());
    }

    @Test
    public void testCentre() {
        mMouse.centre();
        assertEquals(1, mMouse.getAttempts());
    }

    @Test
    public void testMove_NotWrappedIfNotAtEdges() {
        mSketch.width = 200;
        mSketch.mouseX = 1;

        mMouse.setWrapped(true);
        mMouse.move();
        assertFalse(mMouse.isWrapped());

        mMouse.setWrapped(false);
        mMouse.move();
        assertFalse(mMouse.isWrapped());
    }

    @Test
    public void testMove_WrappedIfAtEdges() {
        mSketch.width = 200;
        mSketch.mouseX = 0;

        mMouse.setWrapped(false);
        mMouse.move();
        assertTrue(mMouse.isWrapped());

        mMouse.setWrapped(true);
        mMouse.move();
        assertTrue(mMouse.isWrapped());
    }
}
