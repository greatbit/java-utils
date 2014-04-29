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
    <version>1.5</version>
</dependency>
```

Beans
==========
Beans deep copy:

```
SomeBean targetBean = CopyBeanUtils.getCopy(sourceBean);
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
SomeBean bean = JsonUnmarshaller.unmarshal(beanWithOutRoot, SomeBean.class);
SomeBean bean2 = JsonUnmarshaller.unmarshal(beanWithRoot, "rootElement",  BeanWithoutNamespaceExample.class);
```

Marshal to XML (with or without namespaces):

```
String xmlValue = XMLMarshaller.marshal(bean);
```

Unmarshal from XML (with or without namespaces):

```
SomeBean bean = XMLUnmarshaller.unmarshal(marshalledBeanString, SomeBean.class);
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

New in 1.5-SNAPSHOT
==========

List utils

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

Jenkins Build
==========
http://azee.people.yandex.net/job/java-utils/

