# jlooping
A simple way to have multiple scripts in a singe-thread environment, almost like a state machine, but not.

This allows you to have multiple pieces of code run practically side by side while keeping code clean. Say you have a function the prints `1` and a function that prints `2`. You could do a while loop like this:
```java
while (true) {
  System.out.println(1);
  System.out.println(2);
}
```
But when you add more complex code you will end up with a messy code that is hard to collaborate on. This allows you to add as many different "loops" to a main function cleanly.

While virtual threads are coming in JDK 19, my projects can't use a JDK later than 16, [like this](https://github.com/XaverianTeamRobotics/FtcRobotController).

If you are still confused, there are some examples in the `builtin` package of the code.

## Consult the wiki for usage.
