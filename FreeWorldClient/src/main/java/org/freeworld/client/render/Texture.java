package org.freeworld.client.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.glu.GLU;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by NeutronStars on 09/07/2017
 */
public class Texture {

    private static HashMap<String, Integer> idMap = new HashMap<>();

    private String path;

    public Texture(String resourceName){
        try{
            if (idMap.containsKey(resourceName)) return;

            this.path = resourceName;

            IntBuffer ib = BufferUtils.createIntBuffer(1);
            ib.clear();
            glGenTextures(ib);
            int id = ib.get(0);
            idMap.put(resourceName, Integer.valueOf(id));

            glBindTexture(3553, id);

            glTexParameteri(3553, 10241, GL_NEAREST);
            glTexParameteri(3553, 10240, GL_NEAREST);

            BufferedImage img = ImageIO.read(Texture.class.getResourceAsStream(resourceName));

            int w = img.getWidth();
            int h = img.getHeight();

            ByteBuffer pixels = BufferUtils.createByteBuffer(w * h * 4);
            int[] rawPixels = new int[w * h];

            img.getRGB(0, 0, w, h, rawPixels, 0, w);

            for (int i = 0; i < rawPixels.length; i++){
                int a = rawPixels[i] >> 24 & 0xFF;
                int r = rawPixels[i] >> 16 & 0xFF;
                int g = rawPixels[i] >> 8 & 0xFF;
                int b = rawPixels[i] & 0xFF;

                rawPixels[i] = (a << 24 | b << 16 | g << 8 | r);
            }

            pixels.asIntBuffer().put(rawPixels);
            GLU.gluBuild2DMipmaps(3553, 6408, w, h, 6408, 5121, pixels);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        }catch (IOException e){
            throw new RuntimeException("!!");
        }
    }

    public void bind(){
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, ((Integer)idMap.get(path)).intValue());
    }

    public void unBind(){
        glDisable(GL_TEXTURE_2D);
    }
}
