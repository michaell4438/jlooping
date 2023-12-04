# jlooping
A simple way to have multiple scripts in a singe-thread environment, almost like a state machine, but not.

This allows you to have multiple pieces of code run practically side by side while keeping code clean. Say you have a function the prints `1` and a function that prints `2`. You could do a while loop like this:
```java
while (true) {
  System.out.println(1);
  System.out.println(2);
}
```
But when you add more complex code you will end up with a messy code that is hard to collaborate on. This library allows you to add as many different "loops" to a main function cleanly.

If you are still confused, there are some examples in the `com.michaell.looping.builtin.tests` package of the code.

## Consult the wiki for usage.
