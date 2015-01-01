package com.adjl.firstpersoncamera;

import processing.core.PVector;

/**
 * @author adjl
 */
public interface World {

    public boolean contains(PVector position);

    public void draw();
}
