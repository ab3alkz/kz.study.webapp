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
@Table(name = "ALL_SUFFIXES")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AllSuffixes.findAll", query = "SELECT g FROM AllSuffixes g"),
        @NamedQuery(name = "AllSuffixes.findById", query = "SELECT g FROM AllSuffixes g where g.id = :id")
})
public class AllSuffixes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "value")
    private String value;
    @JoinColumn(name = "D_SUFFIX_ID", referencedColumnName = "ID")
    @OneToOne
    private DSuffix dSuffix;

    public AllSuffixes() {
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

    public DSuffix getdSuffix() {
        return dSuffix;
    }

    public void setdSuffix(DSuffix dSuffix) {
        this.dSuffix = dSuffix;
    }
}