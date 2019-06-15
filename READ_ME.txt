This project is for Williams Sonoma.
The problem solved here is to minimizing the amount of zip code ranges to the minimum needed to cover all ranges from the input sets.

** Please note this project has to jar files (gson, log4j) and if you run it from your IDE, make sure they are in the classpath.

This program is flexible and can be easily modified to accept different inputs and generate different outputs.
Currently supported input: json flat file, demo class with fake data hard coded
Currently supported output: json flat file

IOConfigFactory.java file is a factory pattern that returns a different object for variety of inputs based on property in the zipConfig.properties file

LogService.java is a simple log4j implementation with Singleton pattern 

IOConfigurations reads from zipConfig.properties file and contain a variety of configurable parameters needed in the project. Example: source and target directory, file extension ect.