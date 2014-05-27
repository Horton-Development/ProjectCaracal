
package com.client.Screens;

import com.client.Engine.Engine;
import java.awt.Graphics;


public class CharacterSelectionScreen extends Screen implements Runnable{

    Engine engine;
    Thread thread = new Thread(this);
    
    //Constructor
    public CharacterSelectionScreen(Engine engine){
        super(engine);
        this.engine = engine;
        thread.start();
    }
    
    //Paints the screen
    public void paint(Graphics g){
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    //Runs the character selection screen
    public void run(){
        loadPreviousCharacters();
        loadGameData();
        loadBackground();
        loadImages();
        while(engine.running){
            repaint();
        }
    }

    //Loads images to the screen
    private void loadImages() {
        
    }

    //Loads previous game data
    private void loadGameData() {
        
    }

    //Loads the background screen
    private void loadBackground() {
        
    }

    //Loads previous characters played by the character
    private void loadPreviousCharacters() {
        
    }
    
    
    
    
}
