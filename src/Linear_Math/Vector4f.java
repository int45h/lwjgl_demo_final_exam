package Linear_Math;
import java.lang.Math;

public final class Vector4f
{
	public float 	x, y, z, w, 
					length_c,	// Length  
					length_ac;	// Length (approximate)
	public Vector4f()
	{
		x = y = z = w = 0.f;
	}
	
	public Vector4f(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4f(Vector2f v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = 1.f;
		this.w = 1.f;		
	}
	
	public Vector4f(Vector3f v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = 1.f;
	}
	
	public Vector4f(Vector4f v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = v.w;
	}
	
	public Vector4f(Quaternion4f q)
	{
		this.x = q.r;
		this.y = q.i;
		this.z = q.j;
		this.w = q.k;
	}
	
	public void set(float x, float y, float z, float w) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public void set(Vector4f v) 
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = v.w;
	}
	
	public float length() 
	{
		length_c = (float)(Math.sqrt(x*x + y*y + z*z + w*w)); 
		return length_c;
	}
	
	public float fastLength() 
	{
		float mag = x*x + y*y + z*z + w*w;
		return mag * Common.FastInvSqrtf(mag);
	}
	
	public float invLength() 
	{
		length_c = (float)(Math.sqrt(x*x + y*y + z*z + w*w)); 
		return (float)(1 / length_c);
	}
	
	public float invFastLength() 
	{
		float mag = x*x + y*y + z*z + w*w;
		return Common.FastInvSqrtf(mag);
	}
	
	public void scale(float c) 
	{
		this.x *= c;
		this.y *= c;
		this.z *= c;
		this.w *= c;
	}
	
	public void normalize() 
	{
		float inv_length = (float)(1 / length());
		scale(inv_length);
	}
	
	public void fastNormalize() 
	{
		float	mag = x*x + y*y + z*z + w*w,
				inv_length = Common.FastInvSqrtf(mag);
		
		scale(inv_length);
	}
	
	public void transform(Matrix4x4f M) 
	{
		float 	n_x = this.x * M.c[0] 	+ this.y * M.c[1]	+ this.z * M.c[2]	+ this.w * M.c[3],
				n_y = this.x * M.c[4] 	+ this.y * M.c[5] 	+ this.z * M.c[6] 	+ this.w * M.c[7],
				n_z = this.x * M.c[8] 	+ this.y * M.c[9] 	+ this.z * M.c[10] 	+ this.w * M.c[11],
				n_w = this.x * M.c[12] 	+ this.y * M.c[13] 	+ this.z * M.c[14] 	+ this.w * M.c[15];
		
		this.x = n_x;
		this.y = n_y;
		this.z = n_z;
		this.w = n_w;
	}
	
	public static Vector4f transform(Matrix4x4f M, Vector4f v) 
	{
		return new Vector4f(	v.x * M.c[0] 	+ v.y * M.c[1]	+ v.z * M.c[2]	+ v.w * M.c[3],
								v.x * M.c[4] 	+ v.y * M.c[5]	+ v.z * M.c[6]	+ v.w * M.c[7],
								v.x * M.c[8] 	+ v.y * M.c[9]	+ v.z * M.c[10]	+ v.w * M.c[11],
								v.x * M.c[12]	+ v.y * M.c[13]	+ v.z * M.c[14]	+ v.w * M.c[15]	);
	}
	
	public static Vector4f add(Vector4f a, Vector4f b) 
	{
		return new Vector4f(a.x + b.x, 
							a.y + b.y, 
							a.z + b.z,
							a.w + b.w);
	}
	
	public static Vector4f sub(Vector4f a, Vector4f b) 
	{
		return new Vector4f(a.x - b.x, 
							a.y - b.y, 
							a.z - b.z,
							a.w - b.w);
	}
	
	public static float dot(Vector4f a, Vector4f b) 
	{
		float dot = (	a.x*b.x +
						a.y*b.y + 
						a.z*b.z	+ 
						a.w*b.w);
		return dot;
	}
	
	public String toString() 
	{
		StringBuilder v_str = new StringBuilder(Float.toString(this.x));
		v_str.append(", ");
		
		v_str.append(Float.toString(this.y));
		v_str.append(", ");
		
		v_str.append(Float.toString(this.z));
		v_str.append(", ");
		
		v_str.append(Float.toString(this.w));
		return v_str.toString();
	}
}