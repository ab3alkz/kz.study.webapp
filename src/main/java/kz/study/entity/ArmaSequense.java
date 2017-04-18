package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by amanzhol-ak on 18.04.2017.
 */
@Entity
@Table(name = "arma_sequense")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ArmaSequense.findVal", query = "SELECT g FROM ArmaSequense g")
})
public class ArmaSequense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "val")
    private Integer val;

    public ArmaSequense() {
    }

    public ArmaSequense(Integer id, Integer val) {
        this.id = id;
        this.val = val;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
