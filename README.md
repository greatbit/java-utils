java-utils
==========

[![Join the chat at https://gitter.im/azee/java-utils](https://badges.gitter.im/azee/java-utils.svg)](https://gitter.im/azee/java-utils?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Jenkins Build](http://azee.people.yandex.net/jenkins/buildStatus/icon?job=java-utils)](http://azee.people.yandex.net/jenkins/job/java-utils)

Useful utils lib for java

Build for Java 8
(For 1.7 use version 1.20)

Everyone is welcome to contribute

Maven
==========

```
<dependency>
    <groupId>ru.greatbit</groupId>
    <artifactId>java-utils</artifactId>
    <version>2.0</version>
</dependency>
```

Beans
==========
Beans deep copy:

```
SomeBean targetBean = BeanUtils.getCopy(sourceBean);
```


Compare beans by value:
```
boolean areEqual = BeanUtils.equalByVal(bean1, bean2)
```


Serialize
==========
Used to marshall and unmarshall objects annotated with JAXB annotations

Marshall to JSON:

```
String marshalledBean = JsonSerializer.marshal(bean);
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

Returns a comma separated string from the list without spaces:

```
StringUtils.listAsStringNoSpaces(objects);
```


Returns md5 hash from string:

```
StringUtils.getMd5String("Super secret string")
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

Remove tailing symbols:

```
StringUtils.removeTailing("Some, tailing, comma,", ",")
```

Remove heading symbols:

```
StringUtils.removeHeading(",Some, tailing, comma", ",")
```

Determine the longest common subsequence between the two strings:
```
StringUtils.lcs("Two beer or not two beer", "To bee or not to bee")
```

Transliterate russian characters string to english
```
String translited = StringUtils.translit(someTextWithRussianSymbols)
```

Collections
==========
Merge 2 lists, objects are compared by hashCode and equals:
```
List<String> result = CollectionUtils.mergeLists(first, second);
List<SomeObject> result2 = CollectionUtils.mergeLists(first, second);
```

Merge 2 lists of objects. Objects are compared by serialized into JSON value:
```
List<SomeObject> result = CollectionUtils.mergeListsByValue(first, second);
```

Find differences in 2 lists:
```
Difference<String> difference = CollectionUtils.getDiff(first, second);
Difference<BeanWithNamespaceExample> difference2 = CollectionUtils.getDiff(first, second);

...

difference.getAdded();
difference.getRemoved();
difference.getEqual();
```

Remove values from lists that don't support remove method
```
CollectionUtils.removeByIndex(Arrays.asList("A", "B", "C"), 2);
```

Find differences in 2 lists of objects in which we can't override hashCode() and equals():
```
Difference<BeanWithNamespaceExample> difference = CollectionUtils.getDiffAnyObject(first, second);

...

difference.getAdded();
difference.getRemoved();
difference.getEqual();
```

Create a map of objects from the list where the key is the same object.
If not primitive or String - hashCode() and equals() should be overridden in object class
```
List<BeanWithNamespaceExample> beansList = new LinkedList();

...

Map<BeanWithNamespaceExample, BeanWithNamespaceExample> newMap = CollectionUtils.listToMap(beansList);
```

Create a map of objects from the list where the key is the MD5 string of serialized object.
Use if hashCode() and equals() couldn't be overridden in object class
```
List<BeanWithNamespaceExample> beansList = new LinkedList();

...

Map<String, BeanWithNamespaceExample> newMap = CollectionUtils.listToMD5Map(beansList);
```

Remove duplicate objects from List
```
List<String> values = Arrays.asList("0", "1", "2", "0", "3", "1");
values = CollectionUtils.removeDuplicateValues(values);
```

Remove duplicate values from list using provided function to fetch field (or values) used to compare objects
```
List<String> values = Arrays.asList("a", "aa", "b", "bb", "bbb", "cccc", "dddd");
values = removeDuplicateValues(values, (str -> str.length()));
```

```
List<Pair<String, Object>> pairs = new LinkedList<>();
pairs.add(new Pair<>("1", new Object()));
pairs.add(new Pair<>("2", new Object()));
pairs.add(new Pair<>("1", new Object()));
pairs = removeDuplicateValues(pairs, Pair::getKey);
```

Remove duplicate objects from Map
```
Map<String, List<String>> values = new HashMap<String, List<String>>();
values.put("one", Arrays.asList("0", "1", "2", "0", "3", "1"));
values.put("two", Arrays.asList("0", "1", "2", "1", "2", "0"));
values = CollectionUtils.removeDuplicateValues(values);
```

Reorder a list of elements by another list. Trying to keep absolute order of initial list but reorder regarding to provided relative order list.
E.g. initial was [1, 2, 3, 4, 5] - calling reorder with list [2, 5, 4] will generate list [1, 2, 3, 5, 4]
Threshold can be used (default is 1000). Threshold defines when to use slow but accurate algorithm or fast one that could corrupt initial order if not all elements are present in new one.
Default threshold is 1000 for new order length. If threshold > order size then O(nm) algorithm will be used. Otherwise O(n + m) will be used.
Algorithms are available separately - O(n + m) mergeReorder method and O(nm) swapReorder. 
```
List<integer> newList = CollectionUtils.reorder(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(2, 5, 4));
List<integer> newList = CollectionUtils.reorder(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(2, 5, 4), 10000);
List<integer> newList = CollectionUtils.swapReorder(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(2, 5, 4));
List<integer> newList = CollectionUtils.mergeReorder(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(2, 5, 4));
```

Swap elements in list by their indexes
Will swap 3 and 5
```
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
CollectionUtils.swap(list, 2, 4);
```


Time
==========
Get long - time of the beginning of the day for provided time

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

BFS and DFS traversal processing using visitor pattern:
Visitor visitor - is a visitor interface needed to be implemented

```
Traverse.bfs((Node)head, visitor);
Traverse.dfs((Node)head, visitor);
```

Count number of leafs

```
long leafsCount = countLeafs((Node)head)
```

Count maximum height

```
int height = countMaxHeight((Node)head)
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


Find out if the number is prime:

```
Prime.isPrime(157)
```


Get list of simple primes:

```
Prime.getPrimes(20)
```


Get list of simple primes in a range:

```
Prime.getPrimes(5, 20)
```


Reflection
==========
Get value of the field by name
```
(String) FieldsFetcher.getObjectFromField(parent, parent.getClass(), "str");
(Integer) FieldsFetcher.getObjectFromField(parent, parent.getClass(), "count");
```

Find value of the field by path provided as list of strings
```
(String) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.childPrivate.str".split("\\.")))
```

Get values from object fields by super class and put them into single list.
Used if we need to collect all objects of the same interface into a single list.
```
List<SuperClass> result = FieldsFetcher.getValuesByInterface(container, SuperClass.class);
```

Get values from object generic fields represented as list by super class and put them into single list
Used if we need to collect all objects of the same interface into a single list.
```
List<SuperClass> result = FieldsFetcher.mergeListsByInterface(container, SuperClass.class);
```

Find value of the field by point delimited path
```
(String) FieldsFetcher.findValue(parent, "childPublic.childPrivate.str")
```

Random
==========

Returns random within a range (inclusive)
```
int rand = Random.next(1, 10);
```

Network
==========

Returns InetAddress most suitable for current sever
```
InetAddress address = InetAddress getLocalHostLANAddress()
```


Returns localhost InetAddres
```
InetAddress address = InetAddress getLocalHost()
```


New in 2.1-SNAPSHOT
==========

Collection utils
==========

Get a power set - all invariants of items in collections
Will return: [], [a], [b], [c], [a, b], [a, c], [b, c], [a, b, c]
```
List<String> input = Arrays.asList("a", "b", "c")
<T> Set<Set<T>> = CollectionUtils.powerSet(input);
```

Get all possible permutated invariants of list
Will return: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]
```
List<Set<String>> result = CollectionUtils.permutations(Arrays.asList("1", "2", "3"));
```

License
==========

No limitations of usage
