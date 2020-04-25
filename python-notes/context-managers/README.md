Using Python context managers
=============================

Let's use 'reading a file' as a basis for the examples used in this document, as that is the easiest context to read and write about for this subject.

Reading a file the tried and tested way
---------------------------------------

If you think of a physical file, like those in a filing cabinet (if those still exist), you need to open a file to be able to read its contents. And once you're done, you would ideally want to close and store it properly before you have the change to spill coffee all over it. The same kind of applies to digital files.

So here's the summary of steps you need to take when dealing with any file:
1. Open the file;
2. Read the contents; then
3. Close the file.

Let's say that you have a file called "my_text_file.txt" that contains this:
```
1,Chicken
2,Fish
3,Lettuce
4,Carrots
5,Cheese
```

Let's see how you would probably do it in Java "the old-fashioned way":
```java
static void readTextFileBasic() throws IOException {
        // Open the file
        final File file = new File("my_text_file.txt");
        final Reader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        // Read the contents
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        
        // Close the file
        bufferedReader.close();
    }
```

You could do it this way in Python too:
```python
f = open('my_text_file.txt')
print(f.read())
f.close()
```
Fewer lines of code, and it does the job. But is there a better way to do it?

Printing all the lines automatically
------------------------------------

In the previous section, we either manually read each line in the Java case, or we called for the whole file's contents in the Python case. What if we wanted to read it in a looping fashion? Sure that can be done too.

In Java, we can use a `while` loop - while there is a line to be read, give me that line until you can't give me any more lines:
```java
static void readTextFileBasicWithManualWhileLoop() throws IOException {
    final File file = new File("my_text_file.txt");
    final Reader fileReader = new FileReader(file);
    final BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line = null;
    while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
    }
    bufferedReader.close();
}
```

Thankfully we don't need as many lines in Python. Python's `file` that is returned by the `open()` function is an iterable, which means that we can use the Python `for` loop with it:
```python
f = open('my_text_file.txt')
for line in f:
    print(line)
f.close()
```

Context managers
----------------

Okay, so what this have anything to do with context managers?

In Python, context managers are things that can help us automatically manage the start and end of some operation - in this case, the opening and closing of a file. You access this functionality with the `with` keyword like so:
```python
with open('my_text_file.txt') as f:
    for line in f:
        print(line)
```
There is no need to explicitly open or close the file - it's all done for you. The internal magic is that the `__enter__()` and `__exit__()` methods are implemented for the python `file` object. But since all this is essentially an interface, you can also write custom code that allows you to use the `with` keyword too, as long as you implement these two methods in your class. OR, you can use the `@contextmanager` decorator: https://docs.python.org/3/library/contextlib.html#contextlib.contextmanager.

In a similar vein, Java has objects that implement the `Autoclosable` interface, which allows you to use the try-with-resources pattern:
```java
static void readTextFileWithTryResources() throws IOException {
    final File file = new File("my_text_file.txt");
    final Reader fileReader = new FileReader(file);
    try(BufferedReader bufferedReader = new BufferedReader(fileReader)) {
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        
}
```

Although one can use a newer library such as this nowadays:
```java
static void readTextFileWithStream() throws IOException {
    Files.lines(Path.of("my_text_file.txt"))
            .forEach(System.out::println);
}
```