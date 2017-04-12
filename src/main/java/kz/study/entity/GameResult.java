package kz.study.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @since 12.04.2017
 * @author amanzhol-ak.
 */
@Entity
@Table(name = "GAME_RESULT")
public class GameResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "game_Id")
    private String gameId;
    @Column(name = "uName")
    private String uName;
    @Column(name = "result")
    private Long result;

    public GameResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}