/**
 * ==============================================================================
 * _   _               _                       ____   _
 * | \ | |  ___  _   _ | |_  _ __  ___   _ __  / ___| | |_  __ _  _ __  ___
 * |  \| | / _ \| | | || __|| '__|/ _ \ | '_ \ \___ \ | __|/ _` || '__|/ __|
 * | |\  ||  __/| |_| || |_ | |  | (_) || | | | ___) || |_| (_| || |   \__ \
 * |_| \_| \___| \__,_| \__||_|   \___/ |_| |_||____/  \__|\__,_||_|   |___/
 * <p>
 * ==============================================================================
 * _    _______   __________________  _____
 * | |  / /  _/ | / / ____/_  __/ __ \/ ___/
 * | | / // //  |/ / __/   / / / / / /\__ \
 * | |/ // // /|  / /___  / / / /_/ /___/ /
 * |___/___/_/ |_/_____/ /_/  \____//____/
 * <p>
 * ==============================================================================
 * <p>
 * FreeWorld game
 * Copyright (c) NeutronStars & Vinetos Software 2017,
 * By NeutronStars, juin 2017
 * <p>
 * ==============================================================================
 * <p>
 * This file is part of FreeWorld game.
 * <p>
 * FreeWorld is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * <p>
 * ==============================================================================
 */
package org.freeworld.client.maths;

public class Vector3f<T extends Vector3f<T>> extends Vector2f<Vector3f<?>>{

    protected float z;

    public Vector3f(float x, float y, float z){
        super(x, y);
        this.z = z;
    }

    public final float getZ() {
        return z;
    }

    public final T setZ(float z) {
        this.z = z;
        return (T) this;
    }

    public final T addZ(float z){
        this.z += z;
        return (T) this;
    }

    public final T removeZ(float z){
        this.z -= z;
        return (T) this;
    }

    public final T multiplyZ(float z){
        this.z *= z;
        return (T) this;
    }

    public final T divideZ(float z){
        this.z /= z;
        return (T) this;
    }
}
