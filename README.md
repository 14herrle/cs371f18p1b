# cs371f18p1a Topwords

@author Alejandro Herrle

This build is a work in progress

    # Learning Objectives

* Transition from imperative object-oriented and scripting languages to Scala ("Scala as a better Java")
* Functional versus nonfunctional requirements
* An understanding of
    * stream processing
    * pipes and filters architecture
    * Observer design pattern
    * time/space complexity and scalability
    * separation of processing and I/O concerns
    * test-driven development (TDD)
* Experience with Git source code management
* Building with SBT
* Automated unit testing with JUnit
* Continuous integration with Travis

# Extra Credit Features Implemented
* Optional 4th input argument for printout frequency added
* Case insensitive input supported

# System requirements

* Java 8 SDK or later
* [SBT](https://www.scala-sbt.org/1.x/docs/Setup.html)

# Running the Application

On Linux or Mac OS X:

    $ sbt run

or

    $ sbt 'run arg1 arg2 arg3'
	
On Windows:
	
    > sbt run

or

    > sbt "run arg1 arg2 arg3"

# Running the Tests

On Linux or Mac OS X:

    $ sbt test
	
On Windows:
	
    > sbt test

# Running the Application Outside SBT

This allows passing command-line arguments directly:

On Linux or Mac OS X:

    $ sbt stage
    $ ./target/universal/stage/bin/topwords arg1 arg2 arg3

On Windows:

    > sbt stage
    > .\target\universal\stage\bin\topwords arg1 arg2 arg3
