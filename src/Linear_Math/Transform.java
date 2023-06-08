package Linear_Math;

public class Transform 
{
	public Matrix4x4f	rotationMatrix,
						scaleMatrix,
						translateMatrix,
						modelMatrix;		// Model matrix
	
	public Quaternion4f	rotationQuat;
	
	public Transform() 
	{
		this.rotationMatrix		= new Matrix4x4f();
		this.scaleMatrix		= new Matrix4x4f();
		this.translateMatrix	= new Matrix4x4f();
		this.modelMatrix		= new Matrix4x4f();
		
		this.rotationQuat		= new Quaternion4f();
	}
	
	public void rotate(float x, float y, float z) 
	{
		this.rotationQuat.eulerToQuat(x, y, z);
		rotationQuat.getRotationMatrix(rotationMatrix);
	}
	
	public void rotate(Vector3f xyz) 
	{
		this.rotationQuat.eulerToQuat(xyz);
		rotationQuat.getRotationMatrix(rotationMatrix);
	}
	
	public void translate(float x, float y, float z) 
	{
		Affine.translate(translateMatrix, x, y, z);
	}
	
	public void translate(Vector3f xyz) 
	{
		Affine.translate(translateMatrix, xyz);
	}
	
	public void destroy() 
	{
		this.rotationMatrix		= null;
		this.scaleMatrix		= null;
		this.translateMatrix	= null;
		
		this.modelMatrix		= null;
		this.rotationQuat		= null;
	}

	public void scale(float x, float y, float z) 
	{
		Affine.scale(scaleMatrix, x, y, z, new Vector3f(1, 1, 1));
	}
	
	public void scale(Vector3f xyz) 
	{
		Affine.scale(scaleMatrix, xyz, new Vector3f(1, 1, 1));
	}
	
	public void scale(float f) 
	{
		Affine.scale(scaleMatrix, f, new Vector3f(1, 1, 1));
	}
}
