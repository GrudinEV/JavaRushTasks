package com.javarush.task.task33.task3309;

import com.sun.xml.txw2.annotation.XmlCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;
import java.util.List;

@XmlType
@XmlRootElement
public class First {
    @XmlElement
    List<String> second = Arrays.asList("some string", "some string", "<![CDATA[need CDATA because of < and >]]>", "");
}
