import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  // Determines which area is drawn on the screen
  int intDraw = 0;

  // Start screen
  PImage startScreen;

  // Buttons on start screen
  PImage playButton, controlsButton, exitButton;

  // Controls screen and back button
  PImage controlScreen, ctrlBack;

  // Story summary
  PImage storySummary;

  // Flashlight
	PImage flashlight;
  
  // Alex's size readjustment
  int intSizeProportion = 275;

  // Alex's Idle Images
  PImage alexIdleBack, alexIdleForward, alexIdleLeft, alexIdleRight;

  // Alex's Run Images
  PImage alexBack1, alexBack2, alexForward1, alexForward2, alexLeft1, alexLeft2, alexRight1, alexRight2;

  // Alex's coordinates
  int intAlexX,intAlexY;

  // Alex's Speed
  int intAlexSpeed = 6;

  // Sprite and counter for Alex's movement animation
  int intSprite = 1;
  int intCounter = 0;

  // Variable to prioritize left or right movement animations when Alex moves diagonally
  String strLorR = "";

  // Determines Alex's direction for his idle
  String strDir = "Down";

  // Boolean values for Alex's movement
  boolean wPressed = false;
  boolean sPressed = false;
  boolean aPressed = false;
  boolean dPressed = false;

  // Cabin background for all three rooms
  PImage cabinBack;

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

    // Load the back button on the control screen and resize
    ctrlBack = loadImage("Back Button.png");
    ctrlBack.resize(ctrlBack.width * width/918, ctrlBack.height * height/918);
    
    // Load and resize the story summary
    storySummary = loadImage("Story Summary.png");
    storySummary.resize(storySummary.width * width/800, storySummary.height * height/800);

    // Load Alex's idle animations
    alexIdleBack = loadImage("Alex Idle Back.png");
    alexIdleForward = loadImage("Alex Idle Forward.png");
    alexIdleLeft = loadImage("Alex Idle Left.png");
    alexIdleRight = loadImage("Alex Idle Right.png");

    // Load Alex's movement animations
    alexBack1 = loadImage("Alex Back 1.png");
    alexBack2 = loadImage("Alex Back 2.png");

    alexForward1 = loadImage("Alex Forward 1.png");
    alexForward2 = loadImage("Alex Forward 2.png");
    
    alexLeft1 = loadImage("Alex Left 1.png");
    alexLeft2 = loadImage("Alex Left 2.png");

    alexRight1 = loadImage("Alex Right 1.png");
    alexRight2 = loadImage("Alex Right 2.png");

    // Load the flashlight
    flashlight = loadImage("Flashlight.png");

    cabinBack = loadImage("Cabin Room Template.png");
    cabinBack.resize(cabinBack.width * width/200, cabinBack.height * height/200);

  }

  // Everything drawn to the screen
  public void draw() {
    // Draw start screen
    if (intDraw == 0) {
      drawStartScreen();
    }
    // Draw the story summary
    else if (intDraw == 1) {
      drawStorySum();
    }
    // Draw the controls screen
    else if (intDraw == 2) {
      drawControlScreen();
    }
    // Draw room 1
    else if (intDraw == 3) {
      drawRoom1();
      drawAlex();
    }
    // Draw room 2
    else if (intDraw == 4) {
      drawRoom2();
      drawAlex();
    }
    // Draw room 3
    else if (intDraw == 5) {
      drawRoom3();
      drawAlex();
    }
  }
  
  // Draws the start screen when intDraw = 0
  public void drawStartScreen() {

    // Gives the start button a red tint when the start button hovers over it
    if (mouseX > width * 100/918 && mouseX < width * 100/918 + playButton.width && mouseY > height * 325/918 && mouseY < height * 325/918 + playButton.height) {
      tint(255, 0, 0);
      image(playButton, width * 100/918, height * 325/918);
      noTint();
    }
    
    // Gives the controls button a red tint when the mouse hovers over it
    else if (mouseX > width * 100/918 && mouseX < width * 100/918 + controlsButton.width && mouseY > height * 440/918 && mouseY < height * 440/918 + controlsButton.height) {
      tint(255, 0, 0);
      image(controlsButton, width * 100/918, height * 440/918);
      noTint();
    }
    // Gives the exit button a red tint when the mouse hovers over it
    else if (mouseX > width * 100/918 && mouseX < width * 100/918 + exitButton.width && mouseY > height * 555/918 && mouseY < height * 555/918 + exitButton.height) {
      tint(255, 0, 0);
      image(exitButton, width * 100/918, height * 555/918);
      noTint();
    }
    else {
      // Start screen background
      image(startScreen, 0, 0);

      // Buttons on the start screen
      image(playButton, width * 100/918, height * 325/918);
      image(controlsButton, width * 100/918, height * 440/918);
      image(exitButton, width * 100/918, height * 555/918);
    }
  }
  
  // Displays a summary of the story on the screen when intDraw = 1
  public void drawStorySum() {
    image(storySummary, 0, 0);
  }

  // Draws the control screen when intDraw = 2
  public void drawControlScreen() {
    
    // Gives the back button a red tint when the mouse hovers over it
    if (mouseX > width * 752/918 && mouseX < width * 752/918 + ctrlBack.width && mouseY > height * 808/918 && mouseY < height * 808/918 + ctrlBack.height) {
      tint(255, 0, 0);
      image(ctrlBack, width * 752/918, height * 808/918);
      noTint();
    }
    
    else {
    // Controls
    image(controlScreen, 0, 0);

    // Back button
    image(ctrlBack, width * 752/918, height * 808/918);
    }
    
  }

  // Draws the first room when intDraw = 3
  public void drawRoom1() {
    image(cabinBack, 0, 0);
    if (intAlexX > width) {
      intDraw = 4;
      intAlexX = 1;
    }
  }

  // Draws the second room when intDraw = 4
  public void drawRoom2() {
    image(cabinBack, 0, 0);
    
    // Moves to the first room when Alex moves off to the left of the screen
    if (intAlexX < 0) {
      intDraw = 3;
      intAlexX = width;
    }
    // Moves to the third room when Alex moves off the right of the screen
    else if (intAlexX > width) {
      intDraw = 5;
      intAlexX = 1;
    }
  }

  // Draws the third room when intDraw = 5
  public void drawRoom3() {
    image(cabinBack, 0, 0);
    fill(255, 0, 0);

    // Moves to the second room when Alex moves off the left of the screen
    if (intAlexX < 0) {
      intDraw = 4;
      intAlexX = width;
    }
    // Moves to the ending cutscene
    else if (intAlexX > width) {
      // Some end screen thing here
    }
  }

  public void drawEnd() {
    // Some random thingy here I suppose
  }

  // Draws a flashlight for Alex
  public void drawFlashlight() {
    image(flashlight, intAlexX - width * 3/4, intAlexY - height * 3/4);
  }

  // Draws Alex to the screen
  public void drawAlex() {

    if (wPressed) {
        
      // Makes Alex move forward
      intAlexY -= intAlexSpeed;
      
      // Draws the sprite as long as Alex is not moving diagonally
      if (!(strLorR.equals("Left") || strLorR.equals("Right"))) {
        // Draws the first sprite
        if (intSprite == 1) {
          image(alexForward1, intAlexX, intAlexY);
        }
        // Draws the second sprite
        else if (intSprite == 2) {
          image(alexForward2, intAlexX, intAlexY);
        }
      }
    }

    if (sPressed) {
      
      // Makes Alex move backward
      intAlexY += intAlexSpeed;
      
      // Draws the sprite as long as Alex is not moving diagonally
      if (!(strLorR.equals("Left") || strLorR.equals("Right"))) {
        
        // Draws the first sprite
        if (intSprite == 1) {
          image(alexBack1, intAlexX, intAlexY);
        }
        // Draws the second sprite
        else if (intSprite == 2) {
          image(alexBack2, intAlexX, intAlexY);
        }
      }
    }
    if (aPressed) {
      
      // Makes Alex move to the left
      intAlexX -= intAlexSpeed;
      
      if (!strLorR.equals("Right")) {
        // Draws the first sprite
        if (intSprite == 1) {
          image(alexLeft1, intAlexX, intAlexY);
        }
        // Draws the second sprite
        else if (intSprite == 2) {
          image(alexLeft2, intAlexX, intAlexY);
        }
        // Prioritizes the left moving animation when Alex moves diagonally
        strLorR = "Left";
      }
    }
    if (dPressed) {

      // Makes Alex move to the right
      intAlexX += intAlexSpeed;
      if (!strLorR.equals("Left")) {
        // Draws the first sprite
        if (intSprite == 1) {
          image(alexRight1, intAlexX, intAlexY);
        }

        // Draws the second sprite
        else if (intSprite == 2) {
          image(alexRight2, intAlexX, intAlexY);
        }
        // Prioritizes the right moving animation when Alex moves diagonally
        strLorR = "Right";
      }
    }
    
    // Increments the sprite counter by 1
    intCounter++;

    // Changes the sprite when the counter reaches a certain number
    if (intCounter == 8) {

      // Changes the sprite to the second sprite
      if (intSprite == 1) {
        intSprite = 2;
      }
      // Changes the sprite to the first sprite
      else if (intSprite == 2) {
        intSprite = 1;
      }
      // Reset the sprite counter to zero
      intCounter = 0;
    }

    // Draws Alex's idle
    if (!keyPressed){
      if (strDir.equals("Down")) {
      image(alexIdleBack, intAlexX, intAlexY);
      }
      else if (strDir.equals("Up")) {
      image(alexIdleForward, intAlexX, intAlexY);
      }
      else if (strDir.equals("Left")) {
      image(alexIdleLeft, intAlexX, intAlexY);
      }
      else if (strDir.equals("Right")) {
      image(alexIdleRight, intAlexX, intAlexY);
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
      strDir = "Up";
    }
    else if (key == 's') {
      sPressed = false;
      strDir = "Down";
    }
    else if (key == 'a') {
      aPressed = false;
      strDir = "Left";
      strLorR = "";
    }
    else if (key == 'd') {
      dPressed = false;
      strDir = "Right";
      strLorR = "";
    }
  }

  // Checks when the mouse is pressed
  public void mousePressed() {
    
    // Checks mouse interactivity on the start screen
    if (intDraw == 0) {
      
      // Starts the game when the start button is pressed
      if (mouseX > width * 100/918 && mouseX < width * 100/918 + playButton.width && mouseY > height * 325/918 && mouseY < height * 325/918 + playButton.height) {
        intDraw = 1;
      }
      
      // Shows the players the control screen when the controls button is pressed
      else if (mouseX > width * 100/918 && mouseX < width * 100/918 + controlsButton.width && mouseY > height * 440/918 && mouseY < height * 440/918 + controlsButton.height) {
        intDraw = 2;
      }
      // Exits the program when the exit button is pressed
      else if (mouseX > width * 100/918 && mouseX < width * 100/918 + exitButton.width && mouseY > height * 555/918 && mouseY < height * 555/918 + exitButton.height) {
        exit();
      }
    }
    
    // Checks if the player clicks to move past the story summary
    else if (intDraw == 1) {
      intDraw = 3;
    }

    // Checks if the player presses the back button on the controls screen
    else if (intDraw == 2) {
      
      // Goes back to the start screen if the back button is pressed
      if (mouseX > width * 752/918 && mouseX < width * 752/918 + ctrlBack.width && mouseY > height * 808/918 && mouseY < height * 808/918 + ctrlBack.height) {
        intDraw = 0;
      }
    }
    // Checks the players mouse interactions in the first room
    else if (intDraw == 3) {
      
    }
    // Checks the players mouse interactions in the second room
    else if (intDraw == 4) {

    }
    // Checks the players mouse interactions int he third room
    else if (intDraw == 5) {

    }
  }
}