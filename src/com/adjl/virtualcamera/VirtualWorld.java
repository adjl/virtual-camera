package com.adjl.virtualcamera;

import processing.core.PVector;

/**
 * Virtual world.
 *
 * World where the {@link VirtualCamera} moves in.
 *
 * @author Helena Josol
 */
public interface VirtualWorld {

    /**
     * @return Height of the VirtualWorld.
     */
    public float getHeight();

    /**
     * @param position Position to check if within the VirtualWorld.
     * @return Whether the position is within the world.
     */
    public boolean isWithin(PVector position);

    /**
     * Draws the VirtualWorld for the current frame (PApplet.draw() call).
     *
     * Should be called before the {@link VirtualCamera} is drawn.
     */
    public void draw();
}
