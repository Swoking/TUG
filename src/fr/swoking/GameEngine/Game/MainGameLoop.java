package fr.swoking.GameEngine.Game;

import org.lwjgl.opengl.Display;

import fr.swoking.GameEngine.RenderEngine.DisplayManager;
import fr.swoking.GameEngine.RenderEngine.Loader;
import fr.swoking.GameEngine.RenderEngine.RawModel;
import fr.swoking.GameEngine.RenderEngine.Renderer;
import fr.swoking.GameEngine.shaders.StaticShader;

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
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!Display.isCloseRequested()){
			//game logic
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		shader.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
