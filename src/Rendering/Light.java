package Rendering;

import Linear_Math.Vector3f;

public class Light 
{
	public Vector3f	position,
					color;
	public float	intensity;
	
	public Light() 
	{
		position	= new Vector3f(0,0,0);
		color		= new Vector3f(0,0,0);
		intensity = 0.f;
	}
	
	public Light(Vector3f pos, Vector3f col, float i) 
	{
		position	= pos;
		color		= col;
		intensity	= i;
	}
}
