#version 330 core

varying vec3 normal;
varying vec2 uv;

out vec4 FragColor;

//uniform int time;
uniform sampler2D tex;

uniform vec3	lightPos;
uniform vec3	lightCol;
uniform float 	lightIntensity;

varying vec3	fragPos;

float PI = 3.141592654;

vec3 diffuse()
{
	float diff = dot(normalize(normal), normalize(lightPos - fragPos)) * lightIntensity;
	return vec3(lightCol * diff);
}

void main()
{
	float ambient = 0.25f;	
	vec3 color = texture(tex, uv).xyz;
	
	FragColor = vec4((color * 0.25f) + diffuse(), 1.f);
}