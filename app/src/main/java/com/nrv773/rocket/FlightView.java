package com.nrv773.rocket;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class FlightView extends GLSurfaceView {
    private final FlightRenderer mRenderer;

    public FlightView(Context context){
        super(context);

        // Create an Open GL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new FlightRenderer();

        // Set the Renderer for drawing on the SLSurfaceView
        setRenderer(mRenderer);
    }
}
