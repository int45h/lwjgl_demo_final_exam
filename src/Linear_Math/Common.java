package Linear_Math;

public final class Common
{
	/* Fast inverse square root, as detailed in https://en.wikipedia.org/wiki/Fast_inverse_square_root */ 
	public static float FastInvSqrtf(float x) 
	{
		int i = Float.floatToRawIntBits(x);
		i = 0x5F1FFFF9 - (i >>> 1);
		float f = Float.intBitsToFloat(i);
		return f * 0.703952253f * ( 2.38924456f - x * f * f );
	}

	/* Clamp function since Java's math library has none */
	public static float clampf(float x, float l, float u) 
	{
		return (float)Math.min(u, Math.max(x, l));
	}
	
	/* Linear interpolation */
	public static float lerp(float x, float y, float a) 
	{
		return (x*(1-a) + y*a);
	}
}