
![jarcweave logo inverted](https://user-images.githubusercontent.com/6147299/124512214-75269980-dd9d-11eb-9567-ace9ae00a4bd.png)

**JArcweave** is an API built to read exported JSON files from the [Arcweave](arcweave.com) dialogue editing program. This allows the user to import projects from Arcweave and use it in their Java-based projects. Additionally, this API is designed to also act as a documented learning tool for those interested in making their own Arcweave importers for various other programming languages.

![Screenshot 2021-06-13 201144](https://user-images.githubusercontent.com/6147299/121827991-c56d7880-cc83-11eb-8c0a-a0b44ceedcd8.jpg)

## Features
- Arcweave JSON Project importing: load Arcweave files into Java as a "raw" format where all of the data is preserved as is
- Arcweave Project "processing": projects can be converted with a single line into a more Java-friendly format where all of the produced Java objects are referenced directly rather than by String ID
- Basic example included for using the API
- Fully documented
- Text document included which summarizes the Arcweave JSON format, along with tips on expanding JArcweave's functionality

## Example File
- [Hello World](https://github.com/SkyAphid/JArcweaveImporter/blob/master/src/test/com/nokoriware/arcweave/test/JArcweaveHelloWorldExample.java): a basic Hello World program that lets you test out exported JSON files

## Unsupported Functionality
Below are aspects of JArcweave that are not yet implemented. Unless noted otherwise, the functionality will eventually be added.

- Assets: This functionality will likely not be added since there is not much use for it in my own applications
- Variables
- Conditions
- Branches

## Disclaimer
JArcweave is a new API that is still in active development. There may be bugs or missing features for now.
