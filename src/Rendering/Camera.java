package Rendering;

import java.util.ArrayList;

import ECS.GameObject;
import Linear_Math.*;

public class Camera 
{
	public Matrix4x4f				projection,	// Projection matrix
									view,		// View matrix
									model;		// Model matrix
	
	public Vector3f					viewDir;	// Viewing direction
	
	public ArrayList<Mesh>			objects;
	
	public Vector3f					position;	// Camera position in world space
	
	public Camera() 
	{
		projection	= new Matrix4x4f();
		view		= new Matrix4x4f();
		model		= new Matrix4x4f();
	}
	
	public void lookAt(Vector3f target) 
	{
		this.view = Matrix4x4f.lookAt(this.position, target, new Vector3f(0, 1, 0));
		this.view.transpose();
	}
	
	public void setPerspectiveMatrix(Window w) 
	{
		projection = Matrix4x4f.projection(w.aspect_ratio, 90, 0.1f, 100f);
	}
	
	public void setPerspectiveMatrix(Window w, float fov) 
	{
		projection = Matrix4x4f.projection(w.aspect_ratio, fov, 0.1f, 100f);
	}
	
	public void applyTransform(Shader shader) 
	{
		for (Mesh m : objects) 
		{
			model = Matrix4x4f.multiplyMat(	m.transform.translateMatrix, 
					Matrix4x4f.multiplyMat(	m.transform.rotationMatrix, 
											m.transform.scaleMatrix));
			shader.setUniform(model,	"model");
			shader.setUniform(view,		"view");
		}
	}
	
	public void applyTransform(Mesh[] objs, Shader shader) 
	{
		for (Mesh m : objs)
		{
			model = Matrix4x4f.multiplyMat(	m.transform.translateMatrix, 
					Matrix4x4f.multiplyMat(	m.transform.rotationMatrix, 
											m.transform.scaleMatrix));
			shader.setUniform(model, 	"model");
			shader.setUniform(view, 	"view");
		}
	}
	
	public void applyTransform(Mesh m, Shader shader)
	{
			model = Matrix4x4f.multiplyMat(	m.transform.translateMatrix, 
					Matrix4x4f.multiplyMat(	m.transform.rotationMatrix, 
											m.transform.scaleMatrix));
			shader.setUniform(model,	"model");
			shader.setUniform(view, 	"view");
	}
	
	public void loadMesh(Mesh[] object_list) 
	{
		for (Mesh obj : object_list)
			objects.add(obj);
	}
	
	public void pushMesh(Mesh obj) 
	{
		objects.add(obj);
	}
	
	public void popMesh() 
	{
		objects.remove(objects.size() - 1);
	}
	
	public void removeMeshAt(int index) 
	{
		objects.remove(index);
	}
	
	public void destroy() 
	{
		projection	= null;
		view		= null;
		model		= null;
		
		if (this.objects != null)
			objects.clear();
	}
}
