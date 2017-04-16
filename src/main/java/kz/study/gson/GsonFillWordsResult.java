package kz.study.gson;

import java.util.List;

/**
 * Created by amanzhol-ak on 16.04.2017.
 */
public class GsonFillWordsResult {
    private Long total;
    private List<GsonFillWordsData> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<GsonFillWordsData> getData() {
        return data;
    }

    public void setData(List<GsonFillWordsData> data) {
        this.data = data;
    }
}
