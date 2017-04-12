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
@Table(name = "video_lessons")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VideoLessons.findAll", query = "SELECT g FROM VideoLessons g"),
        @NamedQuery(name = "VideoLessons.findById", query = "SELECT g FROM VideoLessons g where g.id = :id"),
})
public class VideoLessons implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "id")
    private int id;
    @Size(max = 1000)
    @Column(name = "value")
    private String value;
    @Column(name = "description")
    private String description;

    public VideoLessons() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}