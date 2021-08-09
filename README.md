![Java CI](https://github.com/rabend/json-data-generator/workflows/Java%20CI/badge.svg?branch=master)

# JSON Data Generator
Generator for random JSON strings to test your REST-Services!

This lib was created to help us run our load tests with lots of different, random data.
It creates JSON-Strings based on a JSON-Schema with random attribute values.

## Usage
Include the lib in your project with 
```
    implementation "com.github.rabend:json-data-generator:0.15"
```
```
    <dependency>
        <groupId>com.github.rabend</groupId>
        <artifactId>json-data-generator</artifactId>
        <version>0.15</version>
    </dependency>
```

Just use the class ```TestDataGenerator``` in your test case, 
instantiate it and call ```generateJsonString()``` with an URL to the schema you want to generate data for
as often as you need. It will return a JSON-String representation
of the object specified by the schema, with random attribute values.
Or you can just use one of the type related generators, if that's what you need.

## Notes
If you have a JSON-Schema that contains nodes which cannot be generated with this lib, 
feel free to create and issue or implement a solution and open a PR :)

### Features
* Generation of Integer, Double, Boolean, Date, Datetime, String and Object nodes.
* Generation of arrays of the above mentioned types
* Generation of enum backed arrays
* Generation of strings according to regular expression
