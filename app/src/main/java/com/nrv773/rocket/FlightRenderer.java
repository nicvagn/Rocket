package com.nrv773.rocket;

import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class FlightRenderer implements GLSurfaceView.Renderer {

    //define the array for the verticies of our bitching triangle.
    private static final float[] TRIANGLE_POSITIONS = {
                                 -0.5f, -0.5f,
                                  0.5f, 0.5f,
                                  0.5f, -0.5f
                                };


    private Buffer triangle_positions_buffer = ByteBuffer   //this is quite the hack
            .allocateDirect(TRIANGLE_POSITIONS.length * 4)
            .order(ByteOrder.nativeOrder()).asFloatBuffer()
            .put(TRIANGLE_POSITIONS).flip();


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        int[] buffer = new int[1]; //basically used as a pointer, ie: so glGenBuffers can communicate
        GLES20.glGenBuffers(1, buffer, 0); //generate our buffer, 1 is number of buffers, buffer int array and 0 is offset?

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffer[0]); //sets the buffer up like an array basically say im ready to work on it
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, triangle_positions_buffer.capacity(),
                triangle_positions_buffer, GLES20.GL_STATIC_DRAW );

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }
    public void onDrawFrame(GL10 unused){
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}
