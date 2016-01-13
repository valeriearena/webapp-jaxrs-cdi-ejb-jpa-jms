package com.tomee.helloworld.jpa.entity;

import javax.persistence.*;

/**
 * Created by valerie on 1/11/16.
 */
@Entity
@Table(name="technology")
@NamedQueries({
        @NamedQuery(name ="JPATechnologyEntity.findAll", query = "SELECT t FROM JPATechnologyEntity t")
})
public class JPATechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String technology;

    @Column
    private String description;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="salutation_id")
    private JPASalutationEntity jpaSalutationEntity = new JPASalutationEntity();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technologyName) {
        this.technology = technologyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String technologyDescription) {
        this.description = technologyDescription;
    }

    public JPASalutationEntity getJpaSalutationEntity() {
        return jpaSalutationEntity;
    }

    public void setJpaSalutationEntity(JPASalutationEntity jpaSalutationEntity) {
        this.jpaSalutationEntity = jpaSalutationEntity;
    }

    @Override
    public String toString() {
        return "JPATechnologyEntity{" +
                "id=" + id +
                ", technologyName='" + technology + '\'' +
                ", technologyDescription='" + description + '\'' +
                ", jpaSalutationEntity=" + jpaSalutationEntity +
                '}';
    }
}
