# Groovy XSLT DSL
XSLT is good DSL for transformation XML documents, but in my opinion, it is a little bit complex, because it is based on XML.
XML approach has several problems, such as:
* Verbosity
* Calling external functions is complex
* Debugging is not available

This is small example of usage Groovy to build easy to read XML transformations. Of course the DSL doesn't provide whole XSLT functionality,
but I beelive it is possible to make some extentions. It is a little simlified part of real project (real working in production code).
It is common problem when you need to call external code from transformation. In Java it seems like 
```
Java -> XML -> Java 
```
Groovy provides ability to extend you code dynamicaly. The example uses
* XmlSlurper to get GPathResult (of course it is possible to replace it with XmlParser)
* Groovy category to override some behaviour of GPathResult and to make code more brief and clear
* groovy.util.Proxy to override GPathResult Node behaviour,
in particular for a call of GroovyShell to dinamically compile code of inserting new node if it doesn't exist 
