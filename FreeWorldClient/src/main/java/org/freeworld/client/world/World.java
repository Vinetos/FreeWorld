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
import org.freeworld.client.entity.Entity;
import org.freeworld.client.utils.Location;

import java.util.Collection;
import java.util.LinkedHashMap;

public class World {

    private final String name;
    private final LinkedHashMap<String, Chunk> chunks = new LinkedHashMap<>();

    public World(String name) {
        this.name = name;

        /*
         * TODO: Provisoir
         */
        for (int x = -(16 * 5); x < 16 * 5; x += 16) {
            for (int z = -(16 * 5); z < 16 * 5; z += 16) {
                loadChunk(new Location(this, x, 0.0f, z, 0.0f, 0.0f));
            }
        }
    }

    public String getName() {
        return name;
    }

    /*
     * Get chunks loaded.
     */
    public Collection<Chunk> getChunks() {
        return chunks.values();
    }

    /*
     * Load a chunk with a player location.
     */
    public void loadChunk(Location location) {
        Chunk chunk = new Chunk(location);
        chunk.populate();
        System.out.println("Chunk \"" + chunk.getId() + "\" generated.");
        chunks.put(chunk.getId(), chunk);
    }

    /*
     * Get a block with a location.
     */
    public Block getBlock(Location location) {
        return this.getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /*
     * Get a block with ints
     */
    public Block getBlock(int x, int y, int z) {
        Chunk chunk = getChunk(x, z);
        return chunk != null ? chunk.getBlock((x >= 0) ? Math.abs(x % 16) : 15 - Math.abs(x % 16), y, z >= 0 ? Math.abs(z % 16) : 15 - Math.abs(z % 16)) : null;
    }

    /*
     * Set a block width a location
     */
    public void setBlock(Material material, Location location) {
        this.setBlock(material, location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /*
     * Set a block with ints
     */
    public void setBlock(Material material, int x, int y, int z) {
        Chunk chunk = getChunk(x, z);
        if (chunk != null) chunk.setBlock(material, Math.abs(x % 16), y, Math.abs(z % 16));
    }

    public Chunk getChunk(Location location){
        return getChunk(location.getBlockX(), location.getBlockZ());
    }

    public Chunk getChunk(int x, int z){
        return chunks.get((x >> 4) + "_" + (z >> 4));
    }

    public <T extends Entity> T spawnEntity(Class<T> clazz, Location location){
        return spawnEntity(clazz, location, false);
    }

    public <T extends Entity> T spawnEntity(Class<T> clazz, Location location, boolean up){
        Chunk chunk = getChunk(location);
        return chunk != null ? chunk.spawnEntity(clazz, location, up) : null;
    }

    public void updateWorld(){
        chunks.values().forEach(chunk -> chunk.getEntities().forEach(entity -> entity.update()));
        chunks.values().forEach(chunk -> chunk.updateEntityQueue());
    }

    /*
     * Save World.
     */
    public void save() {

    }
}
