package ECS;

import Rendering.Mesh;
import Linear_Math.*;

public class GameObject {
	public	Mesh			mesh;
	public	Vector3f		position,
							size;
	public	Quaternion4f	rotation;
	
	public	String			name;
	
	public	Matrix4x4f		transform,
							rotation_mat;
	
	public GameObject(String name)
	{
		this.name 		= name;
		this.transform	= new Matrix4x4f();
		this.position	= new Vector3f(0, 0, 0);
		this.size		= new Vector3f(1, 1, 1);
		this.rotation	= new Quaternion4f();
	}
	
	public void setMesh(Mesh obj) 
	{
		this.mesh = obj;
		mesh.generateMesh();
	}
	
	public void loadShaders(String path) 
	{
		mesh.loadShaders(path);
	}
	
	public void destroy() 
	{
		this.name 		= null;
		this.mesh.destroy();
		this.transform 	= null;
		this.position	= null;
		this.rotation	= null;
	}
	
	public void applyTransform() 
	{
		this.mesh.transform.translate(position);
	}
}
