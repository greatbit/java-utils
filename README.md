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
    <version>1.4</version>
</dependency>
```

Usage
==========
Include into your spring-context:

```
<context:component-scan base-package="ru.greatbit.utils"/>
```

Beans
==========
Beans deep copy:

```
@Autowired
CopyBeanUtils copyBeanUtils;

....

SomeBean targetBean = copyBeanUtils.getCopy(sourceBean);
```


Serialize
==========
Used to marshall and unmarshall objects annotated with JAXB annotations

Marshall to JSON:

```
@Autowired
JsonMarshaller jsonMarshaller;

...

String marshalledBean = jsonMarshaller.marshal(bean);

```

Marshall from JSON:

```
@Autowired
JsonUnmarshaller jsonUnmarshaller;

...

SomeBean bean = jsonUnmarshaller.unmarshal(beanWithOutRoot, SomeBean.class);
SomeBean bean2 = jsonUnmarshaller.unmarshal(beanWithRoot, "rootElement",  BeanWithoutNamespaceExample.class);
```

Marshal to XML (with or without namespaces):

```
@Autowired
XMLMarshaller xmlMarshaller;

...

String xmlValue = xmlMarshaller.marshal(bean);
```

Unmarshal from XML (with or without namespaces):

```
@Autowired
XMLUnmarshaller xmlUnmarshaller;

...

SomeBean bean = xmlUnmarshaller.unmarshal(marshalledBeanString, SomeBean.class);
```

String
==========
Returns an empty string if null is passed:

```
@Autowired
StringUtils stringUtils;

...

stringUtils.emptyIfNull(someString)
```


Returns a comma separated string from the list:

```
@Autowired
StringUtils stringUtils;

...

stringUtils.listAsString(strings);
```



Returns md5 hash from string:

```
@Autowired
StringUtils stringUtils;

...

stringUtils.getMd5String("Super secret string")
```


Find out if a string is in string list:

```
@Autowired
StringUtils stringUtils;

...

stringUtils.isStringInList(strings, "hide");
```

Time
==========
Get long - time of the beginning of the day of porvided time

```
@Autowired
TimeUtils timeUtils;

...

timeUtils.getStartOfTheDay(new Date().getTime());
```

Cron
==========
Convert Unix cron expression to Quartz

```
@Autowired
CronUtils cronUtils;

...

cronUtils.convertToQuartz("0 10 * * 1-4");
```

Jenkins Build
==========
http://azee.people.yandex.net/job/java-utils/
