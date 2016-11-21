package fr.swoking.GameEngine.Game;

import org.lwjgl.opengl.Display;

import fr.swoking.GameEngine.RenderEngine.DisplayManager;
import fr.swoking.GameEngine.RenderEngine.Loader;
import fr.swoking.GameEngine.RenderEngine.RawModel;
import fr.swoking.GameEngine.RenderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				// Left bottom triangle
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				// Right bottom triangle
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};
		
		RawModel model = loader.loadToVAO(vertices);
		
		while(!Display.isCloseRequested()){
			
			renderer.prepare();
			//game logic
			renderer.render(model);
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
