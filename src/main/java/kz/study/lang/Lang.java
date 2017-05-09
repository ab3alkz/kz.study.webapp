package kz.study.lang;

import org.jetbrains.annotations.Contract;

/**
 * @author Владелец
 */
public enum Lang {

    Kz("Kz"), Ru("Ru"), Ln("Ln");

    private String desc;

    Lang(String desc) {
        this.desc = desc;
    }

    @Contract(pure = true)
    @Override
    public String toString() {
        return desc;
    }
}