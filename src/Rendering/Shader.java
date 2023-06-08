package Rendering;

import org.lwjgl.opengl.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL45.*;

import IO.GFile;
import IO.Log;
import Linear_Math.*;

public class Shader 
{
	public int	shader_vsh,
				shader_fsh,
				shader_prog;
	
	public ArrayList<Attribute> attributes;
	
	public class Attribute
	{
		public int		index,
						location;
		public String	name,
						type;
		
		public Attribute(String name, String type, int ID) 
		{
			this.name 		= name;
			this.type 		= type;
			this.location	= ID;
		}
	}
	
	public Shader() 
	{
		attributes 		= new ArrayList<Attribute>();
	}
	
	public Attribute createAttribute(String name, String type) 
	{
		Attribute a = new Attribute(name, type, shader_prog);
		
		a.index = attributes.size();
		attributes.add(a);
		
		return a;
	}
	
	public boolean loadVertexShader(String filename)
	{
		boolean success = true;
		
		String vsh_src = GFile.loadFileFromPath(filename + ".vsh", StandardCharsets.UTF_8);
		
		shader_vsh = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(shader_vsh, vsh_src);
		glCompileShader(shader_vsh);
		
		// Validate shaders
		int	compileStatus	= glGetShaderi(shader_vsh, GL_COMPILE_STATUS),
			log_length		= glGetShaderi(shader_vsh, GL_INFO_LOG_LENGTH);
		
		String err	= glGetShaderInfoLog(shader_vsh, log_length);
		success		= !(compileStatus == GL45.GL_FALSE);
		
		if ( !success ) 
			Log.logError(err);
		
		return success;
	}
	
	public boolean loadFragmentShader(String filename)
	{
		boolean success = true;
		
		String fsh_src = GFile.loadFileFromPath(filename + ".fsh", StandardCharsets.UTF_8);
		
		shader_fsh = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(shader_fsh, fsh_src);
		glCompileShader(shader_fsh);
		
		// Validate shaders
		int	compileStatus	= glGetShaderi(shader_fsh, GL_COMPILE_STATUS),
			log_length		= glGetShaderi(shader_fsh, GL_INFO_LOG_LENGTH);
		
		String err	= glGetShaderInfoLog(shader_fsh, log_length);
		success		= !(compileStatus == GL45.GL_FALSE);
		
		if ( !success ) 
			Log.logError(err);
		
		return success;
	}
	
	public void loadShaderProgram() 
	{
		shader_prog = glCreateProgram();
		
		glAttachShader(shader_prog, shader_vsh);
		glAttachShader(shader_prog, shader_fsh);
		glLinkProgram(shader_prog);
		
		int	linkStatus	= glGetShaderi(shader_prog, GL_LINK_STATUS),
			log_length	= glGetShaderi(shader_prog, GL_INFO_LOG_LENGTH);
		
		String err	= glGetShaderInfoLog(shader_prog, log_length);
		boolean success	= !(linkStatus == GL45.GL_FALSE);
		
		if ( !success ) 
			Log.logError(err);
		
		glValidateProgram(shader_prog);
		
		int	validateStatus	= glGetShaderi(shader_prog, GL_VALIDATE_STATUS);
		success	= !(validateStatus == GL45.GL_FALSE);
		
		if ( !success ) 
			Log.logError(err);
	}

	public int loadShaders(String filepath) 
	{
		int ID = -1;
		if (!loadVertexShader(filepath))
			return ID = -1;
		if(!loadFragmentShader(filepath))
			return ID = -1;
		
		loadShaderProgram();
		
		return this.shader_prog;
	}
	
	public void bindAttributes() 
	{
		for (Attribute a : attributes)
			glBindAttribLocation(shader_prog, a.index, a.name);
	}
	
	public void setUniform(int value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniform1i(ID, value);
	}
	
	public void setUniform(float value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniform1f(ID, value);
	}
	
	public void setUniform(Vector2f value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniform2f(ID, value.x, value.y);
	}
	
	public void setUniform(Vector3f value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniform3f(ID, value.x, value.y, value.z);
	}
	
	public void setUniform(Vector4f value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniform4f(ID, value.x, value.y, value.z, value.w);
	}
	
	public void setUniform(Matrix2x2f value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniformMatrix2fv(ID, true, value.c);
	}
	
	public void setUniform(Matrix3x3f value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniformMatrix3fv(ID, true, value.c);
	}
	
	public void setUniform(Matrix4x4f value, String uniform_name) 
	{
		int ID = glGetUniformLocation(shader_prog, uniform_name);
		glUniformMatrix4fv(ID, true, value.c);
	}
	
	public void destroy() 
	{
		glDeleteShader(shader_vsh);
		glDeleteShader(shader_fsh);
		glDeleteProgram(shader_prog);
	
		attributes.clear();
	}
}
