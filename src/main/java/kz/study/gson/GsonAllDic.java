package kz.study.gson;

/**
 * @author kusein-at;
 * @since on 05.04.2017.
 */
public class GsonAllDic<E> {

    private E id;
    private String value;
    private String addValue;

    public E getId() {
        return id;
    }

    public void setId(E id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAddValue() {
        return addValue;
    }

    public void setAddValue(String addValue) {
        this.addValue = addValue;
    }
}
