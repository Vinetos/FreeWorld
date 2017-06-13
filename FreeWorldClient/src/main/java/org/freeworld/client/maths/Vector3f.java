/**
 * ==============================================================================
 *  _   _               _                       ____   _
 * | \ | |  ___  _   _ | |_  _ __  ___   _ __  / ___| | |_  __ _  _ __  ___
 * |  \| | / _ \| | | || __|| '__|/ _ \ | '_ \ \___ \ | __|/ _` || '__|/ __|
 * | |\  ||  __/| |_| || |_ | |  | (_) || | | | ___) || |_| (_| || |   \__ \
 * |_| \_| \___| \__,_| \__||_|   \___/ |_| |_||____/  \__|\__,_||_|   |___/
 *
 * ==============================================================================
 *            _    _______   __________________  _____
 *            | |  / /  _/ | / / ____/_  __/ __ \/ ___/
 *            | | / // //  |/ / __/   / / / / / /\__ \
 *            | |/ // // /|  / /___  / / / /_/ /___/ /
 *            |___/___/_/ |_/_____/ /_/  \____//____/
 *
 * ==============================================================================
 *
 * FreeWorld game
 * Copyright (c) NeutronStars & Vinetos Software 2017,
 * By NeutronStars, juin 2017
 *
 * ==============================================================================
 *
 * This file is part of FreeWorld game.
 *
 * FreeWorld is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *==============================================================================
 */
package org.freeworld.client.maths;

public class Vector3f extends Vector2f{

    protected float z;

    public Vector3f(float x, float y, float z){
        super(x, y);
        this.z = z;
    }

    @Override
    public Vector3f setX(float x) {
        return (Vector3f) super.setX(x);
    }

    @Override
    public Vector3f addX(float x) {
        return (Vector3f) super.addX(x);
    }

    @Override
    public Vector3f removeX(float x) {
        return (Vector3f) super.removeX(x);
    }

    @Override
    public Vector3f multiplyX(float x) {
        return (Vector3f) super.multiplyX(x);
    }

    @Override
    public Vector3f divideX(float x) {
        return (Vector3f) super.divideX(x);
    }

    @Override
    public Vector3f setY(float y) {
        return (Vector3f) super.setY(y);
    }

    @Override
    public Vector3f addY(float y) {
        return (Vector3f) super.addY(y);
    }

    @Override
    public Vector3f removeY(float y) {
        return (Vector3f) super.removeY(y);
    }

    @Override
    public Vector3f multiplyY(float y) {
        return (Vector3f) super.multiplyY(y);
    }

    @Override
    public Vector3f divideY(float y) {
        return (Vector3f) super.divideY(y);
    }


    public final float getZ() {
        return z;
    }

    public Vector3f setZ(float z) {
        this.z = z;
        return this;
    }

    public Vector3f addZ(float z){
        this.z += z;
        return this;
    }

    public Vector3f removeZ(float z){
        this.z -= z;
        return this;
    }

    public Vector3f multiplyZ(float z){
        this.z *= z;
        return this;
    }

    public Vector3f divideZ(float z){
        this.z /= z;
        return this;
    }
}
