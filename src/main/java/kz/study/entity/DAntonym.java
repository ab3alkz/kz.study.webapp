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
@Table(name = "D_ANTONYM")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DAntonym.findAll", query = "SELECT g FROM DAntonym g"),
        @NamedQuery(name = "DAntonym.findById", query = "SELECT g FROM DAntonym g where g.id = :id")
})
public class DAntonym implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "SECVALUE")
    private String secValue;

    public DAntonym() {
    }

    public DAntonym(String id, String value, String secValue) {
        this.id = id;
        this.value = value;
        this.secValue = secValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSecValue() {
        return secValue;
    }

    public void setSecValue(String secValue) {
        this.secValue = secValue;
    }
}