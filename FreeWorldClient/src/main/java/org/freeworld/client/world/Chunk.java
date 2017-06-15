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
package org.freeworld.client.world;

import org.freeworld.client.block.Block;
import org.freeworld.client.block.Material;
import org.freeworld.client.utils.BlockRegistry;
import org.freeworld.client.utils.Location;

import java.util.Random;

public class Chunk {

    private final Block[][][] blocks = new Block[16][32][16];
    private final int positionX, positionZ;
    private final Location location;
    private final String id;

    private boolean update;

    public Chunk(Location location) {
        this.location = location;
        positionX = (location.getBlockX() >> 4);
        positionZ = (location.getBlockZ() >> 4);
        id = positionX + "_" + positionZ;
    }

    public void populate() {
        final Random random = new Random();
        for (byte x = 0; x < 16; x++) {
            for (byte y = 0; y < 32; y++) {
                for (byte z = 0; z < 16; z++) {
                        blocks[x][y][z] = y < 3
                                ? random.nextInt(2) == 0
                                        ? BlockRegistry.getBlock(Material.STONE)
                                        : BlockRegistry.getBlock(Material.GRASS)
                                : BlockRegistry.getBlock(Material.AIR);
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public World getWorld() {
        return location.getWorld();
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public Block getBlock(int x, int y, int z) {
        return (x < 0 || x > 15 || y < 0 || y > 31 || z < 0 || z > 15) ? null : blocks[x][y][z];
    }

    public Block[][][] getBlocks() {
        return blocks;
    }

    public void setBlock(Material material, int x, int y, int z) {
        blocks[x][y][z] = BlockRegistry.getBlock(material);
        update = false;
    }
}
