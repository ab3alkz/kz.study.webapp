package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by amanzhol-ak on 23.04.2017.
 */
@Entity
@Table(name = "d_test_type")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DTestType.findAll", query = "SELECT g FROM DTestType g order by g.ord asc")
})
public class DTestType implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "ord")
    private Integer ord;

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

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

    public DTestType(String id) {
        this.id = id;
    }

    public DTestType() {
    }
}
