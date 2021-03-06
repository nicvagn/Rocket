package com.nrv773.rocket;
import android.opengl.GLES20;

import static android.opengl.GLES20.*; //so we can use static final fields and static methods without writing GLES20.whatever()

/**
 * Create openGL shaderPrograms
 */
public final class ShaderCompiler {

    /**
     * Compile a shader from source
     * @param type type of shader to be compiled
     * @param Source source of shader
     * @return shader id of the compiled shader
     */
    private static int CompileShader(int type, String Source){
        int shaderId = glCreateShader(type);    //create a new shader and get it's id
        glShaderSource(shaderId, Source);       //associate shader id with source
        glCompileShader(shaderId);              //compile the shader via it's id

        //TODO: error handling for shader source code is a hack, fix it
        int[] result = new int[1]; //java pointer rip
        glGetShaderiv(shaderId, GL_COMPILE_STATUS, result, 0); //check the compile status of our shader

        if(result[0] == GL_FALSE) {  //if it failed to compile print to console and delete shader
            glDeleteShader(shaderId);   //if compalation failed, delete the shader
            throw new RuntimeException("Compilation failed : " + GLES20.glGetShaderInfoLog(shaderId));
        }

        return shaderId;
    }

    /**
     * create shader program from source code
     * @param vertexShaderCode code for vertex shader in String
     * @param fragmentShaderCode code for fragment shader in String
     * @return shader program id
     */
    public static int CreateShaderProgram(String vertexShaderCode, String fragmentShaderCode){
        int program = glCreateProgram(); //returns an int representing our program
        int vertexShader = CompileShader(GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = CompileShader(GL_FRAGMENT_SHADER, fragmentShaderCode);

        glAttachShader(program, vertexShader);  //attach both shaders to our program
        glAttachShader(program, fragmentShader);

        glLinkProgram(program);  //basically create the executable's for the vertex processor,
                                 //linking the shadder code to the program.
        glValidateProgram(program);  //validate the created program to check if it'll work with the current state

        glDeleteShader(vertexShader);   //we can now delete the intermediate shader's, what we want is already in the program
        glDeleteShader(fragmentShader);
        return program;
    }
}
