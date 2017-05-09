package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by amanzhol-ak on 09.05.2017.
 */
@Entity
@Table(name = "d_case_ending" )
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DCaseEnding.findAll", query = "SELECT g FROM DCaseEnding g"),
        @NamedQuery(name = "DCaseEnding.findById", query = "SELECT g FROM DCaseEnding g where g.id = :id")
})
public class DCaseEnding implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "parent_id")
    private int parentId;
    @Basic
    @Column(name = "value")
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DCaseEnding that = (DCaseEnding) o;

        if (id != that.id) return false;
        if (parentId != that.parentId) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + parentId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
