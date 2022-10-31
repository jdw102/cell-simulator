# Cell Society Design Final
### Names
- Daniel Feinblatt
- Jerry Worthy
- Ryan Wolfram
- Mazen Selim

## Team Roles and Responsibilities
 * Team Member #1: Daniel Feinblatt
	 * OOP designer, code reviewer, controller implementer
 * Team Member #2: Mazen Selim
	 * Controller and model implementer, design collaborator
 * Team Member #3: Jerry Worthy
	 * Frontend implementer, design collaborator
 * Team Member #4: Ryan Wolfram
	 * Model implementer


## Design goals


#### What Features are Easy to Add
- Creating new simulations
- Adding more languages
- Adding new color schemes

## High-level Design
See design planning document for our high level design.

#### Core Classes
See our design planning document and the UML diagram we created for details about core classes and high level design. The only addition we made since the design document is to add the new models, as described in the "Significant differences from Original Plan" section. Otherwise, our plan is entirely representative of our final result.

## Assumptions that Affect the Design
See README for our assumptions
#### Features Affected by Assumptions
- The assumption that in the settings.properties file, all simulations need to have a default parameter, even if the simulation does not utilize a parameter, made it such that we do not instantiate objects differently depending on if the simulation takes parameters or not. Instead, we instantiate the objects the same way and then check if the simulation is one that takes parameters, and if so, then we set that parameter. Otherwise, we ignore the parameter.
- Because we assume that every cell state has a corresponding color in the colors.properties files, state class, a state handler, a value for it in the state handler properties file, and a value for it in the Enum of possible states for that simulation, if the name of a state is wanting to be changed, there needs to be many different name changes in classes and properties files.

## Significant differences from Original Plan
- We did not deviate from our original plan much. The largest difference we could think of is that we decided to implement additional grid models for the more complex simulations that next state rely upon other next states or other cells outside its direct neighborhood.

## New Features HowTo

#### Easy to Add Features
- Adding more simulations requires creating some new files and classes, not changing existing ones:
	- If the simulation follows the simple format where the next state only depends on the current states of the cells around it (simulations such as spreading of fire, game of life, rock, paper, scissors, and percolation all fall into this category), then:
		- Add colors properties file to specify what colors to be used for each state
		- Create a new statehandler class that `extends` the `StateHandler` abstract class that defines the transition function for updating the states
		- Add a properties file for the new state handler to map the state names to their identifying number values
		- Create a new package in the `cellsociety/cellstates/` folder for the new simulation
		- Create a new class that `extends` the `State` class that represents each state for the simulation.
		- Create a new `Enum` that has all the possible state names. See another simulation package for an example.
		- Add a default parameter for the simulation to the Settings.properties file
	- If the simulation has a more complex dependency or update algorithm (e.g. wator world or segregation), then a new Model should be made that can either `extend` the `DefaultGridModel` if only minor changes to its logic are needed, or `implements` the `GridModel` interface to create a more customized different update logic.
- Give the user the ability to create a randomly distributed starting state
	- Can be accomplished by creating a method in the controller that creates a random csv with randomly seleted values and then passing that to the setup simulation method.
- Ability to disable the grid boarders
	- Can be accomplished by simply adding a button in the UI that when clicked, calls a method in the `GridView` to iterate through each `CellView` and remove the boarder from its `CellPane`
- Ability for user to change simulation parameters
	- Can add a text box to the UI in the `InfoPopUp` class that, when changed, calls a method in the controller to change the parameter of the current state handler.

#### Other Features not yet Done
- Making the cellViews contain images instead of colors
	- This would require us to add checking when reading the resource file for the simulation colors to determine if it is an image or a color. It would also require changes in `StateColors`, `ColorPopUp`, and both implementations of `DataView`. 
