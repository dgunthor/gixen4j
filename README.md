# gixen4j [![Build Status](https://travis-ci.org/dgunthor/gixen4j.svg?branch=master)](https://travis-ci.org/dgunthor/gixen4j)
Java API for gixen.com

Add, delete, list your eBay snipes using a simple Java API.
Communication is GET requests over HTTPS.

## Usage

```java
//ensure you have signed up for the paid gixen.com service (currently $6 USD/year)
//create API instance with your eBay username/password
GixenApi gixen = new GixenApi("ebayUsername", "password");

//add a snipe for eBay item 1234567890, max bid 2.00
GixenResponse response = gixen.addSnipe("1234567890", 2.00);

//get current snipes
response = gixen.getSnipesMain();

//delete a snipe
response = gixen.deleteSnipe("1234567890");
```


