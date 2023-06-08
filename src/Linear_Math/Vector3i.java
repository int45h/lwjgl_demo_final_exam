package Linear_Math;

public class Vector3i 
{
	public int x, y, z;
	
	public Vector3i(int x, int y, int z) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(int x, int y, int z) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	int[] toIntArray() 
	{
		int[] arr = new int[3];
		
		arr[0] = this.x;
		arr[1] = this.y;
		arr[2] = this.z;
		
		return arr;
	}
}
