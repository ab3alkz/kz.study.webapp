package kz.study.gson;

/**
 * @author kussein-at
 * @since 06.06.2017.
 */
public class TestClass<E> {

    private E id;
    private String value;
    private String secValue;
    private int adId;

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

    public String getSecValue() {
        return secValue;
    }

    public void setSecValue(String secValue) {
        this.secValue = secValue;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }
}