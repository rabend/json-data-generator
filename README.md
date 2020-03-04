![Java CI](https://github.com/rabend/json-data-generator/workflows/Java%20CI/badge.svg?branch=master)

# JSON Data Generator
Generator for random JSON strings to test your REST-Services!

## The 'what'
This lib was created to help us run our load tests with lots of different, random data.
It creates JSON-Strings based on a JSON-Schema with random attribute values.

## The 'how-to'
Clone the repository and run `mvn clean install`. Then include the lib in your project with 
```
    <dependency>
        <groupId>com.github.rabend</groupId>
        <artifactId>json-data-generator</artifactId>
        <version>0.14</version>
    </dependency>
```

Just use the class ```TestDataGenerator``` in your testcase, 
instantiate it with an URL to the schema you want to generate data for 
and call ```generateJsonString()``` as often as you need. It will return a JSON-String representation
of the object specified by the schema, with random attribute values.
Or you can just use one of the type related generators, if that's what you need.

## Notes
If you have a JSON-Schema that contains nodes which cannot be generated with this lib, 
feel free to implement a solution and open a PR :)

### Features
* Generation of Integer, Double, Boolean, Date, Datetime, String and Object nodes.
* Generation of arrays of the above mentioned types
* Generation of enum backed arrays
* Generation of strings according to regular expression
