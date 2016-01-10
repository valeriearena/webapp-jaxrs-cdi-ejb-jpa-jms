package com.tomee.helloworld.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="greeting")
public class JAXBGreeting {
    private String expression;
    private String name;
    private String city;
    private String state;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "greeting{" +
                "expression='" + expression + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}