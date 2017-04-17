package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @since 08.04.2017
 * @author amanzhol-ak.
 */
@Entity
@Table(name = "test_type" )
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TestType.findAll", query = "SELECT g FROM TestType g"),
        @NamedQuery(name = "TestType.findById", query = "SELECT g FROM TestType g WHERE g.id = :id")
})
public class TestType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "SOURCE_ID")
    private String sourceId;

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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public TestType() {
    }

    public TestType(String id) {
        this.id = id;
    }

}
