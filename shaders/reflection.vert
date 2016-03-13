#version 330 core
layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;

out vec3 Normal;
out vec3 Position;

uniform mat4 transformmationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main()
{
    gl_Position = projectionMatrix * viewMatrix * transformmationMatrix * vec4(position, 1.0f);
    Normal = mat3(transpose(inverse(transformmationMatrix))) * normal;
    Position = vec3(transformmationMatrix * vec4(position, 1.0f));
}