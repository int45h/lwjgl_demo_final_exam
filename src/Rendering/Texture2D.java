package Rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import IO.Log;

public class Texture2D 
{
	public int 	texture_ID,
				index;
	
	public Texture2D(String filename, int index)
	{
		this.index = index;
		
		texture_ID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, texture_ID);
		
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		try 
		{
			loadTextures(filename);
		}
		catch (IOException e) 
		{
			Log.logError(e.toString());
			e.printStackTrace();
		}
	}
	
	public static void loadTextures(String filename) throws IOException
	{
		try 
		{
			STBImage.stbi_set_flip_vertically_on_load(true);
			IntBuffer 	w 			= BufferUtils.createIntBuffer(1),
						h 			= BufferUtils.createIntBuffer(1),
						channels	= BufferUtils.createIntBuffer(1);
			
			ByteBuffer	tex_raw = STBImage.stbi_load(	filename, 
														w, 
														h, 
														channels, 
														0											);
			if (tex_raw == null) 
			{
				throw new IOException("File could not be loaded!");
			}
			int 	width 	= w.get(),
					height	= h.get(),
					channel	= channels.get();
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, tex_raw);
			glGenerateMipmap(GL_TEXTURE_2D);
			STBImage.stbi_image_free(tex_raw);
		}
		catch(Exception e) 
		{
			Log.logError(e.toString());
			e.printStackTrace();
		}
	}
	
	public void destroy() 
	{
		IntBuffer id = IntBuffer.allocate(1);
		id.put(this.texture_ID);
		id.flip();
		
		glDeleteTextures(id);
	}
}
