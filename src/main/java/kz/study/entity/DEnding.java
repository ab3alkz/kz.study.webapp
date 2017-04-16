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
@Table(name = "D_ENDING")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DEnding.findAll", query = "SELECT g FROM DEnding g"),
        @NamedQuery(name = "DEnding.findById", query = "SELECT g FROM DEnding g where g.id = :id")
})
public class DEnding implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "NAME")
    private String value;

    public DEnding() {
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
