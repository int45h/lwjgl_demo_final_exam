package Linear_Math;
import java.lang.Math;

public final class Vector2f
{
	public float 	x, y, 
					length_c,	// Length  
					length_ac;	// Length (approximate)
	public Vector2f()
	{
		x = y = 0.f;
	}
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(Vector3f v)
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	public Vector2f(Vector4f v)
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	public void set(float x, float y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2f v) 
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	public float length() 
	{
		length_c = (float)(Math.sqrt(x*x + y*y)); 
		return length_c;
	}
	
	public float fastLength() 
	{
		float mag = x*x + y*y;
		return mag * Common.FastInvSqrtf(mag);
	}
	
	public float invLength() 
	{
		length_c = (float)(Math.sqrt(x*x + y*y)); 
		return (float)(1 / length_c);
	}
	
	public float invFastLength() 
	{
		float mag = x*x + y*y;
		return Common.FastInvSqrtf(mag);
	}
	
	public void scale(float c) 
	{
		this.x *= c;
		this.y *= c;
	}
	
	public void normalize() 
	{
		float inv_length = (float)(1 / length());
		scale(inv_length);
	}
	
	public void fastNormalize() 
	{
		float	mag = x*x + y*y,
				inv_length = Common.FastInvSqrtf(mag);
		
		scale(inv_length);
	}
	
	public void transform(Matrix2x2f M) 
	{
		float 	n_x = this.x * M.c[0] + this.y * M.c[1],
				n_y = this.x * M.c[2] + this.y * M.c[3];
		
		this.x = n_x;
		this.y = n_y;
	}
	
	public static Vector2f transform(Matrix2x2f M, Vector2f v) 
	{
		return new Vector2f(	v.x * M.c[0] + v.y * M.c[1],
								v.x * M.c[2] + v.y * M.c[3]	);
	}
	
	public static Vector2f add(Vector2f a, Vector2f b) 
	{
		return new Vector2f(a.x + b.x, 
							a.y + b.y);
	}
	
	public static Vector2f sub(Vector2f a, Vector2f b) 
	{
		return new Vector2f(a.x - b.x, 
							a.y - b.y);
	}
	
	public static float dot(Vector2f a, Vector2f b) 
	{
		float dot = (	a.x*b.x +
						a.y*b.y	);
		return dot;
	}
	
	public String toString() 
	{
		StringBuilder v_str = new StringBuilder(Float.toString(this.x));
		v_str.append(", ");
		
		v_str.append(Float.toString(this.y));
		return v_str.toString();
	}
}