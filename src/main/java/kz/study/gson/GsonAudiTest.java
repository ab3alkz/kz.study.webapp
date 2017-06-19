package kz.study.gson;

import java.util.List;

/**
 * Created by amanzhol-ak on 15.05.2017.
 */
public class GsonAudiTest {

    private String id;
    private String frame;
    private String answ;
    private String text;
    private Integer srcId;
    private GsonResult result;
    private List<GsonAudiTest> data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public List<GsonAudiTest> getData() {
        return data;
    }

    public void setData(List<GsonAudiTest> data) {
        this.data = data;
    }

    public String getAnsw() {
        return answ;
    }

    public void setAnsw(String answ) {
        this.answ = answ;
    }

    public GsonResult getResult() {
        return result;
    }

    public void setResult(GsonResult result) {
        this.result = result;
    }
}
