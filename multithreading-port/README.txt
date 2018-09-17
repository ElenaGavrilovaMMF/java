STUDENT: GAVRILOVA ELENA
-----------------------------------------------------------------------------
PROJECT NAME: multithreadingPort
MODULES: epam
ADDITIONAL LIBRARIES: 
   Maven: org.projectlombok:lombok:1.16.20
-----------------------------------------------------------------------------
INDIVIDUAL TASK:
1. Port. Ships enter the port to unload or load containers and moor to 
   the berths. Each berth can only have one ship. Containers are reloaded 
   from the ship to the port store and / or from the store to the 
   ship. The number of containers can not exceed the capacity of the 
   store or ship. Each ship must be serviced.
-----------------------------------------------------------------------------
BRIEFLY ABOUT THE TASK EXECUTION LOGIC:
   The store facility has two berths.
   The ship, in addition to its parameters, has the condition and purpose of 
arrival to the port as data.
   The ship can have three purposes: load, unload and both at the same time.
   If the ship has both targets, then at first it is unloaded, 
and only then it is loaded.
   If the ship can not be serviced for any reason, it is serviced in part.
   If the ship is serviced, its state changes to the DONE.
   If the ship can not be serviced for any reason, it is serviced in part, 
as far as possible. His state changes to PARTIALLY_DONE and the ship 
leaves the berth.
   In this case, the ship is given 4 attempts to try the task.
   If the target is not reached, the ship is released partially serviced.
-----------------------------------------------------------------------------
NOTES:
UML: 
	- UML classes are represented by groups of packages and are located
	in the corresponding project folders.
PMD: 
	- I did not take into account the recommendation of ShortClassName
	(Ship in entity) because I consider the class name given by me to be
	the most optimal one.
	- I did not take into account recommendation AtLeastOneConstructor,
	because I used the library LOMBOK and set the constructor an anotation
	(@NoArgsConstructor or @AllArgsConstructor).
	- I did not take into account recommendation CommentDefaultAccessModifier,
	as there is a contradiction with CHECKSTYLE.
    - I did not take into account recommendation OnleOneReturn,
    because I think it is not advisable to change this method EQUALS
    in this case as recommended PMD.
CHECKSTYLE:
    - I did not check the tests for checkstyle, because the default does
    not check them.
    - I ignored the error NewlineAtEndOfFile (exception/ShipException.java),
     since there is no such fact in fact.