package com.example.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScopesIntegrationTest {
    private static final String NAME = "Will Smith";
    private static final String NAME_OTHER = "Another Smith";

    @Test
    public void givenSingletonScope_whenSetName_thenEqualNames() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("scopes.xml");

        Person personSingletonA = (Person) applicationContext.getBean("personSingletonBeanId"); // getting the object using beanId
        Person personSingletonB = (Person) applicationContext.getBean("personSingletonBeanId"); /* getting the object using beanId but will be
                                                                                                         SAME as personSingletonA cuz this is singleton scope */
        personSingletonA.setName(NAME);
        assertEquals(NAME, personSingletonB.getName()); // checks to see if both objects is the same

        ((AbstractApplicationContext) applicationContext).close();
    }

    @Test
    public void givenPrototypeScope_whenSetNames_thenDifferentNames() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("scopes.xml");

        Person personPrototypeA = (Person) applicationContext.getBean("personPrototypeBeanId"); // getting the object using beanId
        Person personPrototypeB = (Person) applicationContext.getBean("personPrototypeBeanId"); /* getting the object using beanId but will be
                                                                                                       DIFFERENT from personPrototypeA cuz this is prototype scope */

        personPrototypeA.setName(NAME);
        personPrototypeB.setName(NAME_OTHER);

        assertEquals(NAME, personPrototypeA.getName()); // checks to see if the personPrototypeA object hasName from NAME
        assertEquals(NAME_OTHER, personPrototypeB.getName()); /* checks to see if the personPrototypeB object hasName
                                                                 from NAME_OTHER and is not the same as personPrototypeA or NAME */

        ((AbstractApplicationContext) applicationContext).close();
    }
}




