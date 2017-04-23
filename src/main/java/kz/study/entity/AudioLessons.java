/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author kusein-at;
 * @since on 17.11.2016.
 */
@Entity
@Table(name = "LESSON_AUDI")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AudioLessons.findAll", query = "SELECT g FROM AudioLessons g"),
        @NamedQuery(name = "AudioLessons.findByParamId", query = "SELECT g FROM AudioLessons g where g.paramId = :paramId"),
})
public class AudioLessons implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    private String value;
    @Column(name = "paramId")
    private int paramId;

    public AudioLessons() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }
}