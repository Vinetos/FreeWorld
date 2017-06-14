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

public class Vector3d{

    protected double x, y, z;

    public Vector3d(){
        this(0.0, 0.0, 0.0);
    }

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public Vector3d setX(double x) {
        this.x = x;
        return this;
    }

    public Vector3d addX(double x) {
        this.x += x;
        return this;
    }

    public Vector3d removeX(double x) {
        this.x -= x;
        return this;
    }

    public Vector3d multiplyX(double x) {
        this.x *= x;
        return this;
    }

    public Vector3d divideX(double x) {
        this.x /= x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Vector3d setY(double y) {
        this.y = y;
        return this;
    }

    public Vector3d addY(double y) {
        this.y += y;
        return this;
    }

    public Vector3d removeY(double y) {
        this.y -= y;
        return this;
    }

    public Vector3d multiplyY(double y) {
        this.y *= y;
        return this;
    }

    public Vector3d divideY(double y) {
        this.y /= y;
        return this;
    }


    public final double getZ() {
        return z;
    }

    public Vector3d setZ(double z) {
        this.z = z;
        return this;
    }

    public Vector3d addZ(double z) {
        this.z += z;
        return this;
    }

    public Vector3d removeZ(double z) {
        this.z -= z;
        return this;
    }

    public Vector3d multiplyZ(double z) {
        this.z *= z;
        return this;
    }

    public Vector3d divideZ(double z) {
        this.z /= z;
        return this;
    }
}
