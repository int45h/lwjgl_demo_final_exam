package Rendering;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import IO.*;
import Linear_Math.Matrix4x4f;

public class Window 
{
	public int			width, 
						height;
	public float		aspect_ratio;
	public String 		name;
	
	public Input		input;
	
	private long		GLFWWindowID;
	private GLFWWindowSizeCallback GLFWWindowResizeCallback;
	private GLFWVidMode	video_mode;
	
	public Camera		current_camera;
	
	public ArrayList<Mesh>		objects;
	public ArrayList<Shader>	shaders;
	
	void error(String message) 
	{
		Log.logError(message);
	}
	
	boolean startGlfw() 
	{
		boolean isFailed = false;
		
		input = new Input();
		
		if ( (isFailed = !GLFW.glfwInit()) == true )
		{
			error("error: Failed to initialize GLFW!");
			return isFailed;
		}
		
		// Debug
		GLFW.glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
		
		// Set GL version to 4.5
		GLFW.glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		GLFW.glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);
		
		// Set resizable
		GLFW.glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		GLFWWindowID = GLFW.glfwCreateWindow(width, height, name, 0, 0);
		
		if (GLFWWindowID == 0) 
		{
			error("error: Failed to create window!");
			return (isFailed = true);
		}
		
		// Set the window position
		this.video_mode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(	GLFWWindowID, 
								(video_mode.width() - width)	/ 2, 
								(video_mode.height() - height)	/ 2	);
		
		// VSync
		GLFW.glfwSwapInterval(1);
		
		// Input
		GLFW.glfwSetKeyCallback(GLFWWindowID, input.getKBCallback());
		
		// Make the window context current 
		GLFW.glfwMakeContextCurrent(GLFWWindowID);
		
		GL.createCapabilities();
		GLFW.glfwShowWindow(GLFWWindowID);
		
		GLUtil.setupDebugMessageCallback();
		
		// Enable depth bit
		glEnable(GL_DEPTH_TEST);
		
		initCallbacks();
		return isFailed;
	}
	
	public void initCallbacks() 
	{
		GLFWWindowResizeCallback = new GLFWWindowSizeCallback() {
			public void invoke(long ID, int w, int h) 
			{
				GLFW.glfwSetWindowSize(ID, w, h);
				aspect_ratio = ((float)w / (float)h);
				glViewport(0, 0, w, h);
				if (current_camera != null)
					current_camera.projection = Matrix4x4f.projection(aspect_ratio, 45, 1f, 100f);
			}
		};
		glfwSetWindowSizeCallback(GLFWWindowID, GLFWWindowResizeCallback);
	}
	
	public void update(Shader shader, Camera camera) 
	{
		// Make the window context current 
		GLFW.glfwMakeContextCurrent(GLFWWindowID);
				
		glClearColor(1.f, 0.f, 0.f, 0.f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glUseProgram(shader.shader_prog);
		
		if (objects != null && objects.size() > 0) {
			for (Mesh obj : objects)
				renderMesh(obj, shader, camera);
		}
		shader.setUniform(camera.projection, "projection");
		
		GLFW.glfwPollEvents();
	}
	
	public void addMesh(Mesh obj) 
	{
		objects.add(obj);
	}
	
	public void addMesh(Mesh[] obj) 
	{
		for (Mesh m : obj) 
			objects.add(m);
	}
	
	public void removeMesh() 
	{
		if ((objects.size() - 1) != 0)
			objects.remove(objects.size() - 1);
	}
	
	public void removeMesh(int index) 
	{
		objects.remove(index);
	}
	
	public void close() 
	{
		GLFW.glfwSetWindowShouldClose(GLFWWindowID, true);
	}
	
	public void renderMesh(Mesh obj, Shader shader, Camera camera) 
	{
		glUseProgram(shader.shader_prog);
		glBindVertexArray(obj.VAO);
		
		// Apply model transform
		camera.applyTransform(obj, shader);
		
		glDrawElements(GL_TRIANGLES, obj.vertexIndices.length, GL_UNSIGNED_INT, 0);
		if (obj.texture != null) {
			// for whatever reason the textures are loaded backwards and i don't know why
			int largest_index = objects.size() - 1;
			
			glActiveTexture(GL_TEXTURE0 + obj.texture.index);
			glBindTexture(GL_TEXTURE_2D, obj.texture.texture_ID);
			shader.setUniform((largest_index - obj.texture.index), "tex");
			//obj.setTexture("tex", shader);
		}
		glBindVertexArray(0);
	}
	
	public void swapBuffers() 
	{
		GLFW.glfwSwapBuffers(GLFWWindowID);
	}
	
	
	public void destroy() 
	{
		for (Mesh obj : objects) 
			obj.destroy();
		
		if (this.objects != null)
			objects.clear();
		
		if (this.shaders != null)
			shaders.clear();
		
		if (this.current_camera != null) 
		{
			this.current_camera = null;
		}
		
		Log.logInfo("Window " + Long.toString(GLFWWindowID) + " destroyed!");
		GLFW.glfwDestroyWindow(GLFWWindowID);
	}
	
	public boolean shouldClose() 
	{
		return GLFW.glfwWindowShouldClose(GLFWWindowID);
	}
	
	public void setCamera(Camera c) 
	{
		this.current_camera = c;
	}
	
	public Window()
	{
		this.width			= 640;
		this.height			= 480;
		this.aspect_ratio	= ((float)width / (float)height);
		this.name			= "New Window";
		
		this.objects		= new ArrayList<Mesh>();
		this.shaders		= new ArrayList<Shader>();
		
		if (startGlfw())
			destroy();
	}
	
	public Window(int w, int h, String name) 
	{
		this.width			= w;
		this.height			= h;
		this.aspect_ratio	= ((float)width / (float)height);
		this.name			= name;
		
		this.objects		= new ArrayList<Mesh>();
		this.shaders		= new ArrayList<Shader>();
		
		if (startGlfw())
			destroy();
	}
}
