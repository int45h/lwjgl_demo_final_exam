package Linear_Math;

import java.math.*;

public final class Affine
{
	public static Matrix4x4f translate(Vector3f point) 
	{
		return new Matrix4x4f(	1, 0, 0, point.x,
								0, 1, 0, point.y, 
								0, 0, 1, point.z,
								0, 0, 0, 1					);
	}
	
	public static Matrix4x4f translate(float x, float y, float z) 
	{
		return new Matrix4x4f(	1, 0, 0, x,
								0, 1, 0, y, 
								0, 0, 1, z,
								0, 0, 0, 1					);
	}
	
	public static Matrix4x4f translate(Matrix4x4f M, Vector3f point) 
	{
		M.c[3]	= point.x;
		M.c[7]	= point.y;
		M.c[11]	= point.z;
		
		return M;
	}
	
	public static Matrix4x4f translate(Matrix4x4f M, float x, float y, float z) 
	{
		M.c[3]	= x;
		M.c[7]	= y;
		M.c[11]	= z;
		
		return M;
	}
	
	public static Matrix4x4f scale(Vector3f xyz, Vector3f point) 
	{
		return new Matrix4x4f(	xyz.x * point.x, 0, 0, 0,
								0, xyz.y * point.y, 0, 0, 
								0, 0, xyz.z * point.z, 0,
								0, 0, 0, 1					);
	}
	
	public static Matrix4x4f scale(Matrix4x4f M, Vector3f xyz, Vector3f point) 
	{
		M.c[0] 	= point.x * xyz.x; 
		M.c[5] 	= point.y * xyz.y; 
		M.c[10]	= point.z * xyz.z; 
		M.c[15]	= 1;
		
		return M;
	}
	
	public static Matrix4x4f scale(Matrix4x4f M, float f, Vector3f point) 
	{
		M.c[0] 	= point.x * f;
		M.c[5] 	= point.y * f;
		M.c[10]	= point.z * f;
		M.c[15]	= 1;
		
		return M;
	}
	
	public static Matrix4x4f scale(Matrix4x4f M, float x, float y, float z, Vector3f point) 
	{
		M.c[0] 	= point.x * x;
		M.c[5] 	= point.y * y;
		M.c[10]	= point.z * z;
		M.c[15]	= 1;
		
		return M;
	}
	
	public static Matrix4x4f reflection(Vector3f xyz, Vector3f point) 
	{
		float 	x_axis = (float)Math.signum(xyz.x),
				y_axis = (float)Math.signum(xyz.y),
				z_axis = (float)Math.signum(xyz.z);
		
		return new Matrix4x4f(	xyz.x * x_axis, 0, 0, 0, 
								0, xyz.y * y_axis, 0, 0,
								0, 0, xyz.z * z_axis, 0,
								0, 0, 0, 1					);
	}
}