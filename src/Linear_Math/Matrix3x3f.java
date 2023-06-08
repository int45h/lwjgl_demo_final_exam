package Linear_Math;

import java.util.*;
public final class Matrix3x3f
{
	public float[]	c = new float[9];
	
	public Matrix3x3f() 
	{
		Arrays.fill(c, 0);
		c[0] = c[4] = c[8] = 1;
	}
	
	public Matrix3x3f(Matrix2x2f m)
	{
		Arrays.fill(c, 0);
		this.c[0] = m.c[0];
		this.c[1] = m.c[1];
		this.c[3] = m.c[2];
		this.c[4] = m.c[3];
	}
	
	public Matrix3x3f(Matrix3x3f m)
	{
		this.c[0] = m.c[0];
		this.c[1] = m.c[1];
		this.c[2] = m.c[2];
		this.c[3] = m.c[3];
		this.c[4] = m.c[4];
		this.c[5] = m.c[5];
		this.c[6] = m.c[6];
		this.c[7] = m.c[7];
		this.c[8] = m.c[8];
	}
	
	public Matrix3x3f(Matrix4x4f m)
	{
		this.c[0] = m.c[0];
		this.c[1] = m.c[1];
		this.c[2] = m.c[2];
		this.c[3] = m.c[4];
		this.c[4] = m.c[5];
		this.c[5] = m.c[6];
		this.c[6] = m.c[8];
		this.c[7] = m.c[9];
		this.c[8] = m.c[10];
	}
	
	public Matrix3x3f(	float c0, float c1, float c2, 
						float c3, float c4, float c5, 
						float c6, float c7, float c8)
	{
		this.c[0] = c0;
		this.c[1] = c1;
		this.c[2] = c2;
		this.c[3] = c3;
		this.c[4] = c4;
		this.c[5] = c5;
		this.c[6] = c6;
		this.c[7] = c7;
		this.c[8] = c8;
	}
	
	public float ij(int i, int j) 
	{
		return c[i*3 + j];
	}
	
	public float ij(int i, int j, float val) 
	{
		return (c[i*3 + j] = val);
	}
	
	public void setRow(Vector3f v, int i) 
	{
		this.ij(i, 0, v.x);
		this.ij(i, 1, v.y);
		this.ij(i, 2, v.z);
	}
	
	public void setCol(Vector3f v, int j) 
	{
		this.ij(0, j, v.x);
		this.ij(1, j, v.y);
		this.ij(2, j, v.z);
	}
	
	public Vector3f getRow(int i) 
	{
		return new Vector3f(this.ij(i, 0), this.ij(i, 1), this.ij(i, 2));
	}
	
	public Vector3f getCol(int j) 
	{
		return new Vector3f(this.ij(0, j), this.ij(1, j), this.ij(2, j));
	}
	
	public Vector3f diagonal() 
	{
		return new Vector3f(this.c[0], this.c[4], this.c[8]);
	}
	
	public void add(Matrix3x3f b) 
	{
		this.c[0] += b.c[0];
		this.c[1] += b.c[1];
		this.c[2] += b.c[2];
		this.c[3] += b.c[3];
		this.c[4] += b.c[4];
		this.c[5] += b.c[5];
		this.c[6] += b.c[6];
		this.c[7] += b.c[7];
		this.c[8] += b.c[8];
	}
	
	public void sub(Matrix3x3f b) 
	{
		this.c[0] -= b.c[0];
		this.c[1] -= b.c[1];
		this.c[2] -= b.c[2];
		this.c[3] -= b.c[3];
		this.c[4] -= b.c[4];
		this.c[5] -= b.c[5];
		this.c[6] -= b.c[6];
		this.c[7] -= b.c[7];
		this.c[8] -= b.c[8];
	}
	
	public void scale(float c) 
	{
		this.c[0] *= c;
		this.c[1] *= c;
		this.c[2] *= c;
		this.c[3] *= c;
		this.c[4] *= c;
		this.c[5] *= c;
		this.c[6] *= c;
		this.c[7] *= c;
		this.c[8] *= c;
	}
	
	public float det() 
	{
		float	det_a1 =  (c[4] * c[8]) - (c[5] * c[7]),
				det_a2 =  (c[3] * c[8]) - (c[5] * c[6]),
				det_a3 =  (c[3] * c[7]) - (c[4] * c[6]);
						
		return (c[0] * det_a1) - (c[1] * det_a2) + (c[2] * det_a3);
	}
	
	public void transpose() 
	{
		float 	c0 = this.c[0], 
				c1 = this.c[3], 
				c2 = this.c[6], 
				c3 = this.c[1],
				c4 = this.c[4], 
				c5 = this.c[7], 
				c6 = this.c[2], 
				c7 = this.c[5],
				c8 = this.c[8]; 
		
		this.c[0] = c0;
		this.c[1] = c1;
		this.c[2] = c2;
		this.c[3] = c3;
		this.c[4] = c4;
		this.c[5] = c5;
		this.c[6] = c6;
		this.c[7] = c7;
		this.c[8] = c8;
	}
	
	public Matrix2x2f getMatrix2x2f(int i, int j) 
	{
		Matrix2x2f M = new Matrix2x2f();
		switch (i*3 + j) 
		{
		case 0: M.c[0] = c[4]; M.c[1] = c[5]; 
				M.c[2] = c[7]; M.c[3] = c[8]; break;
		case 1: M.c[0] = c[3]; M.c[1] = c[5]; 
				M.c[2] = c[6]; M.c[3] = c[8]; break;
		case 2: M.c[0] = c[3]; M.c[1] = c[4]; 
				M.c[2] = c[6]; M.c[3] = c[7]; break;
				
		case 3: M.c[0] = c[1]; M.c[1] = c[2]; 
				M.c[2] = c[7]; M.c[3] = c[8]; break;
		case 4: M.c[0] = c[0]; M.c[1] = c[2]; 
				M.c[2] = c[6]; M.c[3] = c[8]; break;
		case 5: M.c[0] = c[0]; M.c[1] = c[1]; 
				M.c[2] = c[6]; M.c[3] = c[7]; break;
				
		case 6: M.c[0] = c[1]; M.c[1] = c[2]; 
				M.c[2] = c[4]; M.c[3] = c[5]; break;
		case 7: M.c[0] = c[0]; M.c[1] = c[2]; 
				M.c[2] = c[3]; M.c[3] = c[5]; break;
		case 8: M.c[0] = c[0]; M.c[1] = c[1]; 
				M.c[2] = c[3]; M.c[3] = c[4]; break;
		}
		return M;
	}
	
	public Matrix3x3f adj()
	{
		float	d_a0	= c[4]*c[8] - c[5]*c[7],
				d_a1	= c[3]*c[8] - c[5]*c[6],
				d_a2	= c[3]*c[7] - c[4]*c[6],
				
				d_a3	= c[1]*c[8] - c[2]*c[7],
				d_a4	= c[0]*c[8] - c[2]*c[6],
				d_a5	= c[0]*c[7] - c[1]*c[6],
				
				d_a6	= c[1]*c[5] - c[2]*c[4],
				d_a7	= c[0]*c[5] - c[2]*c[3],
				d_a8	= c[0]*c[4] - c[1]*c[3];

		return new Matrix3x3f(	+d_a0, -d_a3, +d_a6, 
								-d_a1, +d_a4, -d_a7, 
								+d_a2, -d_a5, +d_a8	);
	}
	
	public static Matrix3x3f add(Matrix3x3f a, Matrix3x3f b) 
	{
		return new Matrix3x3f(	a.c[0] + b.c[0],
								a.c[1] + b.c[1],
								a.c[2] + b.c[2],
								a.c[3] + b.c[3],
								a.c[4] + b.c[4],
								a.c[5] + b.c[5],
								a.c[6] + b.c[6],
								a.c[7] + b.c[7],
								a.c[8] + b.c[8]	);
	}
	
	public static Matrix3x3f sub(Matrix3x3f a, Matrix3x3f b) 
	{
		return new Matrix3x3f(	a.c[0] - b.c[0],
								a.c[1] - b.c[1],
								a.c[2] - b.c[2],
								a.c[3] - b.c[3],
								a.c[4] - b.c[4],
								a.c[5] - b.c[5],
								a.c[6] - b.c[6],
								a.c[7] - b.c[7],
								a.c[8] - b.c[8]	);
	}
	
	public static Matrix3x3f multiplyVec(Matrix3x3f m, Vector3f v) 
	{
		return new Matrix3x3f(	v.x * m.c[0], v.y * m.c[1], v.z * m.c[2], 
								v.x * m.c[3], v.y * m.c[4], v.z * m.c[5],
								v.x * m.c[6], v.y * m.c[7], v.z * m.c[8]	);
	}
	
	public static Matrix3x3f multiplyMat(Matrix3x3f Ma, Matrix3x3f Mb) 
	{
		float	d_a0 = Ma.c[0]*Mb.c[0] + Ma.c[1]*Mb.c[3] + Ma.c[2]*Mb.c[6],
				d_a1 = Ma.c[0]*Mb.c[1] + Ma.c[1]*Mb.c[4] + Ma.c[2]*Mb.c[7],
				d_a2 = Ma.c[0]*Mb.c[2] + Ma.c[1]*Mb.c[5] + Ma.c[2]*Mb.c[8],
				d_a3 = Ma.c[3]*Mb.c[0] + Ma.c[4]*Mb.c[3] + Ma.c[5]*Mb.c[6],
				d_a4 = Ma.c[3]*Mb.c[1] + Ma.c[4]*Mb.c[4] + Ma.c[5]*Mb.c[7],
				d_a5 = Ma.c[3]*Mb.c[2] + Ma.c[4]*Mb.c[5] + Ma.c[5]*Mb.c[8],
				d_a6 = Ma.c[6]*Mb.c[0] + Ma.c[7]*Mb.c[3] + Ma.c[8]*Mb.c[6],
				d_a7 = Ma.c[6]*Mb.c[1] + Ma.c[7]*Mb.c[4] + Ma.c[8]*Mb.c[7],
				d_a8 = Ma.c[6]*Mb.c[2] + Ma.c[7]*Mb.c[5] + Ma.c[8]*Mb.c[8];
																		
		return new Matrix3x3f(	d_a0, d_a1, d_a2,
								d_a3, d_a4, d_a5,
								d_a6, d_a7, d_a8	);
	}
	
	public static float det(Matrix3x3f m) 
	{
		float	det_a1 =  (m.c[4] * m.c[8]) - (m.c[5] * m.c[7]),
				det_a2 =  (m.c[3] * m.c[8]) - (m.c[5] * m.c[6]),
				det_a3 =  (m.c[3] * m.c[7]) - (m.c[4] * m.c[6]);
						
		return (m.c[0] * det_a1) - (m.c[1] * det_a2) + (m.c[2] * det_a3);
	}
	
	public static Matrix3x3f identity() 
	{
		return new Matrix3x3f();
	}
	
	public static Matrix3x3f transpose(Matrix3x3f M) 
	{
		return new Matrix3x3f(	M.c[0], M.c[3], M.c[6], 
								M.c[1], M.c[4], M.c[7], 
								M.c[2], M.c[5], M.c[8]	);
	}
	
	public static Matrix3x3f inverse(Matrix3x3f M) 
	{
		float inv_det = (float)1 / M.det();
		Matrix3x3f M_inv = M.adj();
		M_inv.scale(inv_det);
		
		return M_inv;
	}
	
	public String toString() 
	{
		StringBuilder mat_str = new StringBuilder(Float.toString(c[0]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[1]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[2]));
		
		mat_str.append(", \n");
		mat_str.append(Float.toString(c[3]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[4]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[5]));
		
		mat_str.append(", \n");
		mat_str.append(Float.toString(c[6]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[7]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[8]));
		
		mat_str.append("\n");
		
		return mat_str.toString();
	}
}