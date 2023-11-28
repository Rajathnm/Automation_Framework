Feature: Amazon web page

@Amazon
Scenario Outline: Validate amazon search
Given user launch amazon application
Then user validates below exact text on the screen at first occurance
|fieldName|
|Amazon miniTV|
|Sell|
|Gift Cards|
When user mouseover the language dropdown
When user enters text "<Text>" into searchbox and clicks on search
Then validates all the items fetched have same text "<Text>"
Examples:
|Text|
|Iphone|

