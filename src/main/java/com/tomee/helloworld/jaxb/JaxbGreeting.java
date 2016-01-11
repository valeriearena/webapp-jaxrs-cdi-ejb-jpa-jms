package com.tomee.helloworld.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="greeting")
public class JAXBGreeting{

    private Long greetingId;
    private Long userId;
    private String expression;
    private String name;
    private String city;
    private String state;

    public Long getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(Long greetingId) {
        this.greetingId = greetingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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


}