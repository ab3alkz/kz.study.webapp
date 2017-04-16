package kz.study.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author amanzhol-ak.
 * @since 12.04.2017
 */
@Entity
@Table(name = "GAME_RESULT")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "GameResult.findAll", query = "SELECT g FROM GameResult g order by g.result desc,g.gDate asc"),
        @NamedQuery(name = "GameResult.findById", query = "SELECT g FROM GameResult g WHERE g.id = :id")
})
public class GameResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @JoinColumn(name = "game_Id", referencedColumnName = "ID")
    private TestType gameId;
    @JoinColumn(name = "uName", referencedColumnName = "u_Name")
    private Users uName;
    @Column(name = "info")
    private String info;
    @Column(name = "result")
    private Long result;
    @Column(name = "d_date")
    private Date gDate;

    public GameResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestType getGameId() {
        return gameId;
    }

    public void setGameId(TestType gameId) {
        this.gameId = gameId;
    }

    public Users getuName() {
        return uName;
    }

    public void setuName(Users uName) {
        this.uName = uName;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getgDate() {
        return gDate;
    }

    public void setgDate(Date gDate) {
        this.gDate = gDate;
    }
}
