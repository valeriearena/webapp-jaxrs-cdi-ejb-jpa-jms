package com.tomee.helloworld.jpa.entity;

import javax.persistence.*;

/**
 * Created by valerie on 1/10/16.
 */
@Entity
@Table(name="greeting")
@NamedQueries({
        @NamedQuery(name ="Greeting.findAll", query = "SELECT g FROM JPAGreetingEntity g")
})
public class JPAGreetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String expression;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
