[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/B2OnycBl)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=15143636&assignment_repo_type=AssignmentRepo)
# Eternal Memories

Synopsis

Alex, a young photographer, travels into a forest to capture the beauty of nature. As time flies, evening comes, and he realizes he has veered off the path. Night falls, and he has no choice but to stay in the forest overnight. Spotting an abandoned cabin in the distance, he cautiously enters. 

Throughout his exploration of the cabin, Alex realizes things are not as they seem. From doors locking behind him, eerie photographs, and ancient letters and journals hint at a dark past. With the suffocating atmosphere, Alex realizes he must piece the cabin's secrets in order to escape in time... before he is trapped within the confines of the cabin forever.

Gameplay Mechanics
The player uses the WASD keys to move up, left, down, and right in each room of the cabin. They press the e key to interact with clues, and the r key to reset their position to the middle of the cabin (to counter a bug that sometimes teleports them into random places). Once the player opens a clue, they can walk away to remove it from their screen. The player must approach the doors on the right of each room and solve the puzzle based on clues in the current room to enter the next room. Each puzzle shows either numbers or letter buttons, which the player must click on with their mouse in order to input a code. The player can click on the enter button to enter their code.

Good Ending
In the good ending, the player successfully escapes from the cabin. The player can reach the good ending if they go through the three rooms within the time limit of 20 minutes, and never take more than three attempts to guess a code.

Bad Ending
In the bad ending, the player is consumed by the ghost of the cabin owner. This occurs either when the player has more than 3 incorrect attempts at a code, or takes more than 20 minutes to escape.

Limitations
There is an uncommon bug in the code that teleports the player in a random place and renders them unable to move. This occurs when the player enters a room and presses only one direction key to immediately move the player into a wall or object. However, if the player moves in a different direction before walking into a wall or object, this bug does not occur and the collision detection in the game works fine. To combat this issue, the player can press the r key to reset their player position.

When any key other than WASD is pressed, the player character very shortly flashes invisible.

The cabin walls of the program cannot be adjusted to any shape or size, all of the must have the same square boundaries.