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
package org.freeworld.client;

import org.freeworld.client.block.Block;
import org.freeworld.client.entity.PlayerEntity;
import org.freeworld.client.maths.Vector5f;
import org.freeworld.client.render.Renderer;
import org.freeworld.client.utils.Location;
import org.freeworld.client.world.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;

public final class FreeWorld{

    private static FreeWorld freeWorld;

    private final String name = "FreeWorld", version = "Alpha 140617", title = String.format("%1$s %2$s", name, version);

    private final World world;
    private final PlayerEntity player;
    private boolean running;

    private FreeWorld(){
        Block.registerBlocks();
        Renderer.registerRenderBlocks();

        this.world = new World("world");
        player = world.spawnEntity(PlayerEntity.class, new Location(world, -0.0f, 0.0f, 0.0f, 0.0f, 0.0f), true);
    }

    public static FreeWorld getFreeWorld(){
        return freeWorld != null ? freeWorld : ((freeWorld) = new FreeWorld());
    }

    public static void main(String... args){
        System.setProperty("org.lwjgl.librarypath", new File("native/"+(System.getProperties().getProperty("os.name").split(" ")[0]).toLowerCase()).getAbsolutePath());

        FreeWorld.getFreeWorld();

        try {
            Display.setTitle(FreeWorld.getFreeWorld().getTitle());
            Display.setDisplayMode(new DisplayMode(720, 480));
            Display.setResizable(true);
            Display.create();
        }catch (LWJGLException e){
            e.printStackTrace();
        }

        //TODO: Provisoire
        glClearColor(0.2f, 0.7f, 0.7f, 1.0f);

        FreeWorld.getFreeWorld().start();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    private void start(){
        if(running) return;
        running = true;
        run();
    }

    private void run(){
        long lns = System.nanoTime();
        double ns = 1000000000.0/20.0;
        long ls = System.currentTimeMillis();
        int fps = 0, tps = 0;


        while (!Display.isCloseRequested() && running) {
            if(System.nanoTime() - lns > ns){
                lns+=ns;
                tps++;
                update();
            }else{
                fps++;
                render();
                Display.update();
            }

            if(System.currentTimeMillis() - ls >= 1000){
                ls = System.currentTimeMillis();
                Display.setTitle(title+" | FPS : "+fps+" | TPS : "+tps);
                fps = 0; tps = 0;
            }
        }

        Display.destroy();
        System.exit(0);
    }

    private void update(){
        if(Mouse.isButtonDown(0) && !Mouse.isGrabbed()) Mouse.setGrabbed(true);
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Mouse.isGrabbed()) Mouse.setGrabbed(false);
        if(!Mouse.isGrabbed()) return;

        world.updateWorld();
        //System.out.println("X = "+player.getLocation().getX()+" | Y = "+player.getLocation().getY()+" | Z = "+player.getLocation().getZ()+" | Yaw = "+player.getLocation().getYaw()+" | Pitch = "+(player.getLocation().getPitch()%360));
    }

    private void render(){
        if(Display.wasResized()) glViewport(0, 0, Display.getWidth(), Display.getHeight());

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);

        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);

        GLU.gluPerspective(70.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.01f, 1000.0f);

        glEnable(GL_DEPTH_TEST);
        glPopMatrix();
            glPushAttrib(GL_TRANSFORM_BIT);

                glRotatef(player.getLocation().getYaw(), 1, 0, 0);
                glRotatef(player.getLocation().getPitch(), 0, 1, 0);
                glTranslatef(-player.getLocation().getX(), -player.getLocation().getY(), -player.getLocation().getZ());

                Renderer.renderWorld(world);
                Renderer.renderRayCast(player);

            glPopAttrib();
        glPopMatrix();
        glDisable(GL_DEPTH_TEST);

        renderGUI();
    }

    private void renderGUI(){
        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        GLU.gluOrtho2D(0, Display.getWidth(), Display.getHeight(), 0);

        glColor3d(0.0d, 0.0d, 0.0d);
        glRectf(Display.getWidth()/2-4, Display.getHeight()/2-1, Display.getWidth()/2+4, Display.getHeight()/2+1);
        glRectf(Display.getWidth()/2-1, Display.getHeight()/2-4, Display.getWidth()/2+1, Display.getHeight()/2+4);
    }
}
