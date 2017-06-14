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

public class Vector2f extends Vector {

    protected float y;

    public Vector2f(float x, float y) {
        super(x);
        this.y = y;
    }

    @Override
    public Vector2f setX(float x) {
        return (Vector2f) super.setX(x);
    }

    @Override
    public Vector2f addX(float x) {
        return (Vector2f) super.addX(x);
    }

    @Override
    public Vector2f removeX(float x) {
        return (Vector2f) super.removeX(x);
    }

    @Override
    public Vector2f multiplyX(float x) {
        return (Vector2f) super.multiplyX(x);
    }

    @Override
    public Vector2f divideX(float x) {
        return (Vector2f) super.divideX(x);
    }

    public final float getY() {
        return y;
    }

    public Vector2f setY(float y) {
        this.y = y;
        return this;
    }

    public Vector2f addY(float y) {
        this.y += y;
        return this;
    }

    public Vector2f removeY(float y) {
        this.y -= y;
        return this;
    }

    public Vector2f multiplyY(float y) {
        this.y *= y;
        return this;
    }

    public Vector2f divideY(float y) {
        this.y /= y;
        return this;
    }
}
