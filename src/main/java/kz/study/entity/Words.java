package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author amanzhol-ak.
 * @since 08.04.2017
 */
@Entity
@Table(name = "WORDS")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Words.findAll", query = "SELECT g FROM Words g"),
        @NamedQuery(name = "Words.findById", query = "SELECT g FROM Words g WHERE g.id = :id"),
        @NamedQuery(name = "Words.findByIdTest", query = "SELECT g FROM Words g WHERE g.idTest = :idTest")
})
public class Words implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "value_kz")
    private String valueKz;
    @Basic
    @Column(name = "id_test")
    private Integer idTest;

    public Words(Integer id) {
        this.id = id;
    }

    public Words() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValueKz() {
        return valueKz;
    }

    public void setValueKz(String valueKz) {
        this.valueKz = valueKz;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }
}
