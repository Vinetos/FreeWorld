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
import org.freeworld.client.utils.Location;

import java.util.Collection;
import java.util.LinkedHashMap;

public class World{

    private final String name;
    private final LinkedHashMap<String, Chunk> chunks = new LinkedHashMap<>();

    public World(String name){
        this.name = name;

        /*
         * TODO: Provisoir
         */
        loadChunk(new Location(this, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
    }

    public String getName() {
        return name;
    }

    /*
     * Get chunks loaded.
     */
    public Collection<Chunk> getChunks(){
        return chunks.values();
    }

    /*
     * Load a chunk with a player location.
     */
    public void loadChunk(Location location){
        Chunk chunk = new Chunk("id chunk", location);
        chunk.populate();
        chunks.put(chunk.getId(), chunk);
    }

    /*
     * Get a block with a location.
     */
    public Block getBlock(Location location){
        return null;
    }

    /*
     * Get a block with ints
     */
    public Block getBlock(int x, int y, int z){

        return chunks.get("id chunk").getBlock(x, y, z);
    }

    /*
     * Set a block width a location
     */
    public void setBlock(Location location){

    }

    /*
     * Set a block with ints
     */
    public void setBlock(int x, int y, int z) {

    }

    /*
     * Save World.
     */
    public void save(){

    }
}
