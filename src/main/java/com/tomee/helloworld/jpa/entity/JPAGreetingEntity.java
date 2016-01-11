package com.tomee.helloworld.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by valerie on 1/10/16.
 */
@Entity
@Table(name="greeting")
@NamedQueries({
        @NamedQuery(name ="Greeting.findAll", query = "SELECT g FROM JPAGreetingEntity g")
})
public class JPAGreetingEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String expression;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "JPAGreetingEntity{" +
                "id=" + id +
                ", expression='" + expression + '\'' +
                '}';
    }
}
