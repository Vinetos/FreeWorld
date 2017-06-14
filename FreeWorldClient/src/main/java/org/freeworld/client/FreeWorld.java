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
import org.freeworld.client.maths.Vector4f;
import org.freeworld.client.maths.Vector5f;
import org.freeworld.client.render.Renderer;
import org.freeworld.client.world.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;

public final class FreeWorld{

    private static FreeWorld freeWorld;

    private final String name = "FreeWorld", version = "Alpha 140617", title = String.format("%1$s %2$s", name, version);

    private final World world;
    private final Vector5f cam = new Vector5f(0.0f, 5.0f, 0.0f, 0.0f, 0.0f);
    private boolean running;

    private FreeWorld(){
        Block.registerBlocks();
        Renderer.registerRenderBlocks();

        this.world = new World("world");
    }

    public static FreeWorld getFreeWorld(){
        return freeWorld != null ? freeWorld : ((freeWorld) = new FreeWorld());
    }

    public static void main(String... args){

        System.setProperty("org.lwjgl.librarypath", new File("native/"+(System.getProperties().getProperty("os.name").split(" ")[0]).toLowerCase()).getAbsolutePath());

        try {
            Display.setTitle(FreeWorld.getFreeWorld().title);
            Display.setDisplayMode(new DisplayMode(720, 480));
            Display.create();
        }catch (LWJGLException e){
            e.printStackTrace();
        }

        //TODO: Provisoire
        glClearColor(0.2f, 0.7f, 0.7f, 1.0f);

        FreeWorld.getFreeWorld().start();
    }

    private void start(){
        if(running) return;
        running = true;
        run();
    }

    public void run(){
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
                //Render
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

        cam.removeYaw(Mouse.getDY()* 0.1F);
        cam.addPitch(Mouse.getDX()* 0.1F);
        if(cam.getYaw() < -90.0f) cam.setYaw(-90.0f);
        if(cam.getYaw() > 90.0f) cam.setYaw(90.0f);

        if(Keyboard.isKeyDown(Keyboard.KEY_UP)) cam.addX(0.5f);
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) cam.addX(-0.5f);
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) cam.addZ(0.5f);
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) cam.addZ(-0.5f);

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) cam.addY(0.5f);
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) cam.addY(-0.5f);


        //System.out.println("X = "+cam.getX()+" | Y = "+cam.getY()+" | Z = "+cam.getZ()+" | Yaw = "+cam.getYaw()+" | Pitch = "+(cam.getPitch()%360));
    }

    private void render(){
        if(Display.wasResized()) glViewport(0, 0, Display.getWidth(), Display.getHeight());

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);

        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);

        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);

        GLU.gluPerspective(70.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.01f, 250.0f);

        glEnable(GL_DEPTH_TEST);
        glPopMatrix();
            glPushAttrib(GL_TRANSFORM_BIT);

                glRotatef(cam.getYaw(), 1, 0, 0);
                glRotatef(cam.getPitch(), 0, 1, 0);
                glTranslatef(-cam.getX(), -cam.getY(), -cam.getZ());

                Renderer.renderWorld(world);
            glPopAttrib();
        glPopMatrix();
        glDisable(GL_DEPTH_TEST);
    }
}
