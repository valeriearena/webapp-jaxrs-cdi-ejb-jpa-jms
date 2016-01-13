package com.tomee.helloworld.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="helloworld")
public class JAXBHelloWorld {

    private Long salutationId;
    private Long technologyId;
    private String salutation;
    private String technology;
    private String description;

    public Long getSalutationId() {
        return salutationId;
    }

    public void setSalutationId(Long salutationId) {
        this.salutationId = salutationId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JAXBHelloWorld{" +
                "salutationId=" + salutationId +
                ", technologyId=" + technologyId +
                ", salutation='" + salutation + '\'' +
                ", technology='" + technology + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}