#My first Gauge

This is an executable specification file. This file follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

To execute this specification, run
	gauge specs


* Open the web browser Chrome.

##Search a single word

tags: single word

* The word "gauge" has results in "https:\\google.com".


##Search multiple words

*Almost all words have results in "https:\\google.com"
     |Word     |Result     |
     |---------|-----------|
     |Gauge    |ok         |
     |shgfvzcxg|no         |
     |java     |ok         |
     |spec     |ok         |

___
 *Close the web browser
