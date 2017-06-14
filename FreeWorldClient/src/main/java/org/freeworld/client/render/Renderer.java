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
package org.freeworld.client.render;

import org.freeworld.client.block.Block;
import org.freeworld.client.block.Material;
import org.freeworld.client.maths.Vector4f;
import org.freeworld.client.utils.Location;
import org.freeworld.client.world.Chunk;
import org.freeworld.client.world.World;
import org.lwjgl.opengl.GL11;

import java.util.EnumMap;
import java.util.LinkedHashMap;

/*
 * Graphic Render.
 */
public class Renderer {

    private static final EnumMap<Material, BlockRenderer> rendererBlocks = new EnumMap<>(Material.class);

    private static final LinkedHashMap<String, Integer> chunks = new LinkedHashMap<>();

    public static void registerRenderBlocks(){
        rendererBlocks.put(Material.AIR, new BlockRenderer(null));
        rendererBlocks.put(Material.STONE, new BlockRenderer(new Vector4f(0.5f, 0.5f, 0.5f, 1.0f)));

        System.out.println("register render complited.");
    }

    public static void renderWorld(World world){
        if(world == null) return;
        world.getChunks().forEach(Renderer::renderChunk);
    }

    private static void renderChunk(Chunk chunk){
        if(!chunk.isUpdate()){
            if(chunks.containsKey(chunk.getId())) GL11.glDeleteLists(chunks.get(chunk.getId()), 1);
            else chunks.put(chunk.getId(), GL11.glGenLists(1));

            GL11.glNewList(chunks.get(chunk.getId()), GL11.GL_COMPILE);
            GL11.glBegin(GL11.GL_QUADS);
                Block[][][] blocks = chunk.getBlocks();
                for(int x = 0; x < 16; x++)
                    for(int y = 0; y < 32; y++)
                        for (int z = 0; z < 16; z++)
                            renderBlock(blocks[x][y][z], chunk.getLocation().clone().add(x, y, z));
            GL11.glEnd();
            GL11.glEndList();

            chunk.setUpdate(true);
        }

        // Draw the chunk.
        GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glCullFace(GL11.GL_BACK);
                GL11.glCallList(chunks.get(chunk.getId()));
        GL11.glDisable(GL11.GL_CULL_FACE);
    }


    /*
     * Render Blocks.
     */
    public static void renderBlock(Block block, Location location){
        rendererBlocks.get(block.getType()).drawQuads(location);
    }
}
