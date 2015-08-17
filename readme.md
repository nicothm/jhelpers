# JDevTools
This project tries to combine common functions for java-programmers and avoiding common problems in the java world.

Have you ever think about a world where you can simply implement equals(), hashCode() and toString() by calling a method, than this project will make your life much easier.
With this project you can do this:

```java
public String toString() {
    return RichObject.toString(this);
}
public int hashCode() {
    return RichObject.hashCode(this);
}
public boolean equals(Object other) {
    return RichObject.equals(this, other);
}
```

Or for checking if a requirement is fulfilled, simply use this:
```java
public void yourFunction(int value) {
    DebugHelpers.require(value>0, "Value has to be >0!");
}
```

If you like to check it out, simply build it with maven or sbt.

In the console use either:
```sh
$: mvn clean install
```
or:
```sh
$: sbt compile
```

and add it to your projects dependencies.

