# Simple Java Worker

This example uses Orkes Java Client 3 fat jar: `libs/orkes-conductor-client-${version}-all.jar`.

Min Java Version: 11.

The jar includes all transitive dependencies like:
- Jackson.
- OkHttp3.
- Guava.
- Slf4j - but no specific implementation to allow the user to choose between Log4j, Logback or another.

Here's a list of the classes included in jar: [classes.txt](classes.txt).

## Usage

### 1 - Compile
```shell
javac -cp "libs/*" -d . src/io/orkes/conductor/examples/worker/SimpleJavaWorker.java
```

### 2 - Execute
```shell
java -cp "libs/*:./resources:." io.orkes.conductor.examples.worker.SimpleJavaWorker
```