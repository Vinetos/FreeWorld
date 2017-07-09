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
package org.freeworld.client.render;

import org.freeworld.client.block.Block;
import org.freeworld.client.maths.Vector3f;
import org.freeworld.client.maths.Vector4f;
import org.freeworld.client.utils.Location;
import org.freeworld.client.world.World;
import org.lwjgl.opengl.GL11;

/**
 * Graphics Render Blocks.
 */
public class BlockRenderer {

    /*
     * Block Color.
     */
    private final Vector4f color;

    public BlockRenderer(Vector4f color) {
        this.color = color;
    }

    public void drawRayCast(World world, Vector3f vector3f){
        drawQuads(new Location(world, vector3f.getX(), vector3f.getY(), vector3f.getZ(), 0.0f, 0.0f));
    }

    /**
     * Draws Blocks
     * @param location > Block Location
     */
    public void drawQuads(Location location) {
        if (color == null) return;

        GL11.glColor4f(color.getX(), color.getY(), color.getZ(), color.getYaw());

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        /*
         * Block Top
         */
        Block top = location.add(0, 1, 0).getBlock();
        if (top == null || top.isTransparent()) {
            this.setTopData(x, y, z, 1.0f, 1.0f, 1.0f);
        }

        /*
         * Block Bottom.
         */
        Block bottom = location.add(0, -1, 0).getBlock();
        if (bottom == null || bottom.isTransparent()) {
            this.setBottomData(x, y, z, 1.0f, 1.0f, 1.0f);
        }

        /*
         * Block Side
         */
        GL11.glColor4f(color.getX() * 0.8f, color.getY() * 0.8f, color.getZ() * 0.8f, color.getYaw());
        Block side_1 = location.add(0, 0, -1).getBlock();
        if (side_1 == null || side_1.isTransparent()) {
            setSideData(x + 1.0f, y, z, x, y + 1.0f, z);
        }

        Block side_2 = location.add(0, 0, 1).getBlock();
        if (side_2 == null || side_2.isTransparent()) {
            setSideData(x, y, z + 1.0f, x + 1.0f, y + 1.0f, z + 1.0f);
        }

        GL11.glColor4f(color.getX() * 0.7f, color.getY() * 0.7f, color.getZ() * 0.7f, color.getYaw());

        Block side_3 = location.add(1, 0, 0).getBlock();
        if (side_3 == null || side_3.isTransparent()) {
            setSideData(x + 1.0f, y, z + 1.0f, x + 1.0f, y + 1.0f, z);
        }

        Block side_4 = location.add(-1, 0, 0).getBlock();
        if (side_4 == null || side_4.isTransparent()) {
            setSideData(x, y, z, x, y + 1.0f, z + 1.0f);
        }
    }

    public void setTopData(float x, float y, float z, float lx, float ly, float lz) {
        GL11.glVertex3f(x + lx, y + ly, z);
        GL11.glVertex3f(x, y + ly, z);
        GL11.glVertex3f(x, y + ly, z + lz);
        GL11.glVertex3f(x + lx, y + ly, z + lz);
    }

    public void setBottomData(float x, float y, float z, float lx, float ly, float lz) {
        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x + lx, y, z);
        GL11.glVertex3f(x + lx, y, z + lz);
        GL11.glVertex3f(x, y, z + lz);
    }

    public void setSideData(float x0, float y0, float z0, float x1, float y1, float z1) {
        GL11.glVertex3f(x0, y0, z0);
        GL11.glVertex3f(x1, y0, z1);
        GL11.glVertex3f(x1, y1, z1);
        GL11.glVertex3f(x0, y1, z0);
    }
}
