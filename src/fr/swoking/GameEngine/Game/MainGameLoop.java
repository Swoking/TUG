package fr.swoking.GameEngine.Game;

import org.lwjgl.opengl.Display;

import fr.swoking.GameEngine.RenderEngine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		while(!Display.isCloseRequested()){
			
			//game logic
			//render
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();
	}
	
}
