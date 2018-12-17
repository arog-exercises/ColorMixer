# Color Mixer Exercise

A Color class like that can be used inside some Java modules e.g. Spring Boot microservices. 
It uses a Gradle build script and jar packaging.

Build it from the root (this) directory with "gradle jar", assuming you have Gradle and Java installed.
The library only has unit tests. No console or other UI, nor the "main" function.
Tests are executed as part of Gradle build. Yoiu can run them in the IDE as well.  

## General Extensibility Considerations
1. A color does not always consist of three components.
2. Color components can be 48+ bits and we should not make any assumptions about it.
3. Mixing colors can mean different things (adding, subtracting, adjusting).

## The Bare Minimum
The bare minimum code (and tests) are in the com.alex.dlassignment.color package.
They cover the three design considerations above, however are tailored towards the assignment's 
256-bit RGB task.

## Robust Color Mixer
As the bare minimum above did not take long, I started thinking how to implement a robust Color class, 
that can use both additive and subtractive logic. The Color abstraction itself only encapsulates 
the components. A component needs to be smart enough to know how to mix and/or adjust: the case with 
Black in the subtractive system. In general only component knows about its logic to mix.
A Color can combine components of different nature. The adjustment happens after mixing 
(if any for teh adjusting colors like Black).

Both approaches are valid, and the production should use the bare minimum: Keep It Simple Stupid.
However it is an interview exercise, so some showing off is in order.

I tried to explain design decisions in the comments as well. There are some caveats like teh absence of short type literals in Java,
bytes being signed, etc.
 