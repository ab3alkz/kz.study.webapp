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
@Table(name = "D_SUFFIX")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DSuffix.findAll", query = "SELECT g FROM DSuffix g"),
        @NamedQuery(name = "DSuffix.findById", query = "SELECT g FROM DSuffix g where g.id = :id")
})
public class DSuffix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;
    @Size(max = 1000)
    @Column(name = "TYPE")
    private String value;

    public DSuffix() {
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
}