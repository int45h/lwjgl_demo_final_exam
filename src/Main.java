import java.util.*;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;

import Linear_Math.*;
import Rendering.*;
import IO.*;

import ECS.*;
import GameLogic.Core.*;

import static org.lwjgl.opengl.GL45.*;

import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;

class GameThread implements Runnable
{
	private Thread 	t;
	private String 	threadName;
	
	private Window	window;
	private Camera	camera;
	
	private GameObject 	obj2,
						obj3;
	
	private Creature	obj;
	
	private Light		mainLight;
	
	private ArrayList<Shader> shader_list;
	private int current_shader;
	
	public int		frames;
	public long		time;
	
	public GameThread(String name) 
	{
		threadName = name;
	}
	
	public void start() 
	{
		if (t == null) 
		{
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	public void setupCamera() 
	{
		this.camera.objects = this.window.objects;
		
		this.camera.setPerspectiveMatrix(this.window, 180);
		this.window.current_camera = this.camera;
		this.camera.position = new Vector3f(0, 5, -5);
		this.camera.viewDir  = new Vector3f(0,0,1);
		this.camera.lookAt(this.camera.viewDir);
		
		System.out.println(this.window.current_camera.projection);
		System.out.println(this.window.current_camera.view);
		System.out.println(this.window.current_camera.model);
		
	}
	
	public void init() 
	{
		this.window = new Window(640, 480, "Test Window");
		this.camera = new Camera();
		
		this.shader_list 	= new ArrayList<Shader>();
		this.time			= (int)System.currentTimeMillis();
		setupGame();
		setupCamera();
	}
	
	public void addShader(Shader s) 
	{
		this.shader_list.add(s);
		this.current_shader = this.shader_list.size() - 1;
	}
	
	public Shader getShader() 
	{
		return this.shader_list.get(current_shader);
	}
	
	public Shader getShader(int i) 
	{
		return this.shader_list.get(current_shader = i);
	}
	
	public void run() 
	{
		init();
		while(!window.shouldClose()) 
		{
			update();
			render();
		}
		cleanUp();
	}
	
	public void cleanUp() 
	{
		window.destroy();
		camera.destroy();
		shader_list.clear();
		gameExit();
	}
	
	public void moveOnInput(Vector3f pos, float v) 
	{	
		Vector3f Dxy = new Vector3f(0,0,0);
		
		switch (window.input.KeyPressRaw()) 
		{
		case 'W': case 'w': Dxy = new Vector3f(+0f, +0f, -v );	break;
		case 'A': case 'a': Dxy = new Vector3f(-v , +0f, +0f);	break;
		case 'S': case 's': Dxy = new Vector3f(+0f, +0f, +v );	break;
		case 'D': case 'd': Dxy = new Vector3f(+v , +0f, +0f);	break;
		default: Dxy = new Vector3f(0,0,0);
		}
		
		pos.add(Dxy);
		this.camera.position.add(Dxy);
		this.camera.viewDir.add(Dxy);
		this.camera.lookAt(this.camera.viewDir);
	}
	
	public void setupGame() 
	{
		mainLight = new Light(	new Vector3f(0, 2, 0), 
								new Vector3f(1, 1, 1), 
								.8f	);
		
		addShader(new Shader());
		getShader(0).loadShaders("./shaders/final");
		
		obj = new Warrior();
		obj.setMesh(Mesh.loadFromFile("./models/source/low_poly_miku.obj"));
		obj.mesh.shader_ID = getShader(0).shader_prog;
		
		obj2 = new GameObject("p2");
		obj2.setMesh(Mesh.loadFromFile("./models/source/mc_grass.obj"));
		obj2.mesh.shader_ID = getShader(0).shader_prog;
		
		obj2.mesh.transform.translate(0, -5f, 0);
		obj2.mesh.transform.scale(20f, 1f, 20f);
		
		obj.mesh.transform.scale(0.05f);
		
		window.addMesh(obj.mesh);
		window.addMesh(obj2.mesh);
		
		try 
		{
			obj.mesh.loadTexture("./models/source/textures/default.png", 0);
			obj2.mesh.loadTexture("./models/source/textures/mc_grass.png", 1);
		}
		catch (Exception e) 
		{
			Log.logError(e.toString());
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void tick()
	{
		moveOnInput(obj.position, 0.25f);
		
		obj.mesh.transform.translate(obj.position);
		checkTile();
		
		obj.setPositionInt();
		
		getShader().setUniform(mainLight.position, "lightPos");
		getShader().setUniform(mainLight.color, "lightCol");
		getShader().setUniform(mainLight.intensity, "lightIntensity");
		
		if (window.input.KeyPress() == GLFW.GLFW_KEY_I) 
			Log.logfInfo("Player's inventory: %s\n", obj.inventory.spoolContents());
	}	
	
	public void checkTile() 
	{
		Vector3f iPos = obj.getPositionInt();
		if ((int)Math.floor(obj.position.x) != (int)iPos.x || (int)Math.floor(obj.position.z) != (int)iPos.z) 
		{
			int roll = GameLogic.Core.Dice.roll(20);
			if (roll < 2) 
			{
				Log.logInfo("Encounter!");
			}
		}
			
	}
	
	public void gameExit() 
	{
		obj.destroy();
		obj2.destroy();
	}
	
	public void update() 
	{
		Shader shader = this.shader_list.get(current_shader);
		window.update(shader, this.camera);
		if (window.input.GetKeyDown((char)GLFW.GLFW_KEY_ESCAPE))
			window.close();
		
		tick();
		frameCounter();
	}
	
	public void render() 
	{
		window.swapBuffers();
	}
	
	public void frameCounter() 
	{
		frames++;
		if (System.currentTimeMillis() > time + 1000) 
		{
			Log.logInfo("Frames: " + Integer.toString(frames));
			time = System.currentTimeMillis();
			frames = 0;
		}
	}
}

public final class Main 
{	
	public static void main(String[] args) 
	{
	GameThread t = new GameThread("name");
	t.start();
	//System.out.println(Matrix4x4f.projection(4.f/3.f, 90, 1.f, 100.f));
	//System.out.println(Matrix4x4f.lookAt(
	//			new Vector3f(0, 0, -1),
	//			new Vector3f(0, 0, 0),
	//			new Vector3f(0, 1, 0)
	//		));
	}
}
