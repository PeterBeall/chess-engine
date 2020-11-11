This is a simple chess program I'm working on. I will make a chess board on which pieces can be moved only legally. After that, I might make a chess computer which the user can play against. 

The main method is in ChessEngine.java, so run that file using the terminal.

The board is just displayed as text in the terminal. White pieces are letters in parentheses, black pieces are in curly brackets, and empty spaces are in square brackets. For example, the starting position looks like this:

r\f a  b  c  d  e  f  g  h
8| {R}{N}{B}{Q}{K}{B}{N}{R}
7| {_}{_}{_}{_}{_}{_}{_}{_}
6| [ ][ ][ ][ ][ ][ ][ ][ ]
5| [ ][ ][ ][ ][ ][ ][ ][ ]
4| [ ][ ][ ][ ][ ][ ][ ][ ]
3| [ ][ ][ ][ ][ ][ ][ ][ ]
2| (_)(_)(_)(_)(_)(_)(_)(_)
1| (R)(N)(B)(Q)(K)(B)(N)(R)

You move pieces by entering the coordinates of the piece you want to move, then entering the coordinates of the position you want to move it to. Enter one coordinate at a time. For example, to move the e2 pawn to e4, enter e, then 2, then e, then 4.

To test, run TestRunner.java. This uses the JUnit package:

https://github.com/downloads/junit-team/junit/junit-4.10.jar

I dowloaded the jar into C:\JUNIT, then I set the environment variable %JUNIT_HOME% to C:\JUNIT and I set CLASSPATH to %CLASSPATH%;%JUNIT_HOME%\junit-4.10.jar;.;