package IO;

import org.lwjgl.glfw.*;
import Rendering.*;

public class Input 
{
	private GLFWKeyCallback			keyboardEvent;
	private GLFWCursorPosCallback	mousePosEvent;
	private GLFWCursorEnterCallback	mouseEnterEvent;
	private int						GLFWKey;
	private int						GLFWKeyAction;
	private int						time1,
									time2,
									deltaTime;
	private int						mouseX,
									mouseY;
	
	
	public Input() 
	{
		keyboardEvent = new GLFWKeyCallback() {
			public void invoke(long window_ID, int key, int scancode, int action, int mods) 
			{
				logDeltaTime();
				GLFWKey			= key;
				GLFWKeyAction	= action;
			}
		};
	}
	
	public GLFWKeyCallback getKBCallback() 
	{
		return keyboardEvent;
	}
	
	public boolean checkDeltaTime(int ms) 
	{
		return (deltaTime > ms);
	}
	
	public void logDeltaTime() 
	{
		time2 = time1;
		time1 = (int)System.currentTimeMillis();
		deltaTime = time1 - time2;
	}
	
	public boolean GetKeyDown(char c) 
	{
		boolean keyPressed = ((char)GLFWKey == c) ? true : false;
		
		return keyPressed;
	}
	
	public char KeyPressRaw() 
	{
		return (GLFWKeyAction == GLFW.GLFW_PRESS) ? (char)GLFWKey : 0;
	}
	
	public char KeyPress() 
	{
		char key = 0;
		//logDeltaTime();
		
		if 		(GLFWKey < 32 || GLFWKey > 126)
			key = 0;
		else if (GLFWKeyAction == GLFW.GLFW_PRESS && checkDeltaTime(500))
			key = (char)GLFWKey;
		
		return key;
	}
	
	public void destroy() 
	{
		keyboardEvent.free();
	}
}
