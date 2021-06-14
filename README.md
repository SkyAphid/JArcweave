# JArcweave Importer
JArcweave is an API built to help with importing exported JSON files from the [Arcweave](arcweave.com) dialogue editing program. Additionally, it was built and documented to act as a learning tool for those interested in making their own importers for various programming languages.

![Screenshot 2021-06-13 201144](https://user-images.githubusercontent.com/6147299/121827991-c56d7880-cc83-11eb-8c0a-a0b44ceedcd8.jpg)

## Features
- Arcweave JSON Project importing: load Arcweave files into Java as a "raw" format where all of the data is preserved as is
- Arcweave Project "processing": projects can be converted with a single line into a more Java-friendly format where all of the produced Java objects are referenced directly rather than by String ID.
- Basic examples included for using the API
- Fully documented
- Text document included which summarizes the Arcweave JSON format, along with tips on expanding JArcweave's functionality

## Example File
- [JArcweave Hello World](https://github.com/SkyAphid/JArcweaveImporter/blob/master/src/test/com/nokoriware/arcweave/test/JArcweaveHelloWorldExample.java): a basic Hello World program that lets you test out exported JSON files

## Disclaimer
JArcweave is a new API that is still in active development. There may be bugs or missing features for now.

## Unsupported Functionality
Below are aspects of JArcweave that are not yet implemented. Unless noted otherwise, the functionality will eventually be added.

- Assets: This functionality will likely not be added since there is not much use for it in my own applications.
- Variables
- Conditions
- Branches
