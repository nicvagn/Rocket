package com.nrv773.rocket;

import android.opengl.GLES20;
import static android.opengl.GLES20.*; //so we can use static final fields and static methods without writing GLES20.whatever()
import android.opengl.GLSurfaceView;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.nrv773.rocket.SizeOf.sizeof; //give c++ like sizeof function

/**
 * handle's all the openGL rendering for the flight
 */
public class FlightRenderer implements GLSurfaceView.Renderer {

    //define the array for the verticies of our bitching triangle.
    private static final float[] TRIANGLE_POSITIONS = {
                                 -0.5f, -0.5f,
                                  0.5f, 0.5f,
                                  0.5f, -0.5f
                                };

    //create a buffer and add our triangle to it
    private Buffer triangle_positions_buffer = ByteBuffer   //this is quite the hack
            .allocateDirect(TRIANGLE_POSITIONS.length * sizeof(int.class))
            .order(ByteOrder.nativeOrder()).asFloatBuffer()
            .put(TRIANGLE_POSITIONS).flip();

    /**
     * called once when the Surface is created, used to setup openGL stuffs
     */
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        // Set the background frame color
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        int[] buffer = new int[1]; //basically used as a pointer, ie: so glGenBuffers can communicate
        glGenBuffers(1, buffer, 0); //generate our buffer, 1 is number of buffers, buffer int array and 0 is offset?

        glBindBuffer(GL_ARRAY_BUFFER, buffer[0]); //sets the buffer up like an array basically say im ready to work on it
        glBufferData(GL_ARRAY_BUFFER, triangle_positions_buffer.capacity(),
                triangle_positions_buffer, GL_STATIC_DRAW );

        glEnableVertexAttribArray(0);

        // i=first attribute, size=number of components per, type=type we want these to be, stride = how far in byte to get to the next vertex, attribute offset=the offset to this attribute
        glVertexAttribPointer(0,2, GL_FLOAT, false, sizeof(float.class) * 2, 0 );

        glDrawArrays(GL_TRIANGLES, 0, 3);

    }


    /**
     * only called when the surface is changed, ie: the the size of the surface is changed.
     * this should not be called very often
     */
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        glViewport(0, 0, width, height);
    }


    /**
     * method used to draw a frame, will be called a fuck load, so must be efficient.
     */
    @Override
    public void onDrawFrame(GL10 unused){
        // Redraw background color
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
