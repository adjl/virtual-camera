package com.adjl.virtualcamera;

import processing.core.PVector;

/**
 * @author adjl
 */
public interface VirtualWorld {

    public float getHeight();

    public boolean contains(PVector position);

    public void draw();
}
