package com.nrv773.rocket;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class FlightActivity extends Activity {
    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Create a GLSurfaceView instance and set it
        //as the ContentView for this Activity.
        mGLView = new FlightView(this);
        setContentView(mGLView);
    }
}