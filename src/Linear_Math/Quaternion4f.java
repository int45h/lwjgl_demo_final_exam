package Linear_Math;

public final class Quaternion4f
{
	public float r, i, j, k;
	
	public Quaternion4f()
	{
		r = i = j = k = 0;
	}
	
	public Quaternion4f(Vector4f v)
	{
		this.r = v.x;
		this.i = v.y;
		this.j = v.z;
		this.k = v.w;
	}
	
	public Quaternion4f(float r, float i, float j, float k)
	{
		this.r = r;
		this.i = i;
		this.j = j;
		this.k = k;
	}
	
	public Quaternion4f(float r, Vector3f ijk)
	{
		this.r = r;
		this.i = ijk.x;
		this.j = ijk.y;
		this.k = ijk.z;
	}
	
	public static Quaternion4f eulerAngleToQuat(float x, float y, float z) 
	{
		x *= 0.5f;
		y *= 0.5f;
		z *= 0.5f;
		
		float 	x_2cos = (float)Math.cos(x),
				y_2cos = (float)Math.cos(y),
				z_2cos = (float)Math.cos(z),
				x_2sin = (float)Math.sin(x),
				y_2sin = (float)Math.sin(y),
				z_2sin = (float)Math.sin(z);		
		
		return new Quaternion4f(	(x_2cos * y_2cos * z_2cos) + (x_2sin * y_2sin * z_2sin),
									(x_2sin * y_2cos * z_2cos) - (x_2cos * y_2sin * z_2sin),
									(x_2cos * y_2sin * z_2cos) + (x_2sin * y_2cos * z_2sin),
									(x_2cos * y_2cos * z_2sin) - (x_2sin * y_2sin * z_2cos)	);
	}
	
	public static Quaternion4f eulerAngleToQuat(Vector3f xyz) 
	{
		xyz.x *= 0.5f;
		xyz.y *= 0.5f;
		xyz.z *= 0.5f;
		
		float 	x_2cos = (float)Math.cos(xyz.x),
				y_2cos = (float)Math.cos(xyz.y),
				z_2cos = (float)Math.cos(xyz.z),
				x_2sin = (float)Math.sin(xyz.x),
				y_2sin = (float)Math.sin(xyz.y),
				z_2sin = (float)Math.sin(xyz.z);		
		
		return new Quaternion4f(	(x_2cos * y_2cos * z_2cos) + (x_2sin * y_2sin * z_2sin),
									(x_2sin * y_2cos * z_2cos) - (x_2cos * y_2sin * z_2sin),
									(x_2cos * y_2sin * z_2cos) + (x_2sin * y_2cos * z_2sin),
									(x_2cos * y_2cos * z_2sin) - (x_2sin * y_2sin * z_2cos)	);
	}
	
	public void eulerToQuat(float x, float y, float z) 
	{
		x *= 0.5f;
		y *= 0.5f;
		z *= 0.5f;
		
		float 	x_2cos = (float)Math.cos(x),
				y_2cos = (float)Math.cos(y),
				z_2cos = (float)Math.cos(z),
				x_2sin = (float)Math.sin(x),
				y_2sin = (float)Math.sin(y),
				z_2sin = (float)Math.sin(z);		
		
		this.r	= (x_2cos * y_2cos * z_2cos) + (x_2sin * y_2sin * z_2sin);
		this.i 	= (x_2sin * y_2cos * z_2cos) - (x_2cos * y_2sin * z_2sin);
		this.j	= (x_2cos * y_2sin * z_2cos) + (x_2sin * y_2cos * z_2sin);
		this.k 	= (x_2cos * y_2cos * z_2sin) - (x_2sin * y_2sin * z_2cos);
	}
	
	public void eulerToQuat(Vector3f xyz) 
	{
		xyz.x *= 0.5f;
		xyz.y *= 0.5f;
		xyz.z *= 0.5f;
		
		float 	x_2cos = (float)Math.cos(xyz.x),
				y_2cos = (float)Math.cos(xyz.y),
				z_2cos = (float)Math.cos(xyz.z),
				x_2sin = (float)Math.sin(xyz.x),
				y_2sin = (float)Math.sin(xyz.y),
				z_2sin = (float)Math.sin(xyz.z);		
		
		this.r	= (x_2cos * y_2cos * z_2cos) + (x_2sin * y_2sin * z_2sin);
		this.i 	= (x_2sin * y_2cos * z_2cos) - (x_2cos * y_2sin * z_2sin);
		this.j	= (x_2cos * y_2sin * z_2cos) + (x_2sin * y_2cos * z_2sin);
		this.k 	= (x_2cos * y_2cos * z_2sin) - (x_2sin * y_2sin * z_2cos);
	}
	
	public static Quaternion4f add(Quaternion4f a, Quaternion4f b) 
	{
		 return new Quaternion4f(	a.r + b.r,
				 					a.i + b.i,
				 					a.j + b.j,
				 					a.k + b.k);
	}
	
	public static Quaternion4f sub(Quaternion4f a, Quaternion4f b) 
	{
		 return new Quaternion4f(	a.r - b.r,
				 					a.i - b.i,
				 					a.j - b.j,
				 					a.k - b.k);
	}
	
	public void scale(float c) 
	{
		this.r *= c;
		this.i *= c;
		this.j *= c;
		this.k *= c;
	}
	
	public void inverse() 
	{
		float inv_mag = (float)(1 / (r*r + i*i + j*j + k*k));
		scale(inv_mag);
	}
	
	public void conjugate() 
	{
		this.i *= -1.f;
		this.j *= -1.f;
		this.k *= -1.f;
	}
	
	public void setRotAxisRads(float theta, Vector3f axis) 
	{
		float sin_v2	= (float)Math.sin(0.5f * theta) * axis.invLength();
		float cos_2		= (float)Math.cos(0.5f * theta);
		
		this.r = cos_2;
		this.i = sin_v2 * axis.x;
		this.j = sin_v2 * axis.y;
		this.k = sin_v2 * axis.z;
	}
	
	public void setRotAxisRadsFast(float theta, Vector3f axis) 
	{
		float sin_v2	= (float)Math.sin(0.5f * theta) * axis.invFastLength();
		float cos_2		= (float)Math.cos(0.5f * theta);
		
		this.r = cos_2;
		this.i = sin_v2 * axis.x;
		this.j = sin_v2 * axis.y;
		this.k = sin_v2 * axis.z;
	}
	
	public void setRotAxisDegs(float theta, Vector3f axis) 
	{
		theta *= (float)Math.PI / 180.f;
		setRotAxisRads(theta, axis);
	}
	
	public void setRotAxisDegsFast(float theta, Vector3f axis) 
	{
		theta *= (float)Math.PI / 180.f;
		setRotAxisRadsFast(theta, axis);
	}
	
	public Vector3f getEulerAngles() 
	{
		return new Vector3f((float)Math.atan2(2*(r*i + j*k), 1-2*(i*i + j*j)),
							(float)Math.asin(2*(r*j-i*k)),
							(float)Math.atan2(2*(r*k + i*j), 1-2*(j*j + k*k)));
	}
	
	public void getEulerAngles(Vector3f rotation) 
	{
		rotation.x = (float)Math.atan2(2*(r*i + j*k), 1-2*(i*i + j*j));
		rotation.y = (float)Math.asin(2*(r*j-i*k));
		rotation.z = (float)Math.atan2(2*(r*k + i*j), 1-2*(j*j + k*k));
	}
	
	public Vector3f multiplyVec(Vector3f v) 
	{
		Vector3f 	ijk		=	new Vector3f(this.i, this.j, this.k),
					t		= 	Vector3f.scale(Vector3f.cross(ijk, v), 2), 
					v_prime = 	Vector3f.add(v, 
								Vector3f.add(Vector3f.scale(t, this.r), Vector3f.cross(ijk, t))
								);
		
		return v_prime;
	}
	
	public static Vector3f multiplyVec(Quaternion4f q, Vector3f v) 
	{
		Vector3f 	ijk		=	new Vector3f(q.i, q.j, q.k),
					t		= 	Vector3f.scale(Vector3f.cross(ijk, v), 2),
					v_prime = 	Vector3f.add(v, 
								Vector3f.add(Vector3f.scale(t, q.r), Vector3f.cross(ijk, t))
								);
								
		return v_prime;
	}
	
	public Vector3f rotate(Vector3f P) 
	{
		return multiplyVec(P);
	}
	
	// Quaternion derived rotation natrix, from https://www.cprogramming.com/tutorial/3d/quaternions.html
	public Matrix4x4f getRotationMatrix() 
	{
		float 	w = this.r,
				x = this.i,
				y = this.j,
				z = this.k,
				W = w*w,
				X = x*x,
				Y = y*y,
				Z = z*z;
		
		return new Matrix4x4f(	1-2*Y-2*Z, 2*x*y - 2*w*z, 2*x*z + 2*w*y, 0,
								2*x*y + 2*w*z, 1-2*X-2*Z, 8*y*z + 2*w*x, 0,
								2*x*z - 2*w*y, 2*y*z - 2*w*x, 1-2*X-2*y, 0,
								0, 0, 0, 1									);
	}
	
	// Quaternion derived rotation natrix, from https://www.cprogramming.com/tutorial/3d/quaternions.html
	public void getRotationMatrix(Matrix4x4f M) 
	{
		float 	w = this.r,
				x = this.i,
				y = this.j,
				z = this.k,
				W = w*w,
				X = x*x,
				Y = y*y,
				Z = z*z;
			
		M.set(	1-2*Y-2*Z, 2*x*y + 2*w*z, 2*x*z - 2*w*y, 0,
				2*x*y - 2*w*z, 1-2*X-2*Z, 2*y*z + 2*w*x, 0,
				2*x*z + 2*w*y, 2*y*z - 2*w*x, 1-2*X-2*y, 0,
				0, 0, 0, 1									);
	}
		
	
	public String toString() 
	{
		StringBuilder q_str = new StringBuilder(Float.toString(this.r));
		q_str.append(" + ");
		
		q_str.append(Float.toString(this.i));
		q_str.append("i + ");
		
		q_str.append(Float.toString(this.j));
		q_str.append("j + ");
		
		q_str.append(Float.toString(this.k));
		q_str.append("k");
		
		return q_str.toString();
	}
}