import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  // Determines which area is drawn on the screen
  int intDraw = -1;

  // Start screen
  PImage startScreen;

  // Buttons on start screen
  PImage playButton;
  PImage controlsButton;
  PImage exitButton;

  // Controls screen
  PImage controlScreen;

  // Flashlight
	PImage flashlight;
  
  // Alex's size readjustment
  int intSizeProportion = 275;

  // Alex's Idle Images
  PImage alexIdleBack;
  PImage alexIdleForward;
  PImage alexIdleLeft;
  PImage alexIdleRight;

  // Alex's Run Images
  PImage alexBack1;
  PImage alexBack2;
  PImage alexForward1;
  PImage alexForward2;
  PImage alexLeft1;
  PImage alexLeft2;
  PImage alexRight1;
  PImage alexRight2;

  // Alex's coordinates
  int intAlexX;
  int intAlexY;

  // Alex's Speed
  int intAlexSpeed = 6;

  // Boolean values for Alex's movement
  boolean wPressed = false;
  boolean sPressed = false;
  boolean aPressed = false;
  boolean dPressed = false;

  // Set the size of the window
  public void settings() {
    size(800, 800);

    // Sets Alex's initial position
    intAlexX = width / 2;
    intAlexY = height / 2;
  }

  // Load and resize all images
  public void setup() {
    
    // Load the start screen and resize
    startScreen = loadImage("Start Screen.png");
    startScreen.resize(startScreen.width * width/918, startScreen.height * height/918);
    
    // Load the buttons on the start screen and resize
    playButton = loadImage("Play Button.png");
    playButton.resize(playButton.width * width/918, playButton.height * width/918);

    controlsButton = loadImage("Controls Button.png");
    controlsButton.resize(controlsButton.width * width/918, controlsButton.height * height/918);

    exitButton = loadImage("Exit Button.png");
    exitButton.resize(exitButton.width * width/918, exitButton.height * height/918);

    // Load the control screen and resize
    controlScreen = loadImage("Game Controls.png");
    controlScreen.resize(controlScreen.width * width/918, controlScreen.height * height/918);
    
    // Load Alex's movement animations
    alexBack1 = loadImage("Alex Back 1.png");
    alexBack1.resize(alexBack1.width * width/intSizeProportion, alexBack1.height * height/intSizeProportion);
    alexBack2 = loadImage("Alex Back 2.png");
    alexBack2.resize(alexBack2.width * width/intSizeProportion, alexBack2.height * height/intSizeProportion);

    alexForward1 = loadImage("Alex Forward 1.png");
    alexForward1.resize(alexForward1.width * width/intSizeProportion, alexForward1.height * height/intSizeProportion);
    alexForward2 = loadImage("Alex Forward 2.png");
    alexForward2.resize(alexForward2.width * width/intSizeProportion, alexForward2.height * height/intSizeProportion);
    
    alexLeft1 = loadImage("Alex Left 1.png");
    alexLeft1.resize(alexLeft1.width * width/intSizeProportion, alexLeft1.height * height/intSizeProportion);
    alexLeft2 = loadImage("Alex Left 2.png");
    alexLeft2.resize(alexLeft2.width * width/intSizeProportion, alexLeft2.height * height/intSizeProportion);

    alexRight1 = loadImage("Alex Right 1.png");
    alexRight1.resize(alexRight1.width * width/intSizeProportion, alexRight1.height * height/intSizeProportion);
    alexRight2 = loadImage("Alex Right 2.png");
    alexRight2.resize(alexRight2.width * width/intSizeProportion, alexRight2.height * height/intSizeProportion);

    // Load the flashlight
    flashlight = loadImage("Flashlight.png");

  }

  // Everything drawn to the screen
  public void draw() {
    // Draw start screen
    if (intDraw == -1) {
      drawStartScreen();
    }
    // Draw the controls screen
    else if (intDraw == 0) {
      drawControlScreen();
    }
    // Draw room 1
    else if (intDraw == 1) {
      drawRoom1();
      drawAlex();
    }
    // Draw room 2
    else if (intDraw == 2) {
      drawRoom2();
      drawAlex();
    }
    // Draw room 3
    else if (intDraw == 3) {
      drawRoom3();
      drawAlex();
    }
  }
  
  // Draws the start screen when intDraw = -1
  public void drawStartScreen() {
    
    // Start screen background
    image(startScreen, 0, 0);

    // Buttons on the start screen
    image(playButton, width * 100/918, height * 325/918);
    image(controlsButton, width * 100/918, height * 440/918);
    image(exitButton, width * 100/918, height * 555/918);

  }
  
  // Draws the controls when intDraw = 0
  public void drawControlScreen() {
    image(controlScreen, 0, 0);
  }

  // Draws the first room when intDraw = 1
  public void drawRoom1() {

  }

  // Draws the second room when intDraw = 2
  public void drawRoom2() {

  }

  // Draws the third room when intDraw = 3
  public void drawRoom3() {

  }

  // Draws Alex to the screen
  public void drawAlex() {
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

  // Checks when the mouse is pressed
  public void mousePressed() {
    
    // Checks mouse interactivity on the start screen
    if (intDraw == -1) {
      
      // Starts the game when the start button is pressed
      if (mouseX > width * 100/918 && mouseX < width * 100/918 + playButton.width && mouseY > height * 325/918 && mouseY < height * 325/918 + playButton.height) {
        intDraw = 1;
      }
      
      // Shows the players the control screen when the controls button is pressed
      else if (mouseX > width * 100/918 && mouseX < width * 100/918 + controlsButton.width && mouseY > height * 440/918 && mouseY < height * 440/918 + controlsButton.height) {
        intDraw = 0;
      }
      // Exits the program when the exit button is pressed
      else if (mouseX > width * 100/918 && mouseX < width * 100/918 + exitButton.width && mouseY > height * 555/918 && mouseY < height * 555/918 + exitButton.height) {
        exit();
      }
    }
    
    // Checks if the player presses the back button on the controls screen
    else if (intDraw == 0) {
      
      if (mouseX > width * 752/918 && mouseX < width * 896/918 && mouseY > height * 808/918 && mouseY < height * 901/918) {
        intDraw = -1;
      }
    }
  }
}