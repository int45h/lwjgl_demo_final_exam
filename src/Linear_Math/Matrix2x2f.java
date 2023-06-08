package Linear_Math;

import java.util.*;
public final class Matrix2x2f
{
	public float[]	c = new float[4];
	
	public Matrix2x2f() 
	{
		Arrays.fill(c, 0);
		c[0] = c[3] = 1;
	}
	
	public Matrix2x2f(Matrix2x2f m)
	{
		this.c[0] = m.c[0];
		this.c[1] = m.c[1];
		this.c[2] = m.c[2];
		this.c[3] = m.c[3];
	}
	
	public Matrix2x2f(float c0, float c1, float c2, float c3)
	{
		this.c[0] = c0;
		this.c[1] = c1;
		this.c[2] = c2;
		this.c[3] = c3;
	}
	
	public float ij(int i, int j) 
	{
		return c[i*2 + j];
	}
	
	public float ij(int i, int j, float val) 
	{
		return (c[i*2 + j] = val);
	}
	
	public void setRow(Vector2f v, int i) 
	{
		this.ij(i, 0, v.x);
		this.ij(i, 1, v.y);
	}
	
	public void setCol(Vector2f v, int j) 
	{
		this.ij(0, j, v.x);
		this.ij(1, j, v.y);
	}
	
	public Vector2f getRow(int i) 
	{
		return new Vector2f(this.ij(i, 0), this.ij(i, 1));
	}
	
	public Vector2f getCol(int j) 
	{
		return new Vector2f(this.ij(0, j), this.ij(1, j));
	}
	
	public Vector2f diagonal() 
	{
		return new Vector2f(this.c[0], this.c[3]);
	}
	
	public void add(Matrix2x2f b) 
	{
		this.c[0] += b.c[0];
		this.c[1] += b.c[1];
		this.c[2] += b.c[2];
		this.c[3] += b.c[3];
	}
	
	public void sub(Matrix2x2f b) 
	{
		this.c[0] -= b.c[0];
		this.c[1] -= b.c[1];
		this.c[2] -= b.c[2];
		this.c[3] -= b.c[3];
	}
	
	public void scale(float c) 
	{
		this.c[0] *= c;
		this.c[1] *= c;
		this.c[2] *= c;
		this.c[3] *= c;
	}
	
	public float det() 
	{
		return (c[0] * c[3]) - (c[1] * c[2]);
	}
	
	public void transpose() 
	{
		float 	c0 = this.c[0], 
				c1 = this.c[2], 
				c2 = this.c[1], 
				c3 = this.c[3];
		
		this.c[0] = c0;
		this.c[1] = c1;
		this.c[2] = c2;
		this.c[3] = c3;
	}
	
	public static Matrix2x2f add(Matrix2x2f a, Matrix2x2f b) 
	{
		return new Matrix2x2f(	a.c[0] + b.c[0],
								a.c[1] + b.c[1],
								a.c[2] + b.c[2],
								a.c[3] + b.c[3]	);
	}
	
	public static Matrix2x2f sub(Matrix2x2f a, Matrix2x2f b) 
	{
		return new Matrix2x2f(	a.c[0] - b.c[0],
								a.c[1] - b.c[1],
								a.c[2] - b.c[2],
								a.c[3] - b.c[3]	);
	}
	
	public static Matrix2x2f multiplyVec(Matrix2x2f m, Vector2f v) 
	{
		return new Matrix2x2f(	v.x * m.c[0], v.y * m.c[1],
								v.x * m.c[2], v.y * m.c[3]	);
	}
	
	public static Matrix2x2f multiplyMat(Matrix2x2f Ma, Matrix2x2f Mb) 
	{
		return new Matrix2x2f(	Ma.c[0] * Mb.c[0] + Ma.c[1] * Mb.c[2], Ma.c[0] * Mb.c[1] + Ma.c[1] * Mb.c[3],
								Ma.c[2] * Mb.c[0] + Ma.c[3] * Mb.c[2], Ma.c[2] * Mb.c[1] + Ma.c[3] * Mb.c[3]	);
	}
	
	public static float det(Matrix2x2f m) 
	{
		return (m.c[0] * m.c[3]) - (m.c[1] * m.c[2]);
	}
	
	public static Matrix2x2f identity() 
	{
		return new Matrix2x2f();
	}
	
	public static Matrix2x2f transpose(Matrix2x2f M) 
	{
		return new Matrix2x2f(	M.c[0], M.c[2],
								M.c[1], M.c[3]	);
	}
	
	public static Matrix2x2f inverse(Matrix2x2f M) 
	{
		float inv_det = (float)1 / M.det();
		Matrix2x2f adj = new Matrix2x2f(	+M.c[3],	-M.c[1],
											-M.c[2],	+M.c[0]	);
		adj.scale(inv_det);
		
		return adj;
	}
	
	public String toString() 
	{
		StringBuilder mat_str = new StringBuilder(Float.toString(c[0]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[1]));
		
		mat_str.append(",\n");
		mat_str.append(Float.toString(c[2]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[3]));
		
		mat_str.append("\n");
		
		return mat_str.toString();
	}
}