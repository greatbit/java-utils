java-utils
==========

Useful utils lib for java

Project just started. Everyone is welcome to contribute

Maven
==========

```
<dependency>
    <groupId>ru.greatbit</groupId>
    <artifactId>java-utils</artifactId>
    <version>1.6</version>
</dependency>
```

Beans
==========
Beans deep copy:

```
SomeBean targetBean = CopyBeanUtils.getCopy(sourceBean);
```


Compare beans by value:
```
boolean areEqual = CompareBeanUtils.equalByVal(bean1, bean2)
```


Serialize
==========
Used to marshall and unmarshall objects annotated with JAXB annotations

Marshall to JSON:

```
String marshalledBean = JsonMarshaller.marshal(bean);
```

Marshall from JSON:

```
SomeBean bean = JsonSerializer.unmarshal(beanWithOutRoot, SomeBean.class);
SomeBean bean2 = JsonSerializer.unmarshal(beanWithRoot, "rootElement",  BeanWithoutNamespaceExample.class);
```

Marshal to XML (with or without namespaces):

```
String xmlValue = XmlSerializer.marshal(bean);
```

Unmarshal from XML (with or without namespaces):

```
SomeBean bean = XmlSerializer.unmarshal(marshalledBeanString, SomeBean.class);
```


Unmarshal an oject if we don't now if it is a json or an xml:

```
SomeBean bean = Serializer.unmarshal(marshalledBeanString, SomeBean.class);
```

String
==========
Returns an empty string if null is passed:

```
StringUtils.emptyIfNull(someString)
```


Returns a comma separated string from the list:

```
StringUtils.listAsString(strings);
```


Returns md5 hash from string:

```
StringUtils.getMd5String("Super secret string")
```


Find out if a string is in string list:

```
StringUtils.isStringInList(strings, "hide");
```


Add the string to string list only if it is unique

```
StringUtils.addUniqueString("I'm Unique", strings);
```


Find out if all of provided string parts are present in the source string:

```
boolean containsAll = StringUtils.containsAll(source, "javascript", "How", "need")
```


Find out if provided souce string contains any of string parts from the list:

```
boolean containsAny = StringUtils.containsAny(source, "javascript", "How", "need")
```

List
==========
Merge 2 lists:
```
List<String> result = ListUtils.mergeLists(first, second);
List<SomeObject> result2 = ListUtils.mergeLists(first, second);
```

Find differences in 2 lists:
```
Difference<String> difference = ListUtils.getDiff(first, second);
Difference<BeanWithNamespaceExample> difference2 = ListUtils.getDiff(first, second);

...

difference.getAdded();
difference.getRemoved();
difference.getEqual();
```

Time
==========
Get long - time of the beginning of the day of porvided time

```
TimeUtils.getStartOfTheDay(new Date().getTime());
```

Cron
==========
Convert Unix cron expression to Quartz

```
CronUtils.convertToQuartz("0 10 * * 1-4");
```

Trees
==========
Collect nodes in order using Breadth-first traversal:

```
List<Node<String, String>> bfsList = Traverse.bfsList((Node)head);
```


Collect nodes in order using Depth-first traversal:

```
List<Node<String, String>> dfsList = Traverse.dfsList((Node)head);
```


Get all leafs:

```
List<Node<String, String>> leafs = Traverse.getlLeafs((Node)head);
```


New in 1.7-SNAPSHOT
==========

String
==========
Remove tailing symbols:

```
StringUtils.removeTailing("Some, tailing, comma,", ",")
```

Remove heading symbols:

```
StringUtils.removeHeading(",Some, tailing, comma", ",")
```

Math
==========
Get factorial of defined depth:

```
Sequence.factorial(5)
```


Get a sequence of factorial numbers of defined depth:

```
Sequence.factorialSequence(5)
```


Get fibonacchi number of defined depth:

```
Sequence.fibonacci(10)
```

Get a sequence of fibonacci numbers of defined depth:

```
Sequence.fibonacciSequence(5)
```


Jenkins Build
==========
http://azee.people.yandex.net/job/java-utils/

