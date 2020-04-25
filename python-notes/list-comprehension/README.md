List Comprehension - powerful `for` loops
=========================================

Introduction
------------

One important purpose of programming is so that we can automate repetitive tasks. Loops are how we express our desire to repeat a task. This document intends to show you how `for` loops and list comprehension works in Python, and to show the person who has grown up using Java to avoid standing out when writing Python `for` loops. Jokes aside, this is to show the word economy of Python when it comes to looping.


Classic `for` loops
-------------------

Let's say that we have a list or an array of numbers that we would like to loop through and print each one of them. As you are probably familiar, this is a pretty simple task in Java:
```java
final int[] integers = {1, 2, 3, 4, 5};
for (int i = 0; i < integers.length; i++) {
    System.out.println(integers[i]);
}
```

And at this point, you, an expert in Java, can say that you can do one better - you can use the enhanced `for` loop:
```java
final int[] integers = {1, 2, 3, 4, 5};
for (final int integer : integers) {
    System.out.println(integer);
}
```

In Python, the basic `for` loop is similar to Java's enchanced `for` loop:
```python
integers = [1, 2, 3, 4, 5]
for integer in integers:
    print(integer)
```

At this point, you would probably ask, "Wait, what if I need the index number?"

The temptation is to write insert a counter variable. Do this and seasoned Python developers will ask you "Are you a Java developer? I can tell."
```python
# Do this at the expense of your ego, and possibly runtime performance:
index = 0
integers = [1, 2, 3, 4, 5]
for integer in integers:
    print(index + ': ' + integer)
    index = index + 1
```

Instead, consider this:
```python
integers = [1, 2, 3, 4, 5]
for index, integer in enumerate(integers):
    print(index + ': ' + integer)
```
`enumerate` returns an iterable of tuples of your index and your value: (index, value). You then assign the index and value to your separate variables `index` and `integer`.


Simple list comprehension
-------------------------
Okay, cool, now, what if you wanted had a list or an array of numbers and you wanted to double them?

This is how you might do it in Java:
```java
final int[] integers = {1, 2, 3, 4, 5};
final List<Integer> result = new ArrayList<>();
for (final int integer : integers) {
    result.add(integer * 2);
}
```

If you did a direct translation to Python, you might end up with something like this:
```python
# Do this at the expense of your ego, and possibly runtime performance:
integers = [1, 2, 3, 4, 5]
result = []
for integer in integers:
    result.append(integer)
```
No doubt, this implementation would work. But this won't fly in the Python crowd.

This is where _list comprehension_ comes in. You simply do this:
```python
integers = [1, 2, 3, 4, 5]
result = [integer * 2 for integer in integers]
print(result)  # prints [2, 4, 6, 8, 10]
```
And there you go - your looping, your doubling, and the creation of your list in single line of code.

Of course, in Java, one could use `Stream`s:
```java
final int[] integers = {1, 2, 3, 4, 5};
final List<Integer> result = Arrays.stream(integers)
                .map(integer -> integer * 2)
                .boxed()
                .collect(toList());
```

In Python, if you wanted to use `map`, you can too, but for a simple case like this, it might be more cumbersome to write;
```python
integers = [1, 2, 3, 4, 5]
result = list(map(lambda x : x * 2, integers))
```

List comprehension with conditions
----------------------------------

You can also use list comprehension with conditions. For example, maybe you wanted to double your integers, but only if the integer is more than 3. Here's the list comprehension in action:
```python
integers = [1, 2, 3, 4, 5]
result = [integer * 2 for integer in integers if integer > 3]  # [8, 10]
```

Using the Java `Stream` API, the equivalent would probably be:
```java
final int[] integers = {1, 2, 3, 4, 5};
final List<Integer> b = Arrays.stream(integers)
                .filter(integer -> integer > 3)
                .map(integer -> integer * 2)
                .boxed()
                .collect(toList());
```
Again, you can use `filter` and `map` together too in Python, but this is to show how powerful list comprehensions are. A language that has something similar would be C#'s linq syntax:
```c#
int[] integers = new int[] { 1, 2, 3, 4, 5 };

// Define the query expression.
IEnumerable<int> integerQuery =
    from integer in integers
    where integer > 3
    select integer;
```
