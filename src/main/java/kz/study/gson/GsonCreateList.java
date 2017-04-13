package kz.study.gson;

import java.util.HashMap;
import java.util.List;

/**
 * @author kusein-at;
 * @since on 05.04.2017.
 */
public class GsonCreateList {

    private int mLength;//Общее число символов
    private int woutMLength;//Количество символов без пробелов
    private int wordCount;//Количество слов
    private int uniqueWordCount;//Количество уникальных слов
    private HashMap<String, Integer> repeatedWord;//Слова и колличество раз их упоминания:

    public int getmLength() {
        return mLength;
    }

    public void setmLength(int mLength) {
        this.mLength = mLength;
    }

    public int getWoutMLength() {
        return woutMLength;
    }

    public void setWoutMLength(int woutMLength) {
        this.woutMLength = woutMLength;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getUniqueWordCount() {
        return uniqueWordCount;
    }

    public void setUniqueWordCount(int uniqueWordCount) {
        this.uniqueWordCount = uniqueWordCount;
    }

    public HashMap<String, Integer> getRepeatedWord() {
        return repeatedWord;
    }

    public void setRepeatedWord(HashMap<String, Integer> repeatedWord) {
        this.repeatedWord = repeatedWord;
    }
}