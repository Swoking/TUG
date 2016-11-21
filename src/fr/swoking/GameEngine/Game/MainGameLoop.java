package fr.swoking.GameEngine.Game;

import org.lwjgl.opengl.Display;

import fr.swoking.GameEngine.RenderEngine.DisplayManager;
import fr.swoking.GameEngine.RenderEngine.Loader;
import fr.swoking.GameEngine.RenderEngine.Renderer;
import fr.swoking.GameEngine.models.RawModel;
import fr.swoking.GameEngine.models.TexturedModel;
import fr.swoking.GameEngine.shaders.StaticShader;
import fr.swoking.GameEngine.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();

		float[] vertices = {
				// Left bottom triangle
				-0.5f,  0.5f, 0f,
				-0.5f, -0.5f, 0f,
				 0.5f, -0.5f, 0f,
				 0.5f,  0.5f, 0f
		};
		
		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		float[] textureCoords = {
				// Left bottom triangle
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("test"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested()){
			//game logic
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		shader.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
