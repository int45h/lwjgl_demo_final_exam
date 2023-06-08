package Linear_Math;

import java.util.*;
public final class Matrix4x4f
{
	public float[]	c = new float[16];
	
	public Matrix4x4f() 
	{
		Arrays.fill(c, 0);
		c[0] = c[5] = c[10] = c[15] = 1;
	}
	
	public Matrix4x4f(Matrix4x4f m)
	{
		this.c[0]	= m.c[0];
		this.c[1]	= m.c[1];
		this.c[2]	= m.c[2];
		this.c[3]	= m.c[3];
		this.c[4]	= m.c[4];
		this.c[5]	= m.c[5];
		this.c[6]	= m.c[6];
		this.c[7]	= m.c[7];
		this.c[8]	= m.c[8];
		this.c[9]	= m.c[9];
		this.c[10]	= m.c[10];
		this.c[11] 	= m.c[11];
		this.c[12] 	= m.c[12];
		this.c[13] 	= m.c[13];
		this.c[14] 	= m.c[14];
		this.c[15] 	= m.c[15];
	}
	
	public Matrix4x4f(Matrix2x2f M) 
	{
		Arrays.fill(c, 0);
		this.c[0] = M.c[0];
		this.c[1] = M.c[1];
		this.c[4] = M.c[2];
		this.c[5] = M.c[3];
	}
	
	public Matrix4x4f(Matrix3x3f M) 
	{
		Arrays.fill(c, 0);
		this.c[0] 	= M.c[0];
		this.c[1] 	= M.c[1];
		this.c[2] 	= M.c[2];
		this.c[4] 	= M.c[3];
		this.c[5] 	= M.c[4];
		this.c[6] 	= M.c[5];
		this.c[8] 	= M.c[6];
		this.c[9] 	= M.c[7];
		this.c[10] 	= M.c[8];
	}
	
	public Matrix4x4f(	float c0, float c1, float c2, float c3, 
						float c4, float c5, float c6, float c7, 
						float c8, float c9, float c10, float c11, 
						float c12, float c13, float c14, float c15	)
	{
		this.c[0] 	= c0;
		this.c[1] 	= c1;
		this.c[2] 	= c2;
		this.c[3] 	= c3;
		this.c[4] 	= c4;
		this.c[5] 	= c5;
		this.c[6] 	= c6;
		this.c[7] 	= c7;
		this.c[8] 	= c8;
		this.c[9] 	= c9;
		this.c[10] 	= c10;
		this.c[11] 	= c11;
		this.c[12] 	= c12;
		this.c[13] 	= c13;
		this.c[14] 	= c14;
		this.c[15] 	= c15;
	}
	
	public float ij(int i, int j) 
	{
		return c[i*4 + j];
	}
	
	public float ij(int i, int j, float val) 
	{
		return (c[i*4 + j] = val);
	}
	
	public void setRow(Vector4f v, int i) 
	{
		this.ij(i, 0, v.x);
		this.ij(i, 1, v.y);
		this.ij(i, 2, v.z);
		this.ij(i, 3, v.w);
	}
	
	public void setCol(Vector4f v, int j) 
	{
		this.ij(0, j, v.x);
		this.ij(1, j, v.y);
		this.ij(2, j, v.z);
		this.ij(3, j, v.w);
	}
	
	public void setRow(float x, float y, float z, float w, int i) 
	{
		this.ij(i, 0, x);
		this.ij(i, 1, y);
		this.ij(i, 2, z);
		this.ij(i, 3, w);
	}
	
	public void setCol(float x, float y, float z, float w, int j) 
	{
		this.ij(0, j, x);
		this.ij(1, j, y);
		this.ij(2, j, z);
		this.ij(3, j, w);
	}
	
	public Vector4f getRow(int i) 
	{
		return new Vector4f(this.ij(i, 0), this.ij(i, 1), this.ij(i, 2), this.ij(i, 3));
	}
	
	public Vector4f getCol(int j) 
	{
		return new Vector4f(this.ij(0, j), this.ij(1, j), this.ij(2, j), this.ij(3, j));
	}
	
	public Vector4f diagonal() 
	{
		return new Vector4f(this.c[0], this.c[5], this.c[10], this.c[15]);
	}
	
	public void add(Matrix4x4f b) 
	{
		this.c[0]	+= b.c[0];
		this.c[1] 	+= b.c[1];
		this.c[2] 	+= b.c[2];
		this.c[3] 	+= b.c[3];
		this.c[4] 	+= b.c[4];
		this.c[5] 	+= b.c[5];
		this.c[6] 	+= b.c[6];
		this.c[7] 	+= b.c[7];
		this.c[8] 	+= b.c[8];
		this.c[9]	+= b.c[9];
		this.c[10] 	+= b.c[10];
		this.c[11] 	+= b.c[11];
		this.c[12] 	+= b.c[12];
		this.c[13] 	+= b.c[13];
		this.c[14] 	+= b.c[14];
		this.c[15] 	+= b.c[15];
	}
	
	public void sub(Matrix4x4f b) 
	{
		this.c[0]	+= b.c[0];
		this.c[1] 	+= b.c[1];
		this.c[2] 	+= b.c[2];
		this.c[3] 	+= b.c[3];
		this.c[4] 	+= b.c[4];
		this.c[5] 	+= b.c[5];
		this.c[6] 	+= b.c[6];
		this.c[7] 	+= b.c[7];
		this.c[8] 	+= b.c[8];
		this.c[9]	+= b.c[9];
		this.c[10] 	+= b.c[10];
		this.c[11] 	+= b.c[11];
		this.c[12] 	+= b.c[12];
		this.c[13] 	+= b.c[13];
		this.c[14] 	+= b.c[14];
		this.c[15] 	+= b.c[15];
	}
	
	public void scale(float c) 
	{
		this.c[0]	*= c;
		this.c[1] 	*= c;
		this.c[2] 	*= c;
		this.c[3] 	*= c;
		this.c[4] 	*= c;
		this.c[5] 	*= c;
		this.c[6] 	*= c;
		this.c[7] 	*= c;
		this.c[8] 	*= c;
		this.c[9]	*= c;
		this.c[10] 	*= c;
		this.c[11] 	*= c;
		this.c[12] 	*= c;
		this.c[13] 	*= c;
		this.c[14] 	*= c;
		this.c[15] 	*= c;
	}
	
	public float det() 
	{
		float   a = this.c[0],	b = this.c[1], 	c = this.c[2], 	d = this.c[3],
	            e = this.c[4], 	f = this.c[5], 	g = this.c[6], 	h = this.c[7],
	            i = this.c[8], 	j = this.c[9], 	k = this.c[10], l = this.c[11],
	            m = this.c[12], n = this.c[13], o = this.c[14], p = this.c[15];
	    
	    float	t_0 = k*p - l*o,
	    		t_1 = j*p - l*n,
	    		t_2 = j*o - k*n,
	    		t_3 = i*p - l*m,
	    		t_4 = i*o - k*m,
	    		t_5 = i*n - j*m;
	    
	    return (+ a * ( f * t_0 - g * t_1 + h * t_2 )
	            - b * ( e * t_0 - g * t_3 + h * t_4 )
	            + c * ( e * t_1 - f * t_3 + h * t_5 )
	            - d * ( e * t_2 - f * t_4 + g * t_5 ));
	}
	
	public void transpose() 
	{
		float 	c0 	= this.c[0], 
				c1 	= this.c[4], 
				c2 	= this.c[8], 
				c3 	= this.c[12],
				c4 	= this.c[1], 
				c5 	= this.c[5], 
				c6	= this.c[9], 
				c7 	= this.c[13],
				c8 	= this.c[2],
				c9 	= this.c[6],
				c10 = this.c[10],
				c11 = this.c[14],
				c12 = this.c[3],
				c13 = this.c[7],
				c14 = this.c[11],
				c15 = this.c[15];
		
		this.c[0]	= c0;
		this.c[1] 	= c1;
		this.c[2] 	= c2;
		this.c[3] 	= c3;
		this.c[4] 	= c4;
		this.c[5] 	= c5;
		this.c[6] 	= c6;
		this.c[7] 	= c7;
		this.c[8] 	= c8;
		this.c[9]	= c9;
		this.c[10] 	= c10;
		this.c[11] 	= c11;
		this.c[12] 	= c12;
		this.c[13] 	= c13;
		this.c[14] 	= c14;
		this.c[15] 	= c15;
	}
	
	public Matrix3x3f getMatrix3x3f(int i, int j) 
	{
		Matrix3x3f M = new Matrix3x3f();
		switch (i*4 + j) 
		{
		case 0:		M.c[0]	= this.ij(1, 1); M.c[1]	= this.ij(1, 2); M.c[2]	= this.ij(1, 3); 
					M.c[3]	= this.ij(2, 1); M.c[4]	= this.ij(2, 2); M.c[5]	= this.ij(2, 3); 
					M.c[6]	= this.ij(3, 1); M.c[7]	= this.ij(3, 2); M.c[8]	= this.ij(3, 3); break;
		case 1:		M.c[0]	= this.ij(1, 0); M.c[1]	= this.ij(1, 2); M.c[2]	= this.ij(1, 3); 
					M.c[3]	= this.ij(2, 0); M.c[4]	= this.ij(2, 2); M.c[5]	= this.ij(2, 3); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 2); M.c[8]	= this.ij(3, 3); break;
		case 2:		M.c[0]	= this.ij(1, 0); M.c[1]	= this.ij(1, 1); M.c[2]	= this.ij(1, 3); 
					M.c[3]	= this.ij(2, 0); M.c[4]	= this.ij(2, 1); M.c[5]	= this.ij(2, 3); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 1); M.c[8]	= this.ij(3, 3); break;
		case 3:		M.c[0]	= this.ij(1, 0); M.c[1]	= this.ij(1, 1); M.c[2]	= this.ij(1, 2); 
					M.c[3]	= this.ij(2, 0); M.c[4]	= this.ij(2, 1); M.c[5]	= this.ij(2, 2); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 1); M.c[8]	= this.ij(3, 2); break;
		
		case 4: 	M.c[0]	= this.ij(0, 1); M.c[1]	= this.ij(0, 2); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(2, 1); M.c[4]	= this.ij(2, 2); M.c[5]	= this.ij(2, 3); 
					M.c[6]	= this.ij(3, 1); M.c[7]	= this.ij(3, 2); M.c[8]	= this.ij(3, 3); break;
		case 5: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 2); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(2, 0); M.c[4]	= this.ij(2, 2); M.c[5]	= this.ij(2, 3); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 2); M.c[8]	= this.ij(3, 3); break;
		case 6: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 1); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(2, 0); M.c[4]	= this.ij(2, 1); M.c[5]	= this.ij(2, 3); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 1); M.c[8]	= this.ij(3, 3); break;
		case 7: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 1); M.c[2]	= this.ij(0, 2); 
					M.c[3]	= this.ij(2, 0); M.c[4]	= this.ij(2, 1); M.c[5]	= this.ij(2, 2); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 1); M.c[8]	= this.ij(3, 2); break;
		
		case 8: 	M.c[0]	= this.ij(0, 1); M.c[1]	= this.ij(0, 2); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(1, 1); M.c[4]	= this.ij(1, 2); M.c[5]	= this.ij(1, 3); 
					M.c[6]	= this.ij(3, 1); M.c[7]	= this.ij(3, 2); M.c[8]	= this.ij(3, 3); break;
		case 9: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 2); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(1, 0); M.c[4]	= this.ij(1, 2); M.c[5]	= this.ij(1, 3); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 2); M.c[8]	= this.ij(3, 3); break;
		case 10: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 1); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(1, 0); M.c[4]	= this.ij(1, 1); M.c[5]	= this.ij(1, 3); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 1); M.c[8]	= this.ij(3, 3); break;
		case 11: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 1); M.c[2]	= this.ij(0, 2); 
					M.c[3]	= this.ij(1, 0); M.c[4]	= this.ij(1, 1); M.c[5]	= this.ij(1, 2); 
					M.c[6]	= this.ij(3, 0); M.c[7]	= this.ij(3, 1); M.c[8]	= this.ij(3, 2); break;
		
		case 12: 	M.c[0]	= this.ij(0, 1); M.c[1]	= this.ij(0, 2); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(1, 1); M.c[4]	= this.ij(1, 2); M.c[5]	= this.ij(1, 3); 
					M.c[6]	= this.ij(2, 1); M.c[7]	= this.ij(2, 2); M.c[8]	= this.ij(2, 3); break;
		case 13: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 2); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(1, 0); M.c[4]	= this.ij(1, 2); M.c[5]	= this.ij(1, 3); 
					M.c[6]	= this.ij(2, 0); M.c[7]	= this.ij(2, 2); M.c[8]	= this.ij(2, 3); break;
		case 14: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 1); M.c[2]	= this.ij(0, 3); 
					M.c[3]	= this.ij(1, 0); M.c[4]	= this.ij(1, 1); M.c[5]	= this.ij(1, 3); 
					M.c[6]	= this.ij(2, 0); M.c[7]	= this.ij(2, 1); M.c[8]	= this.ij(2, 3); break;
		case 15: 	M.c[0]	= this.ij(0, 0); M.c[1]	= this.ij(0, 1); M.c[2]	= this.ij(0, 2); 
					M.c[3]	= this.ij(1, 0); M.c[4]	= this.ij(1, 1); M.c[5]	= this.ij(1, 2); 
					M.c[6]	= this.ij(2, 0); M.c[7]	= this.ij(2, 1); M.c[8]	= this.ij(2, 2); break;
		}
		return M;
	}
	
	public void set(float a, float b, float c, float d, 
					float e, float f, float g, float h, 
					float i, float j, float k, float l, 
					float m, float n, float o, float p) 
	{
		this.setRow(a, b, c, d, 0);
		this.setRow(e, f, g, h, 1);
		this.setRow(i, j, k, l, 2);
		this.setRow(m, n, o, p, 3);
	}
	
	public static Matrix4x4f add(Matrix4x4f a, Matrix4x4f b) 
	{
		return new Matrix4x4f(	a.c[0] + b.c[0],	a.c[1] + b.c[1],	a.c[2] + b.c[2],	a.c[3] + b.c[3],
								a.c[4] + b.c[4],	a.c[5] + b.c[5],	a.c[6] + b.c[6],	a.c[7] + b.c[7],
								a.c[8] + b.c[8],	a.c[9] + b.c[9],	a.c[10] + b.c[10],	a.c[11] + b.c[11],
								a.c[12] + b.c[12],	a.c[13] + b.c[13],	a.c[14] + b.c[14],	a.c[15] + b.c[15]	);
	}
	
	public static Matrix4x4f sub(Matrix4x4f a, Matrix4x4f b) 
	{
		return new Matrix4x4f(	a.c[0] - b.c[0],	a.c[1] - b.c[1],	a.c[2] - b.c[2],	a.c[3] - b.c[3],
								a.c[4] - b.c[4],	a.c[5] - b.c[5],	a.c[6] - b.c[6],	a.c[7] - b.c[7],
								a.c[8] - b.c[8],	a.c[9] - b.c[9],	a.c[10] - b.c[10],	a.c[11] - b.c[11],
								a.c[12] - b.c[12],	a.c[13] - b.c[13],	a.c[14] - b.c[14],	a.c[15] - b.c[15]	);
	}
	
	public static Matrix4x4f multiplyVec(Vector4f v, Matrix4x4f m) 
	{
		return new Matrix4x4f(	v.x * m.c[0],	v.y * m.c[1],	v.z * m.c[2],	v.w * m.c[3],
								v.x * m.c[4],	v.y * m.c[5],	v.z * m.c[6],	v.w * m.c[7], 
								v.x * m.c[8],	v.y * m.c[9],	v.z * m.c[10],	v.w * m.c[11],
								v.x * m.c[12],	v.y * m.c[13],	v.z * m.c[14],	v.w * m.c[15]	);
	}
	
	public static Matrix4x4f multiplyMat(Matrix4x4f Ma, Matrix4x4f Mb) 
	{
		float	d_a0	= Ma.c[0]*Mb.c[0]	+ Ma.c[1]*Mb.c[4]	+ Ma.c[2]*Mb.c[8]	+ Ma.c[3]*Mb.c[12],
				d_a1	= Ma.c[0]*Mb.c[1]	+ Ma.c[1]*Mb.c[5]	+ Ma.c[2]*Mb.c[9]	+ Ma.c[3]*Mb.c[13],
				d_a2	= Ma.c[0]*Mb.c[2]	+ Ma.c[1]*Mb.c[6]	+ Ma.c[2]*Mb.c[10]	+ Ma.c[3]*Mb.c[14],
				d_a3	= Ma.c[0]*Mb.c[3]	+ Ma.c[1]*Mb.c[7]	+ Ma.c[2]*Mb.c[11]	+ Ma.c[3]*Mb.c[15],
			
				d_a4	= Ma.c[4]*Mb.c[0]	+ Ma.c[5]*Mb.c[4]	+ Ma.c[6]*Mb.c[8]	+ Ma.c[7]*Mb.c[12],
				d_a5	= Ma.c[4]*Mb.c[1]	+ Ma.c[5]*Mb.c[5]	+ Ma.c[6]*Mb.c[9]	+ Ma.c[7]*Mb.c[13],
				d_a6	= Ma.c[4]*Mb.c[2]	+ Ma.c[5]*Mb.c[6]	+ Ma.c[6]*Mb.c[10]	+ Ma.c[7]*Mb.c[14],
				d_a7	= Ma.c[4]*Mb.c[3]	+ Ma.c[5]*Mb.c[7]	+ Ma.c[6]*Mb.c[11]	+ Ma.c[7]*Mb.c[15],
				
				d_a8	= Ma.c[8]*Mb.c[0]	+ Ma.c[9]*Mb.c[4]	+ Ma.c[10]*Mb.c[8]	+ Ma.c[11]*Mb.c[12],
				d_a9	= Ma.c[8]*Mb.c[1]	+ Ma.c[9]*Mb.c[5]	+ Ma.c[10]*Mb.c[9]	+ Ma.c[11]*Mb.c[13],
				d_a10	= Ma.c[8]*Mb.c[2]	+ Ma.c[9]*Mb.c[6]	+ Ma.c[10]*Mb.c[10]	+ Ma.c[11]*Mb.c[14],
				d_a11	= Ma.c[8]*Mb.c[3]	+ Ma.c[9]*Mb.c[7]	+ Ma.c[10]*Mb.c[11]	+ Ma.c[11]*Mb.c[15],
				
				d_a12	= Ma.c[12]*Mb.c[0]	+ Ma.c[13]*Mb.c[4]	+ Ma.c[14]*Mb.c[8]	+ Ma.c[15]*Mb.c[12],
				d_a13	= Ma.c[12]*Mb.c[1]	+ Ma.c[13]*Mb.c[5]	+ Ma.c[14]*Mb.c[9]	+ Ma.c[15]*Mb.c[13],
				d_a14	= Ma.c[12]*Mb.c[2]	+ Ma.c[13]*Mb.c[6]	+ Ma.c[14]*Mb.c[10]	+ Ma.c[15]*Mb.c[14],
				d_a15	= Ma.c[12]*Mb.c[3]	+ Ma.c[13]*Mb.c[7]	+ Ma.c[14]*Mb.c[11]	+ Ma.c[15]*Mb.c[15];

		return new Matrix4x4f(	d_a0,	d_a1,	d_a2,	d_a3, 
								d_a4,	d_a5,	d_a6,	d_a7, 
								d_a8,	d_a9,	d_a10,	d_a11,	
								d_a12,	d_a13,	d_a14,	d_a15	);
	}
	
	public static float det(Matrix4x4f M) 
	{
		float   a = M.c[0],		b = M.c[1], 	c = M.c[2], 	d = M.c[3],
	            e = M.c[4], 	f = M.c[5], 	g = M.c[6], 	h = M.c[7],
	            i = M.c[8], 	j = M.c[9], 	k = M.c[10], 	l = M.c[11],
	            m = M.c[12],	n = M.c[13], 	o = M.c[14], 	p = M.c[15];
	    
	    float	t_0 = k*p - l*o,
	    		t_1 = j*p - l*n,
	    		t_2 = j*o - k*n,
	    		t_3 = i*p - l*m,
	    		t_4 = i*o - k*m,
	    		t_5 = i*n - j*m;
	    
	    return (+ a * ( f * t_0 - g * t_1 + h * t_2 )
	            - b * ( e * t_0 - g * t_3 + h * t_4 )
	            + c * ( e * t_1 - f * t_3 + h * t_5 )
	            - d * ( e * t_2 - f * t_4 + g * t_5 ));
	}
	
	public static Matrix4x4f identity() 
	{
		return new Matrix4x4f();
	}
	
	public static Matrix4x4f transpose(Matrix4x4f M) 
	{
		return new Matrix4x4f(	M.c[0],		M.c[4],		M.c[8],		M.c[12], 
								M.c[1],		M.c[5],		M.c[9],		M.c[13], 
								M.c[2],		M.c[6],		M.c[10],	M.c[14], 
								M.c[3],		M.c[7],		M.c[11],	M.c[15]	);
	}
	
	float det3x3(	float a, float b, float c, 
					float d, float e, float f, 
					float g, float h, float i	) 
	{
		float 	det_a = e*i - f*h,
				det_b = d*i - f*g,
				det_c = d*h - e*g;
		
		return a*det_a - b*det_b + c*det_c;
	}
	/*
	  0,  1,  2,  3,
	  4,  5,  6,  7,
	  8,  9, 10, 11,
	 12, 13, 14, 15 
	 */
	public Matrix4x4f adj()
	{
		float 	M_a = det3x3(c[5], c[6], c[7], c[9], c[10], c[11], c[13], c[14], c[15]),
				M_b = det3x3(c[4], c[6], c[7], c[8], c[10], c[11], c[12], c[14], c[15]),
				M_c = det3x3(c[4], c[5], c[7], c[8], c[9] , c[11], c[12], c[13], c[15]),
				M_d = det3x3(c[4], c[5], c[6], c[8], c[9] , c[10], c[12], c[13], c[14]),
				
				M_e = det3x3(c[1], c[2], c[3], c[9], c[10], c[11], c[13], c[14], c[15]),
				M_f = det3x3(c[0], c[2], c[3], c[8], c[10], c[11], c[12], c[14], c[15]),
				M_g = det3x3(c[0], c[1], c[3], c[8], c[9] , c[11], c[12], c[13], c[15]),
				M_h = det3x3(c[0], c[1], c[2], c[8], c[9] , c[10], c[12], c[13], c[14]),
				
				M_i = det3x3(c[1], c[2], c[3], c[5], c[6] , c[7] , c[13], c[14], c[15]),
				M_j = det3x3(c[0], c[2], c[3], c[4], c[6] , c[7] , c[12], c[14], c[15]),
				M_k = det3x3(c[0], c[1], c[3], c[4], c[5] , c[7] , c[12], c[13], c[15]),
				M_l = det3x3(c[0], c[1], c[2], c[4], c[5] , c[6] , c[12], c[13], c[14]),
				
				M_m = det3x3(c[1], c[2], c[3], c[5], c[6] , c[7] , c[9] , c[10], c[11]),
				M_n = det3x3(c[0], c[2], c[3], c[4], c[6] , c[7] , c[8] , c[10], c[11]),
				M_o = det3x3(c[0], c[1], c[3], c[4], c[5] , c[7] , c[8] , c[9] , c[11]),
				M_p = det3x3(c[0], c[1], c[2], c[4], c[5] , c[6] , c[8] , c[9] , c[10]);
		
		return new Matrix4x4f(	+M_a, -M_b, +M_c, -M_d,
								-M_e, +M_f, -M_g, +M_h,
								+M_i, -M_j, +M_k, -M_l,
								-M_m, +M_n, -M_o, +M_p	);
	}	
	
	public static Matrix4x4f inverse(Matrix4x4f M) 
	{
		float inv_det	= (float)1 / M.det();
		Matrix4x4f inv	= M.adj();
		inv.scale(inv_det);
		
		return inv;
	}
	
	public static Matrix4x4f lookAt(Vector3f cameraPos, Vector3f point, Vector3f up) 
	{
		Vector3f 	z_axis = Vector3f.normalize(Vector3f.sub(cameraPos, point)),
					x_axis = Vector3f.normalize(Vector3f.cross(up, z_axis)),
					y_axis = Vector3f.cross(z_axis, x_axis);
		
		Matrix4x4f look = new Matrix4x4f(	x_axis.x,							y_axis.x,							z_axis.x,							0f,
											x_axis.y,							y_axis.y,							z_axis.y,							0f,
											x_axis.z,							y_axis.z,							z_axis.z,							0f,
											-Vector3f.dot(x_axis, cameraPos),	-Vector3f.dot(y_axis, cameraPos),	-Vector3f.dot(z_axis, cameraPos),	1f	);
		return look;
	}
	
	public static Matrix4x4f projection(float aspect, float fov, float n, float f) 
	{
		fov = (float)Math.toRadians(fov);
		float 	tan_fov 	= (float)Math.tan(fov * 0.5f),
				cot_fov 	= (float)(1/tan_fov);
		float 	frust_len_1	= (float)(1 / (f-n)); 
		
		float 	t = n * tan_fov, 
				b = -t, 
				r = t * aspect, 
				l = -r;
		
		float	rl_1 	= (float)1/(r-l),
				tb_1 	= (float)1/(t-b),
				n_2		= (float)2*n;
		
		Matrix4x4f proj = new Matrix4x4f(	n_2*rl_1,	0,			0,					0,
											0,			n_2*tb_1,	0,					0,
											(r+l)*rl_1,	(t+b)*tb_1, (-f-n)*frust_len_1,	-1f,
											0,			0,			-n_2*f*frust_len_1, 0f	);
		proj.transpose();
		return proj;	
	}

	public String toString() 
	{
		StringBuilder mat_str = new StringBuilder(Float.toString(c[0]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[1]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[2]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[3]));
		
		mat_str.append(", \n");
		mat_str.append(Float.toString(c[4]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[5]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[6]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[7]));
		
		mat_str.append(", \n");
		mat_str.append(Float.toString(c[8]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[9]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[10]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[11]));
		
		mat_str.append(", \n");
		mat_str.append(Float.toString(c[12]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[13]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[14]));
		
		mat_str.append(", ");
		mat_str.append(Float.toString(c[15]));
		
		mat_str.append("\n");
		
		return mat_str.toString();
	}
}