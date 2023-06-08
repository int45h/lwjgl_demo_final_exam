package Linear_Math;
import java.lang.Math;

public final class Vector3f 
{
	public float 	x, y, z, 
					length_c,	// Length  
					length_ac;	// Length (approximate)
	public Vector3f()
	{
		x = y = z = 0.f;
	}
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector2f v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = 1.f;
	}
	
	public Vector3f(Vector3f v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public Vector3f(Vector4f v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public void set(float x, float y, float z) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(Vector3f v) 
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public float length() 
	{
		length_c = (float)(Math.sqrt(x*x + y*y + z*z)); 
		return length_c;
	}
	
	public float fastLength() 
	{
		float mag = x*x + y*y + z*z;
		return mag * Common.FastInvSqrtf(mag);
	}
	
	public float invLength() 
	{
		length_c = (float)(Math.sqrt(x*x + y*y + z*z)); 
		return (float)(1 / length_c);
	}
	
	public float invFastLength() 
	{
		float mag = x*x + y*y + z*z;
		return Common.FastInvSqrtf(mag);
	}
	
	public void scale(float c) 
	{
		this.x *= c;
		this.y *= c;
		this.z *= c;
	}
	
	public void normalize() 
	{
		float inv_length = (float)(1 / length());
		scale(inv_length);
	}
	
	public void fastNormalize() 
	{
		float	mag = x*x + y*y + z*z,
				inv_length = Common.FastInvSqrtf(mag);
		
		scale(inv_length);
	}
	
	public void transform(Matrix3x3f M) 
	{
		float 	n_x = this.x * M.c[0] + this.y * M.c[1] + this.z * M.c[2],
				n_y = this.x * M.c[3] + this.y * M.c[4] + this.z * M.c[5],
				n_z = this.x * M.c[6] + this.y * M.c[7] + this.z * M.c[8];
		
		this.x = n_x;
		this.y = n_y;
		this.z = n_z;
	}
	
	public void add(Vector3f a) 
	{
		this.x += a.x;
		this.y += a.y;
		this.z += a.z;
	}
	
	public void sub(Vector3f a) 
	{
		this.x -= a.x;
		this.y -= a.y;
		this.z -= a.z;
	}
	
	public static Vector3f transform(Matrix3x3f M, Vector3f v) 
	{
		return new Vector3f(	v.x * M.c[0] + v.y * M.c[1] + v.z * M.c[2],
								v.x * M.c[3] + v.y * M.c[4] + v.z * M.c[5],
								v.x * M.c[6] + v.y * M.c[7] + v.z * M.c[8]	);
	}
	
	public static Vector3f add(Vector3f a, Vector3f b) 
	{
		return new Vector3f(a.x + b.x, 
							a.y + b.y, 
							a.z + b.z);
	}
	
	public static Vector3f sub(Vector3f a, Vector3f b) 
	{
		return new Vector3f(a.x - b.x, 
							a.y - b.y, 
							a.z - b.z);
	}
	
	public static Vector3f scale(Vector3f v, float c) 
	{
		return new Vector3f(v.x*c,
							v.y*c,
							v.z*c);
	}
	
	public static float dot(Vector3f a, Vector3f b) 
	{
		float dot = (	a.x*b.x +
						a.y*b.y + 
						a.z*b.z	);
		return dot;
	}
	
	public static Vector3f cross(Vector3f a, Vector3f b) 
	{
		return new Vector3f(a.y*b.z - a.z*b.y,
							a.z*b.x - a.x*b.z,
							a.x*b.y - a.y*b.x);
	}
	
	public static Vector3f normalize(Vector3f xyz) 
	{
		float inv_mag = (float)1 / xyz.length();
		return Vector3f.scale(xyz, inv_mag);
	}
	
	public static Vector3f fastNormalize(Vector3f xyz) 
	{
		float inv_mag = xyz.invFastLength();
		return Vector3f.scale(xyz, inv_mag);
	}
	
	public String toString() 
	{
		StringBuilder v_str = new StringBuilder(Float.toString(this.x));
		v_str.append(", ");
		
		v_str.append(Float.toString(this.y));
		v_str.append(", ");
		
		v_str.append(Float.toString(this.z));
		return v_str.toString();
	}
}