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

public class Vector5f extends Vector4f {

    protected float pitch;

    public Vector5f(float x, float y, float z, float yaw, float pitch) {
        super(x, y, z, yaw);
        this.pitch = pitch;
    }

    public Vector3f getDirection(){
        return new Vector3f(((float) Math.cos(Math.toRadians(pitch - 90)))*((float) Math.cos(Math.toRadians(-yaw))), (float) Math.sin(Math.toRadians(-yaw)), ((float) Math.sin(Math.toRadians(pitch - 90)))*((float) Math.cos(Math.toRadians(-yaw)))).normalize();
    }

    @Override
    public Vector5f setX(float x) {
        return (Vector5f) super.setX(x);
    }

    @Override
    public Vector5f addX(float x) {
        return (Vector5f) super.addX(x);
    }

    @Override
    public Vector5f removeX(float x) {
        return (Vector5f) super.removeX(x);
    }

    @Override
    public Vector5f multiplyX(float x) {
        return (Vector5f) super.multiplyX(x);
    }

    @Override
    public Vector5f divideX(float x) {
        return (Vector5f) super.divideX(x);
    }

    @Override
    public Vector5f setY(float y) {
        return (Vector5f) super.setY(y);
    }

    @Override
    public Vector5f addY(float y) {
        return (Vector5f) super.addY(y);
    }

    @Override
    public Vector5f removeY(float y) {
        return (Vector5f) super.removeY(y);
    }

    @Override
    public Vector5f multiplyY(float y) {
        return (Vector5f) super.multiplyY(y);
    }

    @Override
    public Vector5f divideY(float y) {
        return (Vector5f) super.divideY(y);
    }

    @Override
    public Vector5f setZ(float z) {
        return (Vector5f) super.setZ(z);
    }

    @Override
    public Vector5f addZ(float z) {
        return (Vector5f) super.addZ(z);
    }

    @Override
    public Vector5f removeZ(float z) {
        return (Vector5f) super.removeZ(z);
    }

    @Override
    public Vector5f multiplyZ(float z) {
        return (Vector5f) super.multiplyZ(z);
    }

    @Override
    public Vector5f divideZ(float z) {
        return (Vector5f) super.divideZ(z);
    }

    @Override
    public Vector5f setYaw(float yaw) {
        return (Vector5f) super.setYaw(yaw);
    }

    @Override
    public Vector5f addYaw(float yaw) {
        return (Vector5f) super.addYaw(yaw);
    }

    @Override
    public Vector5f removeYaw(float yaw) {
        return (Vector5f) super.removeYaw(yaw);
    }

    @Override
    public Vector5f multiplyYaw(float yaw) {
        return (Vector5f) super.multiplyYaw(yaw);
    }

    @Override
    public Vector5f divideYaw(float yaw) {
        return (Vector5f) super.divideYaw(yaw);
    }

    public float getPitch() {
        return pitch;
    }

    public final Vector5f setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public final Vector5f addPitch(float pitch) {
        this.pitch += pitch;
        return this;
    }

    public final Vector5f removePitch(float pitch) {
        this.pitch -= pitch;
        return this;
    }

    public final Vector5f multiplyPitch(float pitch) {
        this.pitch *= pitch;
        return this;
    }

    public final Vector5f dividePitch(float pitch) {
        this.pitch /= pitch;
        return this;
    }
}
