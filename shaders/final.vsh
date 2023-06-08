#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec3 aCol;
layout (location = 2) in vec2 aUV;

varying vec3 normal;
varying vec2 uv;
varying vec3 fragPos;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
uniform vec3 lightPos;

//uniform int time;

void main()
{
	normal	= aCol;
	uv		= aUV;

	fragPos = vec3(model * vec4(aPos, 1.f));
	gl_Position = projection * view * model * vec4(aPos, 1.f);
	//gl_Position = model * vec4(aPos, 1.f);
		
}