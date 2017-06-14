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
package org.freeworld.client.entity;

import org.freeworld.client.maths.Vector3f;
import org.freeworld.client.utils.Location;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class PlayerEntity extends Entity {

    public PlayerEntity(String name, Location location){
        super(name, location);
    }

    @Override
    public void update() {
        move();
    }

    private void move(){
        location.removeYaw(Mouse.getDY()* 0.05F);
        location.addPitch(Mouse.getDX()* 0.05F);

        if(location.getYaw() < -90.0f) location.setYaw(-90.0f);
        if(location.getYaw() > 90.0f) location.setYaw(90.0f);

        float zDir = 0.0f, xDir = 0.0f, yDir = 0.0f;
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            zDir = -speed;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            zDir = speed;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            xDir = -speed;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            xDir = speed;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            yDir = speed+0.02f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            yDir = -speed;
        }

        double rad = Math.toRadians((location.getPitch()));
        velocity.addX(xDir * Math.cos(rad) - zDir * Math.sin(rad)).addY(yDir).addZ(zDir * Math.cos(rad) + xDir * Math.sin(rad));

        location.addX((float) velocity.getX()).addY((float)velocity.getY()).addZ((float)velocity.getZ());
        velocity.multiplyX(0.5f).multiplyY(0.5f).multiplyZ(0.5f);
    }
}
