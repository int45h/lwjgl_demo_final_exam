package Rendering;

import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.glActiveTexture;
//import org.lwjgl.opengl.GL45;
import static org.lwjgl.opengl.GL45.*;
import Linear_Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack.*;
import org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.assimp.*;
import static org.lwjgl.assimp.Assimp.*;

import org.lwjgl.stb.*;
import static org.lwjgl.stb.STBImage.*;

import IO.GFile;
import IO.Log;

public class Mesh 
{
	public Vertex[] 	vertices;		// Vertices
	
	public int[]		vertexIndices,	// Vertex Indices
						normalIndices,	// Normal Indices
						UVIndices;		// UV indices
						
	public Shader		shader;			// Shader program
	
	public int			VAO,			// Vertex Array Object ID 
						VBO, 			// Vertex Buffer Object ID
						EBO;			// Indices ID
	
	public int			shader_ID;		// Shader ID
	public Transform	transform;		// Transformation object, position and rotation
	public Texture2D	texture;		// Texture to use
	
	public static Mesh loadFromFile(String filepath)
	{
		Mesh m = new Mesh();
		try 
		{
			int flags = Assimp.aiProcess_JoinIdenticalVertices	| 
						Assimp.aiProcess_Triangulate 			|
						Assimp.aiProcess_FixInfacingNormals;
			
			AIScene scene = Assimp.aiImportFile(filepath, flags);
			
			if (scene == null) 
			{
				throw new Exception("Failed to open model from " + filepath);
			}
			
			int mesh_count = scene.mNumMeshes();
			Log.logInfo("Mesh count: " + Integer.toString(mesh_count));
			
			AIMesh a_m = AIMesh.create(scene.mMeshes().get(0));
			m = processMesh(m, a_m);
			
			Log.logInfo("Mesh vertices length: " + Integer.toString(m.vertices.length));
		}
		catch (Exception e) 
		{
			Log.logError(e.toString());
			e.printStackTrace();
		}
		return m;
	}	
	
	public void loadTexture(String filename, int index) throws IOException
	{
		texture = new Texture2D(filename, index);
	}
	
	public void useTexture(int index) 
	{
		glActiveTexture(GL_TEXTURE0 + index);
		glBindTexture(GL_TEXTURE_2D, this.texture.texture_ID);
	}
	
	public void setTexture(String tex_name, Shader s) 
	{
		s.setUniform(this.texture.index, tex_name);
	};
	
	public static Mesh processMesh(Mesh m, AIMesh a_m) 
	{
		ArrayList<Vector3f> v_pos 	= new ArrayList<Vector3f>(),
							v_norm	= new ArrayList<Vector3f>();
		ArrayList<Vector2f>	v_tex	= new ArrayList<Vector2f>();
		ArrayList<Integer>	v_ind	= new ArrayList<Integer>();
		
		getVertices(a_m, v_pos);
		getNormals(a_m, v_norm);
		getUVs(a_m, v_tex);
		getIndices(a_m, v_ind);
		
		m				= new Mesh(v_pos.size());
		m.vertexIndices	= v_ind.stream().mapToInt(Integer::intValue).toArray(); 
		for (int i = 0; i < m.vertices.length; i++) 
		{
			m.vertices[i] = new Vertex();
			m.vertices[i].setPosition(v_pos.get(i));
			m.vertices[i].setNormal(v_norm.get(i));
			if (v_tex.size() == m.vertices.length)
				m.vertices[i].setUV(v_tex.get(i));
		}
		
		v_pos.clear();
		v_norm.clear();
		v_tex.clear();
		v_ind.clear();
		
		return m;
	}
	
	public static void getVertices(AIMesh a_m, ArrayList<Vector3f> v_pos) 
	{
		AIVector3D.Buffer a_v = a_m.mVertices();
		while (a_v.remaining() > 0) 
		{
			AIVector3D a_v3d = a_v.get();
			v_pos.add(	new Vector3f(	a_v3d.x(), 
										a_v3d.y(), 
										a_v3d.z()	));
		}
	}
	
	public static void getNormals(AIMesh a_m, ArrayList<Vector3f> v_norm) 
	{
		AIVector3D.Buffer a_v = a_m.mNormals();
		while (a_v.remaining() > 0) 
		{
			AIVector3D a_v3d = a_v.get();
			v_norm.add(	new Vector3f(	a_v3d.x(), 
										a_v3d.y(), 
										a_v3d.z()	));
		}
	}
	
	public static void getUVs(AIMesh a_m, ArrayList<Vector2f> v_uv) 
	{
		if (a_m.mNumUVComponents().get(0) > 0) 
		{
			AIVector3D.Buffer texture = a_m.mTextureCoords(0);
			while (texture.remaining() > 0) 
			{
				AIVector3D a_t = texture.get();
				v_uv.add(	new Vector2f(	a_t.x(),
											a_t.y()	));
			}
		}
	}
	
	public static void getIndices(AIMesh a_m, ArrayList<Integer> v_ind) 
	{
		AIFace.Buffer a_faces = a_m.mFaces();
		while(a_faces.remaining() > 0) 
		{
			AIFace face = a_faces.get();
			for (int i = 0; i < face.mNumIndices(); i++)
				v_ind.add(face.mIndices().get(i));
		}
	}
	
	public Mesh() 
	{
	
	}
	
	public Mesh(int n) 
	{
		transform	= new Transform();
		vertices	= new Vertex[n];
		shader		= new Shader();
		
		System.out.printf("Vertices Length: %d\n", vertices.length);
	}
	
	public boolean generateMesh()
	{
		boolean success = true;

		try 
		{
			VAO = glGenVertexArrays();
			VBO = glGenBuffers();
			EBO = glGenBuffers();
			
			glBindVertexArray(VAO);
			
			FloatBuffer vertexData 	= vertToFloatBuffer(vertices);
			IntBuffer	indexData	= indexToIntBuffer(vertexIndices);
			
			Log.logInfo("Size of indexData: " + Integer.toString(vertexIndices.length));
			
			glBindBuffer(GL_ARRAY_BUFFER, VBO);
			glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
			glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.size, 0);
			glEnableVertexAttribArray(0);
			
			glVertexAttribPointer(1, 3, GL_FLOAT, false, Vertex.size, 12);
			glEnableVertexAttribArray(1);
			
			glVertexAttribPointer(2, 2, GL_FLOAT, false, Vertex.size, 24);
			glEnableVertexAttribArray(2);
			//glBindVertexArray(0);
			
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, vertexIndices, GL_STATIC_DRAW);
			//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
			
			glBindVertexArray(0);
		}
		catch (RuntimeException e) 
		{
			e.printStackTrace();
			Log.logError(e.toString());
			success = false;
		}
		return success;
	}
	
	public static FloatBuffer vertToFloatBuffer(Vertex[] v) throws RuntimeException 
	{
		FloatBuffer vertexData = BufferUtils.createFloatBuffer( v.length * Vertex.size );
		for (int i = 0; i < v.length; i++) 
		{
			vertexData.put(v[i].toFloatArr());
		}
		vertexData.flip();
		
		return vertexData;
	}
	
	public static IntBuffer indexToIntBuffer(int[] indices) throws RuntimeException 
	{
		IntBuffer indexData = BufferUtils.createIntBuffer(indices.length);
		indexData.put(indices);
		indexData.flip();
		
		return indexData;
	}
	
	public void setIndices(int n, int x, int y, int z) 
	{
		this.vertexIndices[n + 0] = x;
		this.vertexIndices[n + 1] = x;
		this.vertexIndices[n + 2] = x;
	}
	
	public static Mesh triangle() 
	{
		Mesh tri		= new Mesh(3);
		tri.vertexIndices 	= new int[] {0, 1, 2};
		
		tri.vertices[0] = new Vertex(+0.0f, +0.5f, +0.0f,	1,0,0,	0,0);
		tri.vertices[1] = new Vertex(+0.5f, -0.5f, +0.0f, 	0,0,1,	0,0);
		tri.vertices[2] = new Vertex(-0.5f, -0.5f, +0.0f, 	0,1,0,	0,0);
		
		return tri;
	}
	
	public static Mesh quad() 
	{
		Mesh quad		= new Mesh(4);
		quad.vertexIndices 	= new int[] {	0, 1, 2, 
											2, 3, 0	};
		
		quad.vertices[0] = new Vertex(-0.5f, +0.5f, +0.0f,	1,0,0,	0,0);
		quad.vertices[1] = new Vertex(-0.5f, -0.5f, +0.0f, 	0,0,1,	0,0);
		quad.vertices[2] = new Vertex(+0.5f, -0.5f, +0.0f, 	0,1,0,	0,0);
		quad.vertices[3] = new Vertex(+0.5f, +0.5f, +0.0f, 	0,1,0,	0,0);
		
		return quad;
	}	
	
	public static Mesh cube() 
	{
		Mesh cube			= new Mesh(8);
		
		cube.vertices[0]	= new Vertex(-0.5f, +0.5f, +0.5f,	1,0,0,	0,0);
		cube.vertices[1]	= new Vertex(-0.5f, -0.5f, +0.5f,	0,1,0,	0,0);
		cube.vertices[2]	= new Vertex(+0.5f, -0.5f, +0.5f,	0,0,1,	0,0);
		cube.vertices[3]	= new Vertex(+0.5f, +0.5f, +0.5f,	1,0,0,	0,0);
		cube.vertices[4]	= new Vertex(-0.5f, +0.5f, -0.5f,	0,1,0,	0,0);
		cube.vertices[5]	= new Vertex(-0.5f, -0.5f, -0.5f,	0,0,1,	0,0);
		cube.vertices[6]	= new Vertex(+0.5f, -0.5f, -0.5f,	1,0,0,	0,0);
		cube.vertices[7]	= new Vertex(+0.5f, +0.5f, -0.5f,	0,1,0,	0,0);
		
		cube.vertexIndices		= new int[] {	0, 1, 2, // front
												2, 3, 0,
											
												0, 1, 5, // left
												5, 4, 0,
											
												0, 3, 7, // top
												7, 4, 0,
											
												3, 2, 6, // right
												6, 7, 3,
											
												1, 2, 6, // bottom 
												6, 5, 1,
											
												5, 6, 7, // back
												7, 4, 5	};
		return cube;
	}
	
	public boolean loadShaders(String filepath) 
	{
		boolean success = true;
		
		if (!shader.loadVertexShader(filepath))
			return success = false;
		if(!shader.loadFragmentShader(filepath))
			return success = false;
		
		shader.loadShaderProgram();
		
		return success;
	}
	
	public void destroy() 
	{
		glDeleteVertexArrays(VAO);
		glDeleteBuffers(VBO);
		glDeleteBuffers(EBO);
		
		vertices		= null;
		vertexIndices 	= null;
		//texture.destroy();
		transform.destroy();
	}
}
