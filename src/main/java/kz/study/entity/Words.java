package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by amanzhol-ak on 08.04.2017.
 */
@Entity
@Table(name = "WORDS")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Words.findAll", query = "SELECT g FROM Words g"),
        @NamedQuery(name = "Words.findById", query = "SELECT g FROM Words g WHERE g.id = :id")
})
public class Words implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "value_kz")
    private String valueKz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValueKz() {
        return valueKz;
    }

    public void setValueKz(String valueKz) {
        this.valueKz = valueKz;
    }

    public Words(String id) {
        this.id = id;
    }
    public Words(String id,String valueKz) {
        this.id = id;
        this.valueKz = valueKz;

    }

    public Words() {
    }
}
