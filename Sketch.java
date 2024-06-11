import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Collections;
import ddf.minim.*;

public class Sketch extends PApplet {
	
  // Minim class
  Minim minim;

  // Make the background music an audio player
  AudioPlayer backgroundMusic;
  
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

  // Alex's Idle Images
  PImage alexIdleBack, alexIdleForward, alexIdleLeft, alexIdleRight;

  // Alex's Run Images
  PImage alexBack1, alexBack2, alexForward1, alexForward2, alexLeft1, alexLeft2, alexRight1, alexRight2;

  // Alex's coordinates
  int intAlexX,intAlexY;

  // Alex's previous position
  int intAlexPrevX, intAlexPrevY;

  // Alex's Speed
  int intAlexSpeed;

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

  // Image to store the e key and boolean to determine when it is pushed
  PImage interactKey;
  boolean ePushed = false;

  // Cabin background for all three rooms
  PImage cabinBack;

  // Related arrays to store the room one pages drawn in the room as objects, their x and y coordinates, and the full images shown to the player's screen
  PImage[] roomOnePages = new PImage[4];
  int[] roomOnePageX;
  int[] roomOnePageY;
  PImage[] roomOneClues = new PImage[4];

  // ArrayLists (for flexibility when adding objects to rooms) to store the objects, and x and y values in room 1
  ArrayList<PImage> oneObjects = new ArrayList<PImage>();
  ArrayList<Integer> oneObjectX = new ArrayList<Integer>();
  ArrayList<Integer> oneObjectY = new ArrayList<Integer>();

  // Related arrays to store the room two pages drawn in the room as objects, their x and y coordinates, and the full images shown to the player's screen
  PImage[] roomTwoPages = new PImage[3];
  int[] roomTwoPageX;
  int[] roomTwoPageY;
  PImage[] roomTwoLetters = new PImage[3];

  // ArrayLists to store the objects, and x and y values in room 2
  ArrayList<PImage> twoObjects = new ArrayList<PImage>();
  ArrayList<Integer> twoObjectX = new ArrayList<Integer>();
  ArrayList<Integer> twoObjectY = new ArrayList<Integer>();
  
  // Related arrays to store the room three pages drawn in the room as objects, their x and y coordinates, and the full images shown to the player's screen
  PImage[] roomThreePages = new PImage[6];
  int[] roomThreePageX;
  int[] roomThreePageY;
  PImage[] roomThreeJournals = new PImage[6];

  // ArrayLists to store the objects, and x and y values in room 3
  ArrayList<PImage> threeObjects = new ArrayList<PImage>();
  ArrayList<Integer> threeObjectX = new ArrayList<Integer>();
  ArrayList<Integer> threeObjectY = new ArrayList<Integer>();

  // Array to store the number keys for the passwords in room 1 and 2
  PImage[] numKeys = new PImage[10];

  // Saves the current clue image
  PImage currentClueImage = null;

  // Sets the passwords of the doors
  String[] strPasswords = new String [3];
  String[] strTargPass = {"2413", "666", "Alex"};

  // Array for the door objects drawn in the rooms
  PImage[] doors = new PImage[6];

  // X and y coordinates of the six doors
  int[] doorX;
  int[] doorY;

  // Images of what is shown when doors are interacted with
  PImage door1Text;
  PImage door2Unpassed;
  PImage door2Passed;
  PImage door4Unpassed;
  PImage door4Passed;
  PImage door6Unpassed;
  PImage door6Passed;

  // Boolean array to determine which doors are locked or not
  boolean[] boolDoorUnlocked = new boolean [6];

  // Variable to store the current door the player is interacting with
  int intCurDoor;

  // Related arrays for the button images, x coordinates, and y coordinates for the first password
  PImage[] doorFirstButt = new PImage[4];
  int[] doorFirstX;
  int[] doorFirstY;

  // Number of attempts for the first password
  int intAttempts = 3;

  // Set the size of the window
  public void settings() {
    size(800, 800);

    // Sets Alex's initial position
    intAlexX = width / 2;
    intAlexY = height / 2;

    // Alex's speed
    intAlexSpeed = width * 6/800;

    // Adds the x and y values of the objects in room 1 based on the window size into two arrays
    Collections.addAll(oneObjectX, width * 280/800, width * 390/800, width * 85/800, width * 194/800, width * 563/800);
    Collections.addAll(oneObjectY, height * 656/800, height * 640/800, height * 227/800, height * 337/800, height * 215/800);

    // Adds the x and y values of the objects in room 2 based on the window size into two arrays
    Collections.addAll(twoObjectX, width * 86/800, width * 100/800, width * 611/800, width * 200/800, width * 460/800);
    Collections.addAll(twoObjectY, height * 230/800, height * 640/800, height * 384/800, height * 560/800, height * 229/800);

    // Adds the x and y values of the objects in room 3 based on the window size into two arrays
    Collections.addAll(threeObjectX, width * 125/800, width * 487/800, width * 74/800, width * 594/800, width * 525/800, 
    width * 541/800, width * 121/800, width * 111/800, width * 657/800, width * 582/800);
    Collections.addAll(threeObjectY, height * 265/800, height * 221/800, height * 226/800, height * 242/800, height * 340/800, 
    height * 266/800, height * 680/800, height * 242/800, height * 670/800, height * 670/800);

    // Intializes the x and y values of the pages in room one based on the window size
    roomOnePageX = new int[]{width * 307/800, width * 298/800, width * 590/800, width * 102/800};
    roomOnePageY = new int[]{height * 254/800, height * 661/800, height * 663/800, height * 253/800};

    // Intializes the x and y values of the pages in room two based on the window size
    roomTwoPageX = new int[]{width * 622/800, width * 506/800, width * 109/800};
    roomTwoPageY = new int[]{height * 417/800, height * 291/800, height * 260/800};

    // Intializes the x and y values of the pages in room three based on the window size
    roomThreePageX = new int[]{width * 163/800, width * 549/800, width * 598/800, width * 321/800, width * 406/800, width * 246/800};
    roomThreePageY = new int[]{height * 689/800, height * 382/800, height * 679/800, height * 446/800, height * 530/800, height * 236/800};

    // Set the x and y positions of the doors
    doorX = new int[] {width * 20/800, width * 710/800, width * 20/800, width * 710/800, width * 20/800, width * 710/800};
    doorY = new int[] {height * 426/800, height * 426/800, height * 426/800, height * 426/800, height * 426/800, height * 426/800};

    // Set the x and y positions of the buttons in the first password
    doorFirstX = new int[] {453, 627, 453, 627};
    doorFirstY = new int[] {659, 659, 718, 718};
  }

  // Load and resize all images
  public void setup() {
    
    // Allows minim to load files
    minim = new Minim(this);
  
    // Load and loop the background music
    backgroundMusic = minim.loadFile("Caliginous Hearthfire.mp3");
    backgroundMusic.loop();
    
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
    alexIdleBack.resize(width * 48/800, height * 69/800);
    alexIdleForward = loadImage("Alex Idle Forward.png");
    alexIdleForward.resize(width * 48/800, height * 69/800);
    alexIdleLeft = loadImage("Alex Idle Left.png");
    alexIdleLeft.resize(width * 48/800, height * 69/800);
    alexIdleRight = loadImage("Alex Idle Right.png");
    alexIdleRight.resize(width * 48/800, height * 69/800);

    // Load and resize Alex's movement animations
    alexBack1 = loadImage("Alex Back 1.png");
    alexBack1.resize(width * 48/800, height * 69/800);
    alexBack2 = loadImage("Alex Back 2.png");
    alexBack2.resize(width * 48/800, height * 69/800);

    alexForward1 = loadImage("Alex Forward 1.png");
    alexForward1.resize(width * 48/800, height * 69/800);
    alexForward2 = loadImage("Alex Forward 2.png");
    alexForward2.resize(width * 48/800, height * 69/800);
    
    alexLeft1 = loadImage("Alex Left 1.png");
    alexLeft1.resize(width * 48/800, height * 69/800);
    alexLeft2 = loadImage("Alex Left 2.png");
    alexLeft2.resize(width * 48/800, height * 69/800);

    alexRight1 = loadImage("Alex Right 1.png");
    alexRight1.resize(width * 48/800, height * 69/800);
    alexRight2 = loadImage("Alex Right 2.png");
    alexRight2.resize(width * 48/800, height * 69/800);

    // Load the flashlight
    flashlight = loadImage("Flashlight.png");
    flashlight.resize(width * 2, height * 2);

    // Load the room 1 pages
    for (int m = 0; m < roomOnePages.length; m++) {
      roomOnePages[m] = loadImage("Photo Page.png");
    }

    // Load the room 1 clues
    for (int i = 0; i < roomOneClues.length; i++) {
      roomOneClues[i] = loadImage("Room 1 Clue " + (i + 1) + ".png");
    }

    // Load the objects in room 1 and add them to an ArrayList
    // Array for the names of the objects in room 1
    String[] roomOneObjNames = {"Small Table.png", "Couch.png", "Table.png", "Chair.png", "Piano.png"};

    // Adds each object in room 1 into the room 1 ArrayList
    for (String name : roomOneObjNames) {
      
      PImage img = loadImage(name);
      img.resize(width * img.width/800, height * img.height/800);
      oneObjects.add(img);
    }

    // Load the room 2 pages
    for (int n = 0; n < roomTwoPages.length; n++) {
      roomTwoPages[n] = loadImage("Letter Page.png");
    }
    // Load the room 2 letters
    for (int j = 0; j < roomTwoLetters.length; j++) {
      roomTwoLetters[j] = loadImage("Room 2 Letter " + (j + 1) + ".png");
      roomTwoLetters[j].resize(roomTwoLetters[j].width / 3, roomTwoLetters[j].height / 3);
    }

    // Load the objects in room 2 and add them to an ArrayList
    // Array for the names of the objects in room 2
    String[] roomTwoObjNames = {"Small Couch 2.png", "Table.png", "Small Table.png", "Chair 2.png", "Bed.png"};

    // Adds each object in room 2 into the room 2 ArrayList
    for (String name2 : roomTwoObjNames) {

      PImage img2 = loadImage(name2);
      img2.resize(width * img2.width / 800, height * img2.height / 800);
      twoObjects.add(img2);
    }
    // Load the room 3 pages
    for (int o = 0; o < roomThreePages.length; o++) {
      roomThreePages[o] = loadImage("Letter Page.png");
      roomThreePages[o].resize(width * roomThreePages[o].width / 800, height * roomThreePages[o].height / 800);
    }

    // Load the room 3 letters
    for (int k = 0; k < roomThreeJournals.length; k++) {
      roomThreeJournals[k] = loadImage("Room 3 Letter " + (k + 1) + ".png");
      roomThreeJournals[k].resize(roomThreeJournals[k].width / 3, roomThreeJournals[k].height / 3);
    }

    // Load the objects in room 2 and add them to an ArrayList
    // Array for the names fo the objects in room 3
    String[] roomThreeObjNames = {"Large Crate R.png", 
    "Large Crate R.png", "Large Crate R.png", "Large Crate.png", "Large Crate.png", 
    "Large Crate.png", "Couch 2.png", "Small Crate R.png", "Small Crate R.png", "Small Crate.png"};
    
    // Adds each object in room 3 into the room 3 ArrayList
    for (String name3 : roomThreeObjNames) {

      PImage img3 = loadImage(name3);
      threeObjects.add(img3);
    }

    // Load all of the number buttons to be used in room 1
    for (int e = 0; e < doorFirstButt.length; e++) {
      doorFirstButt[e] = loadImage("keyboard_" + (e + 1) + ".png");
      doorFirstButt[e].resize(width * doorFirstButt[e].width/800, width * doorFirstButt[e].height/800);
    } 

    // Load all of the number buttons to be used in room 2
    for (int l = 0; l < numKeys.length; l ++) {
      numKeys[l] = loadImage("keyboard_" + l + ".png");
    }

    cabinBack = loadImage("Cabin Room Template.png");
    cabinBack.resize(cabinBack.width * width/200, cabinBack.height * height/200);

    interactKey = loadImage("keyboard_e_outline.png");
    interactKey.resize(width / 15, height / 15);

    // Load the door images
    for (int s = 0; s < doors.length; s++) {
      doors[s] = loadImage("Door.png");
    }

    // Load the stuff the doors show
    door1Text = loadImage("Door 1 Text.png");
    door2Unpassed = loadImage("Door 2 Unpassed.png");

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
      detectWallCollision();
      drawFlashlight();
      drawCurrentClue();
      doorResult();
   
    }
    // Draw room 2
    else if (intDraw == 4) {
      drawRoom2();
      drawAlex();
      //detectWallCollision();
      //drawFlashlight();
      drawCurrentClue();
    }
    // Draw room 3
    else if (intDraw == 5) {
      drawRoom3();
      drawAlex();
      //detectWallCollision();
      //drawFlashlight();
      drawCurrentClue();
    }

    // Temporary mouse coordinates for positioning purposes
    textSize(16); // Set the text size
    String coords = "Mouse X: " + mouseX + ", Mouse Y: " + mouseY;
    text(coords, 10, height - 20); // Display the coordinates at the bottom left
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
    
    currentClueImage = null;
    intCurDoor = -1;

    // Draws the background
    image(cabinBack, 0, 0);

    // Draws the objects in the room
    for (int a = 0; a < oneObjects.size(); a++) {
      image(oneObjects.get(a), oneObjectX.get(a), oneObjectY.get(a));

      // Checks collision in the objects in the room
      detectCollision(oneObjectX.get(a), oneObjectY.get(a), oneObjects.get(a).width, oneObjects.get(a).height);
    }

    // Draws the photo pages in the room
    for (int q = 0; q < roomOnePages.length; q++) {
      image(roomOnePages[q], roomOnePageX[q], roomOnePageY[q]);

      // Checks proximity of player to the photo pages for interaction
      detectInteraction(roomOneClues[q], roomOnePageX[q], roomOnePageY[q], roomOnePages[q].width, roomOnePages[q].height);
    }

    // Draws the doors in the room
    image(doors[0], doorX[0], doorY[0]);
    detectDoorInter(doorX[0], doorY[0], doors[0].width, doors[0].height, 0);
    image(doors[1], doorX[1], doorY[1]);
    detectDoorInter(doorX[1], doorY[1], doors[1].width, doors[1].height, 1);
    
    // Allows Alex to move between rooms
    if (intAlexX > width) {
      intDraw = 4;
      intAlexX = 1;
    }
  }

  // Draws the second room when intDraw = 4
  public void drawRoom2() {
    
    currentClueImage = null;

    // Draws the background
    image(cabinBack, 0, 0);
    
    // Draws the objects in the room
    for (int b = 0; b < twoObjects.size(); b++) {
      image(twoObjects.get(b), twoObjectX.get(b), twoObjectY.get(b));

      // Checks collision in the objects in the room
      detectCollision(twoObjectX.get(b), twoObjectY.get(b), twoObjects.get(b).width, twoObjects.get(b).height);
    }

    // Draws the letter pages in the room
    for (int r = 0; r < roomTwoPages.length; r++) {
      image(roomTwoPages[r], roomTwoPageX[r], roomTwoPageY[r]);

      // Checks proximity of player to the photo pages for interaction
      detectInteraction(roomTwoLetters[r], roomTwoPageX[r], roomTwoPageY[r], roomTwoPages[r].width, roomTwoPages[r].height);
    }

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

    currentClueImage = null;

    // Draws the background
    image(cabinBack, 0, 0);

    // Draws the objects in the room
    for (int c = 0; c < threeObjects.size(); c++) {
      image(threeObjects.get(c), threeObjectX.get(c), threeObjectY.get(c));

      // Checks collision in the objects in the room
      detectCollision(threeObjectX.get(c), threeObjectY.get(c), threeObjects.get(c).width, threeObjects.get(c).height);
    }

    // Draws the photo pages in the room
    for (int s = 0; s < roomThreePages.length; s++) {
      image(roomThreePages[s], roomThreePageX[s], roomThreePageY[s]);

      // Checks proximity of player to the photo pages for interaction
      detectInteraction(roomThreeJournals[s], roomThreePageX[s], roomThreePageY[s], roomThreePages[s].width, roomThreePages[s].height);
    }

    // Moves to the second room when Alex moves off the left of the screen
    if (intAlexX < 0) {
      intDraw = 4;
      intAlexX = width;
    }
    // Moves to the ending cutscene
  }

  // Detects collision with objects based on the x, y, width, and height parameters
  public void detectCollision(int intObsX, int intObsY, int intObsW, int intObsH) {

    if (intAlexX + alexIdleLeft.width / 2 > intObsX && intAlexX - alexIdleLeft.width / 2 < intObsX + intObsW && intAlexY + alexIdleLeft.height / 2 > intObsY && intAlexY < intObsY + intObsH) {

      // Moves Alex back to his previous position
      intAlexX = intAlexPrevX;
      intAlexY = intAlexPrevY;

    }
  }

  // Detects proximity of interactions
  public void detectInteraction(PImage clImg, int clX, int clY, int clW, int clH) {
    
    if (intAlexX > clX - clW && intAlexX < clX + 2 * clW && intAlexY > clY - clH && intAlexY < clY + 2 * clH) {
      
      // Saves the clue image
      currentClueImage = clImg;
      image(interactKey, intAlexX + alexIdleForward.width / 2, intAlexY - alexIdleForward.height / 2);

      }
  }
  
  // Detects proximity of door interactions
  public void detectDoorInter(int drX, int drY, int drW, int drH, int drIndex) {
    
    if (intAlexX > drX - drW / 2 && intAlexX < drX + 3/2 * drW && intAlexY > drY - drH / 2 && intAlexY < drY + 3/2 * drH) {
      intCurDoor = drIndex;
    }
  }

  // Performs the operation of each door
  public void doorResult() {

    if (intCurDoor == 0) {
    
      imageMode(CENTER);
      image(door1Text, width / 2, height / 2);
      imageMode(CORNER);
      
    }
    else if (intCurDoor == 1) {
      
      if (!boolDoorUnlocked[1]) {
        imageMode(CENTER);
        image(door2Unpassed, width / 2, height / 2);
        imageMode(CORNER);

        for(int f = 0; f < doorFirstButt.length; f++) {
          
        }

      }
      
      else if (boolDoorUnlocked[1]) {
        intDraw = 4;
      }
    }
    else if (intCurDoor == 2) {
      intDraw = 3;
    }
    else if (intCurDoor == 3) {

    }
    else if (intCurDoor == 4) {

    }
    else if (intCurDoor == 5) {

    }  

  }

  // Draws the current clue on the screen
  public void drawCurrentClue() {
    
    // Sets e pushed to true when there is an image in currentClueImage and the e key is pressed
    if (currentClueImage != null && keyPressed && key == 'e') {
      ePushed = true;
    }
    // Draws the clue on the screen after the e key is pressed
    else if (ePushed && currentClueImage != null) {
      
      imageMode(CENTER);
      image(currentClueImage, width / 2, height / 2);
      imageMode(CORNER);
    } 
    
    // Sets e pushed to false when there is no image in currentClueImage
    else {
      ePushed = false;
    }
}

  public void detectWallCollision() {
     if (intAlexX - alexIdleForward.width / 2 < width * 66/800 || intAlexX + alexIdleForward.width / 2 > 739 || intAlexY + alexIdleForward.height / 2 > width * 776/800 || intAlexY < height * 209/800){
      
      intAlexX = intAlexPrevX;
      intAlexY = intAlexPrevY;
     }
  }

  public void drawEnd() {
    // Some random thingy here I suppose
  }

  // Draws a flashlight for Alex
  public void drawFlashlight() {
    imageMode(CENTER);
    image(flashlight, intAlexX , intAlexY);
    imageMode(CORNER);
  }

  // Draws Alex to the screen
  public void drawAlex() {

    if (wPressed) {

      // Saves the previous y value for collision detection
      intAlexPrevY = intAlexY;

      // Makes Alex move forward
      intAlexY -= intAlexSpeed;

      // Draws the sprite as long as Alex is not moving diagonally
      if (!(strLorR.equals("Left") || strLorR.equals("Right"))) {
        // Draws the first sprite
        if (intSprite == 1) {
          imageMode(CENTER);
          image(alexForward1, intAlexX, intAlexY);
          imageMode(CORNER);
        }
        // Draws the second sprite
        else if (intSprite == 2) {
          imageMode(CENTER);
          image(alexForward2, intAlexX, intAlexY);
          imageMode(CORNER);
        }
      }
    }

    if (sPressed) {
      
      // Saves the previous y value for collision detection
      intAlexPrevY = intAlexY;

      // Makes Alex move backward
      intAlexY += intAlexSpeed;
      
      // Draws the sprite as long as Alex is not moving diagonally
      if (!(strLorR.equals("Left") || strLorR.equals("Right"))) {
        
        // Draws the first sprite
        if (intSprite == 1) {
          imageMode(CENTER);
          image(alexBack1, intAlexX, intAlexY);
          imageMode(CORNER);
        }
        // Draws the second sprite
        else if (intSprite == 2) {
          imageMode(CENTER);
          image(alexBack2, intAlexX, intAlexY);
          imageMode(CORNER);
        }
      }
    }
    if (aPressed) {
      
      // Saves the previous x value for collision detection
      intAlexPrevX = intAlexX;

      // Makes Alex move to the left
      intAlexX -= intAlexSpeed;

      if (!strLorR.equals("Right")) {
        // Draws the first sprite
        if (intSprite == 1) {
          imageMode(CENTER);
          image(alexLeft1, intAlexX, intAlexY);
          imageMode(CORNER);
        }
        // Draws the second sprite
        else if (intSprite == 2) {
          imageMode(CENTER);
          image(alexLeft2, intAlexX, intAlexY);
          imageMode(CORNER);
        }
        // Prioritizes the left moving animation when Alex moves diagonally
        strLorR = "Left";
      }
    }
    if (dPressed) {

      // Saves the previous x value for collision detection
      intAlexPrevX = intAlexX;

      // Makes Alex move to the right
      intAlexX += intAlexSpeed;

      if (!strLorR.equals("Left")) {
        // Draws the first sprite
        if (intSprite == 1) {
          imageMode(CENTER);
          image(alexRight1, intAlexX, intAlexY);
          imageMode(CORNER);
        }

        // Draws the second sprite
        else if (intSprite == 2) {
          imageMode(CENTER);
          image(alexRight2, intAlexX, intAlexY);
          imageMode(CORNER);
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
        imageMode(CENTER);
        image(alexIdleBack, intAlexX, intAlexY);
        imageMode(CORNER);
      }
      else if (strDir.equals("Up")) {
        imageMode(CENTER);
        image(alexIdleForward, intAlexX, intAlexY);
        imageMode(CORNER);
      }
      else if (strDir.equals("Left")) {
        imageMode(CENTER);
        image(alexIdleLeft, intAlexX, intAlexY);
        imageMode(CORNER);
      }
      else if (strDir.equals("Right")) {
        imageMode(CENTER);
        image(alexIdleRight, intAlexX, intAlexY);
        imageMode(CORNER);
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