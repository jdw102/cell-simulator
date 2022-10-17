# Cell Society Design Plan
### Team Number
### Names


## Design Overview



#### Overall design goals


#### Classes, their behavior and relationships (**not** instance variables)

 * As CRC

This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |


 *  OR UML in Markdown:

@startuml
Super <|-- Sub

class Super {
  void overrideMe()
  String toString()
}

class Sub {
  void overrideMe()
}
@enduml


 * OR UML Image

Here is a graphical look at our design:

![This is cool, too bad you can't see it](online-shopping-uml-example.png "An initial UI")

or made from [a tool that generates UML from existing code](http://staruml.io/).



## Design Details

#### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

#### Extension Cases

 * What commonalities will be factored out into superclasses?
 
 * How will differences be handled when superclasses are extended?
 
 

## Design Considerations

Justification for classes and methods given in the design.

#### Design Issue #1
Deciding whether we should have model and view at individual cell level.
 * Design #1 Tradeoffs
   * Description
   One big view one big model.
   * Pros
 
   * Cons

 * Design #2 Tradeoffs
   * Description
   Bunch of tiny views bunch of tiny models.
   * Pros
 
   * Cons
 
 * Justification for choice
Chose the individual cell models and views to better utilize the observer and observable interfaces.

#### Design Issue #2
What should define the difference between different simulations.
 * Design #1 Tradeoffs
   * Description 
     * Have a different model for each one.
   * Pros
 
   * Cons
     * 

 * Design #2 Tradeoffs
   * Description
     * Have a separate state handler class.
   * Pros
 
   * Cons
 
 * Justification for choice


#### Data Structure Implementation Change

 * Implementation #1
   * Description
      
   * Classes possibly affected
 
   * Methods possibly affected

 * Implementation #2
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected
 
 * Justification for how implementation choices is hidden
 

#### File Format Implementation Change

 * Implementation #1
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected

 * Implementation #2
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected
 
 * Justification for how implementation choices is hidden


#### JavaFX "Grid" Component Implementation Change

 * Implementation #1
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected

 * Implementation #2
   * Description
   
   * Classes possibly affected
 
   * Methods possibly affected
 
 * Justification for how implementation choices is hidden




## User Interface

Initial Design Idea: 

![This was our initial idea for the UI, but we found that the simulation information did not need to take up so much space](images/altdesign.png "Alternative UI Design")

Final Design Idea:

![This final design will utilize a pop up dialog window to show extra information about the simulation](images/finaldesign.png "The final design of our UI")

## Team Responsibilities

#### Primary Responsibilities
 * Team Member #1

 * Team Member #2

 * Team Member #3

 * Team Member #4
Jerry Worthy - UI/Views

#### Secondary Responsibilities
 * Team Member #1

 * Team Member #2

 * Team Member #3

 * Team Member #4
 Jerry Worthy - Design

#### Schedule Plan

