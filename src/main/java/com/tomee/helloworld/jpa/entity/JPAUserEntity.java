package com.tomee.helloworld.jpa.entity;

import javax.persistence.*;

/**
 * Created by valerie on 1/11/16.
 */
@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name ="JPAUserEntity.findAll", query = "SELECT u FROM JPAUserEntity u")
})
public class JPAUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String state;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="greeting_id")
    private JPAGreetingEntity jpaGreetingEntity = new JPAGreetingEntity();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public JPAGreetingEntity getJpaGreetingEntity() {
        return jpaGreetingEntity;
    }

    public void setJpaGreetingEntity(JPAGreetingEntity jpaGreetingEntity) {
        this.jpaGreetingEntity = jpaGreetingEntity;
    }

    @Override
    public String toString() {
        return "JPAUserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
