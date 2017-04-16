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
@Table(name = "ALL_ENDING")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AllEnding.findAll", query = "SELECT g FROM AllEnding g"),
        @NamedQuery(name = "AllEnding.findById", query = "SELECT g FROM AllEnding g where g.id = :id")
})
public class AllEnding implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "value")
    private String value;
    @JoinColumn(name = "D_ENDING_ID", referencedColumnName = "ID")
    @OneToOne
    private DEnding dEnding;
    @JoinColumn(name = "D_CASE_ID", referencedColumnName = "ID")
    @OneToOne
    private DCase dCase;

    public AllEnding() {
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

    public DEnding getdEnding() {
        return dEnding;
    }

    public void setdEnding(DEnding dEnding) {
        this.dEnding = dEnding;
    }

    public DCase getdCase() {
        return dCase;
    }

    public void setdCase(DCase dCase) {
        this.dCase = dCase;
    }
}