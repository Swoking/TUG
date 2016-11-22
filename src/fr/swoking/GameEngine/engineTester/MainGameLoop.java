package fr.swoking.GameEngine.engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import fr.swoking.GameEngine.entities.Camera;
import fr.swoking.GameEngine.entities.Entity;
import fr.swoking.GameEngine.entities.Light;
import fr.swoking.GameEngine.models.RawModel;
import fr.swoking.GameEngine.models.TexturedModel;
import fr.swoking.GameEngine.renderEngine.DisplayManager;
import fr.swoking.GameEngine.renderEngine.Loader;
import fr.swoking.GameEngine.renderEngine.OBJLoader;
import fr.swoking.GameEngine.renderEngine.Renderer;
import fr.swoking.GameEngine.shaders.StaticShader;
import fr.swoking.GameEngine.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		RawModel model = OBJLoader.loadObjModel("stall", loader);
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stall")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);;
		texture.setReflectivity(1);
		
		Entity entity = new Entity(staticModel, new Vector3f(0, -5, -25),0,0,0,1);
		Light light = new Light(new Vector3f(0,0,-20), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()){
			//game logic
			entity.increaseRotation(0, 1, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		shader.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
