package com.nrv773.rocket;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FlightActivity extends AppCompatActivity {
    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Create a GLSurfaceView instance and set it
        //as the ContentView for this Activity.
        mGLView = new FlightView(this);
        setContentView(mGLView);
    }
}