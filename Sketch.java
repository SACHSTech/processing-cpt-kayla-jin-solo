import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  // Determines what is drawn on the screen
  int intArea = -1;

  // Start screen and control screen images
  PImage startScreen;
  PImage controlScreen;

  // Flashlight
	PImage flashlight;
  
  // Alex Idle Tileset
  PImage alexIdleTileset;

  // Alex Run Images
  PImage alexBack1;
  PImage alexBack2;
  PImage alexForward1;
  PImage alexForward2;
  PImage alexLeft1;
  PImage alexLeft2;
  PImage alexRight1;
  PImage alexRight2;

  // Alex coordinates
  int intAlexX = 200;
  int intAlexY = 200;

  // Speed
  int intAlexSpeed = 4;

  // Boolean values for Alex's movement
  boolean wPressed = false;
  boolean sPressed = false;
  boolean aPressed = false;
  boolean dPressed = false;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(800, 800);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    
    // Load the start screen and controls
    startScreen = loadImage("Start Screen No Buttons.png");
    controlScreen = loadImage("Game Controls.png")
    
    // Load Alex's movement animations
    alexBack1 = loadImage("Alex Back 1.png");
    alexBack2 = loadImage("Alex Back 2.png");
    alexForward1 = loadImage("Alex Forward 1.png");
    alexForward2 = loadImage("Alex Forward 2.png");
    alexLeft1 = loadImage("Alex Left 1.png");
    alexLeft2 = loadImage("Alex Left 2.png");
    alexRight1 = loadImage("Alex Right 1.png");
    alexRight2 = loadImage("Alex Right 2.png");

  }

  // Everything drawn to the screen
  public void draw() {
	  background(0, 0, 0);
    // Alex's movement
    if (keyPressed) {
      
      if (wPressed) {
        intAlexY -= intAlexSpeed;
        image(alexForward1, intAlexX, intAlexY);
      }
      else if (sPressed) {
        intAlexY += intAlexSpeed;
        image(alexBack1, intAlexX, intAlexY);
      }
      else if (aPressed) {
        intAlexX -= intAlexSpeed;
        image(alexLeft1, intAlexX, intAlexY);
      }
      else if (dPressed) {
        intAlexX += intAlexSpeed;
        image(alexRight1, intAlexX, intAlexY);
      }
    }
  }
  
  // Alex's movement
  public void keyPressed() {
    if (key == 'w') {
      wPressed = true;
    }
    else if (key == 's') {
      sPressed = true;
    }
    else if (key == 'a') {
      aPressed = true;
    }
    else if (key == 'd') {
      dPressed = true;
    }
  }

  // Alex's movement
  public void keyReleased() {
    if (key == 'w') {
      wPressed = false;
    }
    else if (key == 's') {
      sPressed = false;
    }
    else if (key == 'a') {
      aPressed = false;
    }
    else if (key == 'd') {
      dPressed = false;
    }
  }

  // Draws the start screen when intArea = -1
  public void drawStartScreen() {

  }
  
  // Draws the controls when intArea = 0
  public void drawControlScreen() {

  }

  // Draws the first room when intArea = 1
  public void drawRoom1() {

  }

  // Draws the second room when intArea = 2
  public void drawRoom2() {

  }

  // Draws the third room when intArea = 3
  public void drawRoom3() {

  }
}