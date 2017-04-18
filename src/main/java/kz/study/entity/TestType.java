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
        @NamedQuery(name = "TestType.findById", query = "SELECT g FROM TestType g WHERE g.id = :id"),
        @NamedQuery(name = "TestType.findByIsPublic", query = "SELECT g FROM TestType g WHERE g.isPublic = :isPublic")
})
public class TestType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "Level")
    private Integer level;
    @Basic
    @Column(name = "IS_PUBLIC")
    private Integer isPublic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public TestType(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
}
