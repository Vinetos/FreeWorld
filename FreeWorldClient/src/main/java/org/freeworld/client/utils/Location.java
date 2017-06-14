/*
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
package org.freeworld.client.utils;

import org.freeworld.client.block.Block;
import org.freeworld.client.maths.Vector5f;
import org.freeworld.client.world.World;

public class Location{

    private World world;
    private Vector5f vector5f;

    public Location(Location location){
        this(location.world, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public Location(World world, float x, float y, float z, float yaw, float pitch){
        this.world = world;
        this.vector5f = new Vector5f(x, y, z, yaw, pitch);
    }

    public World getWorld() {
        return world;
    }

    public Block getBlock(){
        return world.getBlock(getBlockX(), getBlockY(), getBlockZ());
    }

    public int getBlockX(){
        return (int) vector5f.getX();
    }

    public int getBlockY(){
        return (int) vector5f.getY();
    }

    public int getBlockZ(){
        return (int) vector5f.getZ();
    }

    public float getX(){
        return vector5f.getX();
    }

    public float getY(){
        return vector5f.getY();
    }

    public float getZ(){
        return vector5f.getZ();
    }

    public float getYaw(){
        return vector5f.getYaw();
    }

    public float getPitch(){
        return vector5f.getPitch();
    }

    public Location set(int x, int y, int z){
        vector5f.setX(x).setY(y).setZ(z);
        return this;
    }

    public Location add(int x, int y, int z){
        return clone().set(x, y, z);
    }

    public Location clone(){
        return new Location(this);
    }
}
