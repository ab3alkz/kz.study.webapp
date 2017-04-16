package kz.study.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "D_CASE")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DCase.findAll", query = "SELECT g FROM DCase g"),
        @NamedQuery(name = "DCase.findById", query = "SELECT g FROM DCase g where g.id = :id")
})
public class DCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "NAME")
    private String value;
    @Column(name = "QS")
    private String question;
    @Column(name = "c_disc")
    private String descrip;

    public DCase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}