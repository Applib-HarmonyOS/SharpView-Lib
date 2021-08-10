package com.zhaoxing.view.sharpview;

/**
 * SharpView.
 */
public interface SharpView {

    SharpViewRenderProxy getRenderProxy();

    /**
     * Indicates arrow direction of sharpview.
     */
    enum ArrowDirection {
        LEFT, TOP, RIGHT, BOTTOM
    }
}
