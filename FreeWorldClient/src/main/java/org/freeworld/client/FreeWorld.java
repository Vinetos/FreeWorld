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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;

public final class FreeWorld{

    private static FreeWorld freeWorld;
    private boolean running;

    private FreeWorld(){

    }

    public static FreeWorld getFreeWorld(){
        return freeWorld != null ? freeWorld : new FreeWorld();
    }

    public static void main(String... args){

        System.setProperty("org.lwjgl.librarypath", new File("native/"+(System.getProperties().getProperty("os.name").split(" ")[0]).toLowerCase()).getAbsolutePath());

        try {
            Display.setTitle("FreeWorld Alpha 130617");
            Display.setDisplayMode(new DisplayMode(720, 480));
            Display.create();
        }catch (LWJGLException e){
            e.printStackTrace();
        }

        //TODO: Provisoire
        glEnable(GL_DEPTH_TEST);
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
                //Update
            }else{
                fps++;
                Display.update();
                //Render
            }

            if(System.currentTimeMillis() - ls >= 1000){
                ls = System.currentTimeMillis();
                Display.setTitle("FreeWorld Alpha 130617 | FPS : "+fps+" | TPS : "+tps);
                fps = 0; tps = 0;
            }
        }

        Display.destroy();
        System.exit(0);
    }
}
