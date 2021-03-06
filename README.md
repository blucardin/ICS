# ICS
My cluminating assesment for for my introduction to computer science course. The instructor did not require me to create a repository, however it has helped me with the development. 

# Final Report

## How did you approach this project for each stage of the Software Development Cycle. Provide a rationale if needed (ie. Why did you choose to do something this way instead of that way?)


I approached this project in the way that I felt would be most efficient. 

First, I started brainstorming the logic for each of the games. During this section of development I noted the techniques I needed to use to make each game. I planned how I was going to store the state of the snake, the board, and the target, as well as how to check who had won in Xs and Os, and more. Most importantly, I thought of ways to structure the menus. I settled on making a main file that had a main menu that led to individual menus for each game. 

Second, I set up my coding environment. I wanted to be able to code at school, and at my house, but I also wanted to use my own IDE and code suggestions. To be able to do this, I set up a docker container with java 16, then uploaded the container to a github repository. From there I opened the repository with replit and configured the start button to run the command to compile and start the code. This way, I could open the repository on Visual Studio Code and use my extensions like Github Copilot and Java Intellisense, then push it to github and access the code from replit. This system made it much easier to develop the game.
 
Third, I started coding the menus and setting up the file structure. Every game (as well as its menu, shop, and “how to” section) is contained within its own file. The main file starts the main menu and runs the menu method of a game file when the user wants to play that game. The menu method then runs the game method, shop method, or “how to” section depending on what the user wants to do. This separates my code into manageable sections and makes it easier for me to understand and debug. 

Next, I programmed each game one by one. I started with the base logic needed to make the game work, then I added extra elements. The menus and shops were repeated almost identically for each game as they did not need to be re-written. I tested and iterated over my designs to ensure quality. 

Finally, I added all the comments and beautified the code.

In the code I made heavy use of arrays, arraylists, loops, and indexing as I found that to be the simplest way of manipulating data. 


## What steps were taken to make this project better?
I tried to make this project the best that I could. I crowdsourced feedback from my friends to make my game easier for the user to use, and to identify bugs. I used spellcheck and comment generating extensions to increase the readability of the document. I implemented version control to keep track of changes. Finally, I used a code beautifier to make code more readable. 

## What would you improve on if you could do the project again?
I cannot think of anything I could do better if I were to do the project again. I am happy with the way my project turned out. I also got more experience with docker, bash, github and other tools to allow me to work on this project from school and home.  
