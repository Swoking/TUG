package fr.swoking.GameEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;

import fr.swoking.GameEngine.entities.Camera;
import fr.swoking.GameEngine.entities.Light;
import fr.swoking.GameEngine.toolbox.Maths;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE   = "src/fr/swoking/GameEngine/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/fr/swoking/GameEngine/shaders/fragmentShader.txt";

	private int location_fransformationMatrix;
	private int location_pojectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_fransformationMatrix = super.getUniformLocation("transformationMatrix");
		location_pojectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
	}
	
	public void loadShineVariables(float damper, float reflectivity){
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	public void loadLight(Light light){
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_fransformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadPojectionMatrix(Matrix4f projection){
		super.loadMatrix(location_pojectionMatrix, projection);
	}
	
	

}
