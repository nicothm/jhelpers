# JHelpers
This project combines common functions for java-programmers and helpers for avoiding common problems in the java world like unexpected null-references or not-readable string-representation of objects.

Are you tired of implementing equals(), hashCode() and toString()? You don't have to!

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
    DebugHelpers.require(value>0, "Value has to be > 0!");
}
```

# Other stuff
Currently we have a cool script in tools/ for adding/updating a custom header to all files in the given directorys.

If you like to check it out, simply build it with maven:

```sh
$: mvn clean install
```

and add it to your projects dependencies.
