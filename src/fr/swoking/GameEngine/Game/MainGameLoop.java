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
			
			renderer.prepare();
			//game logic
			renderer.render(model);
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
