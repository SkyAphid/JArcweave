
![jarcweave logo inverted](https://user-images.githubusercontent.com/6147299/124512214-75269980-dd9d-11eb-9567-ace9ae00a4bd.png)

**JArcweave** is an API built to read exported JSON files from the [Arcweave](arcweave.com) dialogue editing program. This allows the user to import projects from Arcweave and use it in their Java-based projects. Additionally, this API is designed to also act as a fully documented learning tool for those interested in making their own Arcweave importers for various other programming languages.

![Screenshot 2021-06-13 201144](https://user-images.githubusercontent.com/6147299/121827991-c56d7880-cc83-11eb-8c0a-a0b44ceedcd8.jpg)

## Features
- Arcweave JSON Project importing: load Arcweave files into Java as a "raw" format where all of the data is preserved as is
- Arcweave Project "processing": projects can be converted with a single line into a more object-based java-friendly format where all objects are referenced by references rather than string IDs.
- A basic example program included for testing and learning how to use the API
- All classes in the project are fully documented
- Text document included which summarizes the Arcweave JSON format, along with tips on expanding JArcweave's functionality

## Example File
- [Hello World](https://github.com/SkyAphid/JArcweaveImporter/blob/master/src/test/com/nokoriware/arcweave/test/JArcweaveHelloWorldExample.java): a basic Hello World program that lets you test out exported JSON files

## Unsupported Functionality
Below is functionality from Arcweave that is omitted by this parser. This is due to those features being either unapplicable or due to the fact it would take a great deal of time to implemented and maintain.

- Assets
- Variables
- Conditions
- Branches

## Disclaimer
JArcweave is a new API that is still in active development. There may be bugs or missing features for now.
