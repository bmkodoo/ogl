#version 330 core
in vec3 Normal;
in vec3 Position;
out vec4 color;

uniform vec3 cameraPos;
uniform samplerCube skybox;

void main()
{
    float ratio = 1.00 / 1.33;
    vec3 I = normalize(Position - cameraPos);
    vec3 Refact = refract(I, normalize(Normal), ratio);
    vec3 Refl = reflect(I, normalize(Normal));
    color = (texture(skybox, Refact) + texture(skybox, Refl))/3 + vec4(0.2, 0.0, 0.1, 0.66);
}