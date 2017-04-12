package kz.study.util;

import java.util.*;

/**
 * @author aslan.
 * @since 4/12/17
 */
public class Analizator {

    private int mLength;//Общее число символов
    private int woutMLength;//Количество символов без пробелов
    private int wordCount;//Количество слов
    private int uniqueWordCount;//Количество уникальных слов

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

    public void getTest(String s) {
        String withoutSpace;

        List<String> stringList = new ArrayList<>();

        setmLength(s.length());
        withoutSpace = s.replace(" ", "");

        setWoutMLength(withoutSpace.trim().length());

        setUniqueWordCount(0);

        System.out.println("Общее число символов: " + mLength);
        System.out.println("Количество символов без пробелов: " + woutMLength);
        System.out.println("Количество слов: " + wordCount);
    }

    private static void uniqueWordMethod(String s) {
        List<String> words = new ArrayList<>();
        words.addAll(Arrays.asList(s.split(" ")));

        for ( String w: words)
            System.out.print(w +",");

        // Пробую скопировать имеющийся Лист
        List<String> list = new ArrayList<String>(words);


        // Так как фамилии надо посчитать, то проще использовать HashMap
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        // Инициализирую счетчик
        Integer item;
        // прогоняю по циклу ArrayList и
        // закидываю фамилии в HashMap как ключ, а значение(Value) создаю или увеличиваю на 1
        for (String wrd : list) {

            //System.out.println("++++++"+hm.get(wrd));
            item = hm.get(wrd);
            if (item == null) hm.put(wrd, 1); // если нет в списке то добавить со значением 1
            else hm.put(wrd, item + 1); // если есть такая фамилия(Key), то +1
        }

        // Вывод результатов
        System.out.println(" ");
        System.out.println("Количество слов: " + words.size());
        System.out.println("Количество уникальных слов: " + hm.size());
        System.out.println("Слова и колличество раз их упоминания:");
        System.out.println(hm);

        System.out.println(".........................................................");
        System.out.println(hm.values());
    }

    public static void main(String[] args) {
        Analizator analizator = new Analizator();
        String rx = "Аслан крикнул Аслан";
        analizator.getTest(rx);

        uniqueWordMethod(rx);
    }

}
