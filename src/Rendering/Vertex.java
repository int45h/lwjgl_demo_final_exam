package Rendering;

import Linear_Math.*;

/* Ideally, a vertex should be 8 bytes */
public class Vertex {
	public float 	x, y, z, 	// position (Vector3f)
					r, g, b,	// color (Vector3f) 
					u, v;		// texture coords (Vector2f)
					
	public static final int	length	= 8,		// # of components
							size	= 8 * 4;	// Size in bytes
	
	public Vertex()
	{
		x = 0.f;
		y = 0.f;
		z = 0.f;
		
		r = 0.f;
		g = 0.f;
		b = 0.f;
		
		u = 0.f;
		v = 0.f;
	}
	
	public Vertex(	float x, float y, float z, 
					float r, float g, float b,
					float u, float v			) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.r = r;
		this.g = g;
		this.b = b;
		
		this.u = u;
		this.v = v;
	}
	
	public Vertex(Vector3f xyz, Vector3f rgb, Vector2f uv) 
	{
		this.x = xyz.x;
		this.y = xyz.y;
		this.z = xyz.z;
		
		this.r = rgb.x;
		this.g = rgb.y;
		this.b = rgb.z;
		
		this.u = uv.x;
		this.v = uv.y;
	}
	
	public void setPosition(Vector3f xyz) 
	{
		this.x = xyz.x;
		this.y = xyz.y;
		this.z = xyz.z;
	}
	
	public void setNormal(Vector3f rgb) 
	{
		this.r = rgb.x;
		this.g = rgb.y;
		this.b = rgb.z;
	}
	
	public void setUV(Vector2f uv) 
	{
		this.u = uv.x;
		this.v = uv.y;
	}
	
	public Vector3f getPosition() 
	{
		return new Vector3f(this.x, this.y, this.z);
	}
	
	public Vector3f getNormal() 
	{
		return new Vector3f(this.r, this.g, this.b);
	}
	
	public Vector2f getUV() 
	{
		return new Vector2f(this.u, this.v);
	}
	
	public float[] toFloatArr() 
	{
		float[] arr = new float[length]; 
		
		arr[0] = this.x;
		arr[1] = this.y;
		arr[2] = this.z;
		
		arr[3] = this.r;
		arr[4] = this.g;
		arr[5] = this.b;
		
		arr[6] = this.u;
		arr[7] = this.v;
		
		return arr;
	}
}
