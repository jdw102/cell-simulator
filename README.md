Cell Society
====

This project implements a cellular automata simulator.

Names: Daniel Feinblatt, Ryan Wolfram, Jerry Worthy, Mazen Selim


### Timeline

Start Date: October 12, 2022

Finish Date: October 30, 2022

Hours Spent: 200+ hours

### Primary Roles
- Daniel Feinblatt: OOP designer, code reviewer, controller implementer
- Mazen Selim: Controller and model implementer, design collaborator
- Jerry Worthy: Frontend implementer, design collaborator
- Ryan Wolfram: Model implementer

### Resources Used

Collaborators:
- https://icons8.com/: used for icons
- https://www.geeksforgeeks.org/permute-string-changing-case/: algorithm for iterating through all possible upper-lower combinations of a string

Attributions for others' work:


### Running the Program

Main class: Main.java

How to use the program:
- User starts application and selects their language.
- User is presented with a default grid for the simulation with the default simulation being set in the settings.properties file.
- User can use dropdown to select the type of cell automata simulation they want which will clear the grid and display a new default grid.
- User can click the squares to manually customize their starting states.
- User can edit cell state colors using an edit colors button.
- User can edit simulation information by clicking on the information button. This allows them to also change the grid neighbor policy and neighbor radius using a dropdown menu and slider respectively.
- User can save the current state and information to a new .sim file.
- User can choose to upload a `.sim` file containing simulation info.
- Upon upload, a new grid is built with the given initial state.
- User can press the play button to have the simulation autoplay or can step through the simulation
  one iteration at a time using the forward and backward buttons.
- User can use the slider to adjust the autoplay animation speed.

Data files needed: 
- All files found in the `resources` folder

Features implemented:
- Animate a simulation indefinitely by user pressing the play button, and change the speed at which the simulation progresses
- User can pause, resume, and step through the simulation one step at a time manually
- Simulations implemented
	- Conway's game of life simulation
	- Spreading of fire simulation
	- Schelling's model of segregation simulation
	- Wa-Tor World model of predator-prey relationships simulation
	- Rock, Paper, Scissors simulation
	- Percolation simulation
- Ways to setup a simulation:
	- Upload a .sim file
	- Click on cells to change their state before a simulation starts as well as while a simulation is running
- See and change simulation's descriptive information
- Adjustable window size without ruining the user interface
- Save the current state of a simulation to a .sim file
- Create multiple windows to run multiple simulations simultaneously
- Choose between three different color modes for the user interface
- Choose between three different languages when starting the program
- Choose between different arrangements of neighbors and grid edge policies. These include complete, cardinal, and toroidal. The user can also specify a larger radius for the neighborhood (such as all cells within 2 cells of the center)
- Two different views of the simulation: grid and histogram that graphs the populations as they change
- Allow user to change the edge policy/neighbor arrangement while the simulation is running

### Notes/Assumptions

Assumptions or Simplifications:
- We assume that the parameter given in a simulation is always a number value.
- We assume in wator world the fish move first before sharks do.
- We assume all the properties files are complete and are not malformed.
- We assume that a state is only one word with a capital first letter.
- We assume that every cell state has a corresponding color in the colors.properties files, state class, a state handler, a value for it in the state handler properties file, and a value for it in the Enum of possible states for that simulation. 
- In the settings.properties file, all simulations need to have a default parameter, even if the simulation does not utilize a parameter.


Interesting data files:
- glider gun for game of life

Known Bugs: When making a new window on a non-symmetrical grid, it will remove some rows and eventually an index out
of bounds exception is thrown. Sometimes the error thrown for an invalid simulation type does not trigger the show alert method in the display view. Could be fixed given more time to debug.

Noteworthy features: 
- Changing colors dynamically
- New window opens from last start
- Can change to histogram view dynamically
- Can change neighborhood settings dynamically
- Can change to toroidal grid dynamically.


### Impressions
The topic of the project is interesting it certainly is pretty cool to see all these simulations running. There was an overwhelming amount to implement, and we feel that it is not reasonable for the professor to state that all functionality is pretty easy to implement, and the challenge is only in the design. We found that a lot of functionality was quite difficult to implement, so it felt belitting and unsupportive of learning when he was saying that things should be easy. 
