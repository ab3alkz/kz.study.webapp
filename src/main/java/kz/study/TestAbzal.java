package kz.study;

import java.util.ArrayList;
import java.util.List;

import static kz.study.util.Util.createGuid;

/**
 * Created by amanzhol-ak on 08.04.2017.
 */
public class TestAbzal {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("Сәлеметсізбе");
//        list.add("Қазақстан");
//        list.add("Әке");
//        list.add("Әже");
//        list.add("Ана");
//        list.add("Ата");
//        list.add("Қазақ");
//        list.add("Мысық");
//        list.add("Балапан");
//        list.add("Қыс");
//        list.add("Көктем");
//        list.add("Жаз");
//        list.add("Күз");
        for (String word: list) {
            System.out.println(getSql(word));
        }
    }

    private static String getSql(String word) {
        return "insert into words (id,value_kz) values('"+createGuid()+"','"+word+"');";
    }
}
