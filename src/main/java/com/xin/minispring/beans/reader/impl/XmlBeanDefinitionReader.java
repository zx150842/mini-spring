package com.xin.minispring.beans.reader.impl;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.xin.minispring.beans.BeanDefinition;
import com.xin.minispring.beans.BeanReference;
import com.xin.minispring.beans.io.Resources;
import com.xin.minispring.beans.reader.AbstractBeanDefinitionReader;

/**
 * @author zx150842@126.com
 *
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

  @Override
  public void loadBeanDefinitions(String location) throws Exception {
    InputStream in = Resources.getResourceAsStream(location);
    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(in);
      Element element = document.getDocumentElement();
      loadBeanDefinitions(element);
    } finally {
      in.close();
    }
  }
  
  private void loadBeanDefinitions(Element root) {
    NodeList nodeList = root.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); ++i) {
      Node node = nodeList.item(i);
      if (node instanceof Element) {
        loadBeanDefinition((Element)node);
      }
    }
  }
  
  private void loadBeanDefinition(Element element) {
    String tagName = element.getTagName();
    if (tagName.equals("bean")) {
      String beanName = element.getAttribute("id");
      String className = element.getAttribute("class");
      BeanDefinition beanDefinition = new BeanDefinition();
      beanDefinition.setClassName(className);
      loadBeanProperties(element, beanDefinition);
      getRegistry().put(beanName, beanDefinition);
    }
  }
  
  private void loadBeanProperties(Element root, BeanDefinition beanDefinition) {
    NodeList nodeList = root.getElementsByTagName("property");
    for (int i = 0; i < nodeList.getLength(); ++i) {
      Node node = nodeList.item(i);
      if (node instanceof Element) {
        Element element = (Element)node;
        String name = element.getAttribute("name");
        String value = element.getAttribute("value");
        if (value != null && value.length() > 0) {
          beanDefinition.getProperties().put(name, value);
        } else {
          String ref = element.getAttribute("ref");
          if (ref != null && ref.length() > 0) {
            beanDefinition.getProperties().put(name, new BeanReference(ref));
          } else {
            throw new IllegalArgumentException("Bean property has no value and no ref");
          }
        }
      }
    }
  }
}
