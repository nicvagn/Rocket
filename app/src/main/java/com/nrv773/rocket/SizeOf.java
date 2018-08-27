package com.nrv773.rocket;

import java.nio.FloatBuffer;

/**
 * class to get the size of primative data types e.g. byte, int, short, double, float
 * char, short in a method like sizeof()
 */
public final class SizeOf {
    /**
     * returns the size of primitive data types in bytes based on hardcoded data.
     * throws null pointer exception if given a null
     */
    public static int sizeof(Class dataType){
        if(dataType == null){
            throw new NullPointerException();
        }
        if(dataType == byte.class || dataType == Byte.class){
            return 1;
        }
        if(dataType == short.class || dataType == Short.class){
            return 2;
        }
        if(dataType == char.class || dataType == Character.class){
            return 2;
        }
        if(dataType == int.class || dataType == Integer.class){
            return 4;
        }
        if(dataType == long.class || dataType == Long.class){
            return 8;
        }
        if(dataType == float.class || dataType == Float.class){
            return 4;
        }
        if(dataType == double.class || dataType == Double.class){
            return 8;
        }
        return 4; //default for 32-bit memory pointer.
    }

}
