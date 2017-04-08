package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by amanzhol-ak on 08.04.2017.
 */
@Entity
@Table(name = "test_type" )
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TestType.findAll", query = "SELECT g FROM TestType g"),
        @NamedQuery(name = "TestType.findById", query = "SELECT g FROM TestType g WHERE g.id = :id")
})
public class TestType {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public TestType() {
    }

    public TestType(String id) {
        this.id = id;
    }


}
