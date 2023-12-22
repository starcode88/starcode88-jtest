# starcode88-junit

This library contains utility classes for implementing tests in Java using
JUnit, especially if we are using JUnit to impement integration tests.

This library contains a class Assertions which is a wrapper class for the
class Assertions in JUnit. The problem of the class in JUnit has the problem
that the assert functions will log to stderr, they don't log to stdout.
If there is a error in an integration test you would typically check the
log file what has been done before. But you won't find the assertion in the
log file. Therefore the class Assertions in this library will also log to
stdout, the it is much easier to see what has been done before the
assertion has failed.

In addition this library contains classes for a standardized way to log
test cases into your log file. That contains the ID of test cases, the
description, the steps which will be done and the expected result and
the final test result.

The description of test cases will be read from the file "testcases.json".

In the folder src/test/resources you will find some example test cases which
show how the classes can be used.

