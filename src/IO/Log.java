package IO;

import java.util.*;
import java.text.*;

public class Log 
{
	public static String getDateTime() 
	{
		SimpleDateFormat format = new SimpleDateFormat("E d HH:mm:ss z");
		Date date				= new Date(System.currentTimeMillis());
		return format.format(date);
	}
	
	public static String getMessage(String label, String logType, String message) 
	{
		StringBuilder log_str = new StringBuilder(getDateTime());
		
		log_str.append(" [");
		log_str.append(label);
		log_str.append("] (");
		log_str.append(logType);
		log_str.append("):\t");
		
		log_str.append(message);
		
		return log_str.toString();
	}
	
	public static void logInfo(String message) 
	{
		//System.out.printf("%s [%s]:\t%s\n", getDateTime(), label, message);
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		String label = ste[ste.length - 2].toString();
		System.out.println(getMessage(label, "info", message));
	}
	
	public static void logWarning(String message) 
	{
		//System.out.printf("%s [%s]:\t%s\n", getDateTime(), label, message);
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		String label = ste[ste.length - 2].toString();
		System.out.println(getMessage(label, "warning", message));
	}
	
	public static void logError(String message) 
	{
		//System.err.printf("%s [%s]:\t%s\n", getDateTime(), label, message);
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		String label = ste[ste.length - 2].toString();
		System.err.println(getMessage(label, "error", message));
	}
	
	public static void logfInfo(String message, Object... va_args) throws IllegalArgumentException
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		String label = ste[ste.length - 2].toString();
		
		StringBuilder output = new StringBuilder(message);
		int current_index = 0;
		
		for (Object o : va_args) 
		{
			current_index = output.indexOf("%", current_index) + 1;
			if (current_index == 0 || current_index >= output.length())
				break;
			//TO-DO: Add formatting options
			switch (output.charAt(current_index)) 
			{
			case 'd': 
				if 		(o instanceof Integer || o instanceof Long)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 'f':
				if 		(o instanceof Float || o instanceof Double)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 's':
				if 		(o instanceof String)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 'c':
				if 		(o instanceof Character )	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			}
		}
		
		System.out.print(getMessage(label, "info", output.toString()));
	}
	
	public static void logfWarning(String message, Object... va_args) throws IllegalArgumentException
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		String label = ste[ste.length - 2].toString();
		
		StringBuilder output = new StringBuilder(message);
		int current_index = 0;
		
		for (Object o : va_args) 
		{
			current_index = output.indexOf("%", current_index) + 1;
			if (current_index == 0 || current_index >= output.length())
				break;
			//TO-DO: Add formatting options
			switch (output.charAt(current_index)) 
			{
			case 'd': 
				if 		(o instanceof Integer || o instanceof Long)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 'f':
				if 		(o instanceof Float || o instanceof Double)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 's':
				if 		(o instanceof String)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 'c':
				if 		(o instanceof Character )	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			}
		}
		
		System.out.print(getMessage(label, "warning", output.toString()));
	}
	
	public static void logfError(String message, Object... va_args) throws IllegalArgumentException
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace(); 
		String label = ste[ste.length - 2].toString();
		
		StringBuilder output = new StringBuilder(message);
		int current_index = 0;
		
		for (Object o : va_args) 
		{
			current_index = output.indexOf("%", current_index) + 1;
			if (current_index == 0 || current_index >= output.length())
				break;
			//TO-DO: Add formatting options
			switch (output.charAt(current_index)) 
			{
			case 'd': 
				if 		(o instanceof Integer || o instanceof Long)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 'f':
				if 		(o instanceof Float || o instanceof Double)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 's':
				if 		(o instanceof String)	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			case 'c':
				if 		(o instanceof Character )	{ output.replace(current_index - 1, current_index + 1, o.toString()); }
				else	{	throw new IllegalArgumentException("argument type mismatch!"); }
				break;
			}
		}
		
		System.err.print(getMessage(label, "error", output.toString()));
	}
}
