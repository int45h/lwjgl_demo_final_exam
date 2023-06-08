package IO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import Rendering.Mesh;

public class GFile 
{
	public static String loadFileFromPath(String path, Charset encoding) 
	{
		String output = "";
		
		try 
		{
			System.out.println(Paths.get(path).toAbsolutePath().normalize().toString());
			
			byte[] fileb = Files.readAllBytes(Paths.get(path));
			return output = new String(fileb, encoding); 
		}
		catch (Exception e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static BufferedReader loadTextBufferFromPath(String path) 
	{
		BufferedReader file;
		try 
		{
			file = new BufferedReader(new FileReader(path));
		}
		catch (Exception e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
			file = null;
		}
		return file;
	}
}
