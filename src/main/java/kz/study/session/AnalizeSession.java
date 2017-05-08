package kz.study.session;

import kz.study.gson.GsonCreateList;
import kz.study.gson.GsonResult;
import kz.study.util.Utx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static kz.study.util.Util.getGsonResult;
import static kz.study.util.Util.isNullOrEmpty;

/**
 * @author beljerin
 */
@Stateless
public class AnalizeSession extends Utx {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalizeSession.class);

    public GsonResult getAnalize(int id, String text) {
        if (id != 0) {
            switch (id) {
                case 1:
                    return getSymanticAnalyze(text);
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }
        return null;
    }

    private GsonResult getSymanticAnalyze(String text) {
        GsonCreateList gsonCreateList = new GsonCreateList();
        try {
            if (!isNullOrEmpty(text)) {
                List<String> words = new ArrayList<>();
                text = text.toLowerCase();
                words.addAll(Arrays.asList(text.split(" ")));

                List<String> list = new ArrayList<>(words);

                HashMap<String, Integer> hm = new HashMap<>();

                Integer item;
                for (String wrd : list) {
                    item = hm.get(wrd);
                    if (item == null) {
                        hm.put(wrd, 1);
                    } else {
                        hm.put(wrd, item + 1);
                    }
                }
                gsonCreateList.setWordCount(words.size());//Количество слов
                gsonCreateList.setUniqueWordCount(hm.size());//Количество уникальных слов
                gsonCreateList.setmLength(text.length());//Общее число символов
                gsonCreateList.setWoutMLength(text.replace(" ", "").length());//Количество символов без пробелов
                gsonCreateList.setRepeatedWord(hm);

                return getGsonResult(Boolean.TRUE, gsonCreateList);
            }
        } catch (Exception e) {
            LOGGER.error("error" + text, e);
        }
        return getGsonResult(Boolean.FALSE, null);
    }

    private GsonResult getMorphologyAnalize(String text) {
        if (!isNullOrEmpty(text)) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public GsonResult getTranslate(String text) {
        if (!isNullOrEmpty(text)) {
            try {
                text = text.toLowerCase();
                List<String> list = new ArrayList<>();

                list.add(getTranslateString(text));
                return getGsonResult(Boolean.TRUE, list);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return getGsonResult(Boolean.FALSE, null);
    }
    public static String getTranslateString(String text) {
        char[] myCharArray = text.toCharArray();
        StringBuilder latynTest = new StringBuilder();
        for (char c : myCharArray) {
            latynTest.append(getTestStringLatyn(c));
        }
        return  latynTest.toString();
    }

    public static String getTestStringLatyn(char text) {

        String newValue = String.valueOf(text);

        switch (newValue) {
            case "А":
                newValue = newValue.replace(newValue, "A");
                break;
            case "а":
                newValue = newValue.replace(newValue, "a");
                break;
            case "Ә":
                newValue = newValue.replace(newValue, "AE");
                break;
            case "ә":
                newValue = newValue.replace(newValue, "ae");
                break;
            case "Б":
                newValue = newValue.replace(newValue, "B");
                break;
            case "б":
                newValue = newValue.replace(newValue, "b");
                break;
            case "В":
                newValue = newValue.replace(newValue, "V");
                break;
            case "в":
                newValue = newValue.replace(newValue, "v");
                break;
            case "Г":
                newValue = newValue.replace(newValue, "G");
                break;
            case "г":
                newValue = newValue.replace(newValue, "g");
                break;
            case "Ғ":
                newValue = newValue.replace(newValue, "GH");
                break;
            case "ғ":
                newValue = newValue.replace(newValue, "gh");
                break;
            case "Д":
                newValue = newValue.replace(newValue, "D");
                break;
            case "д":
                newValue = newValue.replace(newValue, "d");
                break;
            case "Ё":
            case "Е":
            case "Э":
                newValue = newValue.replace(newValue, "E");
                break;
            case "ё":
            case "е":
            case "э":
                newValue = newValue.replace(newValue, "e");
                break;
            case "Ж":
                newValue = newValue.replace(newValue, "ZH");
                break;
            case "ж":
                newValue = newValue.replace(newValue, "zh");
                break;
            case "З":
                newValue = newValue.replace(newValue, "Z");
                break;
            case "з":
                newValue = newValue.replace(newValue, "z");
                break;
            case "и":
                newValue = newValue.replace(newValue, "ij");
                break;
            case "И":
                newValue = newValue.replace(newValue, "IJ");
                break;
            case "Й":
                newValue = newValue.replace(newValue, "J");
                break;
            case "й":
                newValue = newValue.replace(newValue, "j");
                break;
            case "К":
                newValue = newValue.replace(newValue, "K");
                break;
            case "к":
                newValue = newValue.replace(newValue, "k");
                break;
            case "Қ":
                newValue = newValue.replace(newValue, "Q");
                break;
            case "қ":
                newValue = newValue.replace(newValue, "q");
                break;
            case "Л":
                newValue = newValue.replace(newValue, "L");
                break;
            case "л":
                newValue = newValue.replace(newValue, "l");
                break;
            case "М":
                newValue = newValue.replace(newValue, "M");
                break;
            case "м":
                newValue = newValue.replace(newValue, "m");
                break;
            case "н":
                newValue = newValue.replace(newValue, "n");
                break;
            case "Н":
                newValue = newValue.replace(newValue, "N");
                break;
            case "Ң":
                newValue = newValue.replace(newValue, "NG");
                break;
            case "ң":
                newValue = newValue.replace(newValue, "ng");
                break;
            case "О":
                newValue = newValue.replace(newValue, "O");
                break;
            case "о":
                newValue = newValue.replace(newValue, "o");
                break;
            case "Ө":
                newValue = newValue.replace(newValue, "OE");
                break;
            case "ө":
                newValue = newValue.replace(newValue, "oe");
                break;
            case "П":
                newValue = newValue.replace(newValue, "P");
                break;
            case "п":
                newValue = newValue.replace(newValue, "p");
                break;
            case "Р":
                newValue = newValue.replace(newValue, "R");
                break;
            case "р":
                newValue = newValue.replace(newValue, "r");
                break;
            case "С":
            case "Ц":
                newValue = newValue.replace(newValue, "S");
                break;
            case "с":
            case "ц":
                newValue = newValue.replace(newValue, "s");
                break;
            case "Т":
                newValue = newValue.replace(newValue, "T");
                break;
            case "т":
                newValue = newValue.replace(newValue, "t");
                break;
            case "У":
            case "Ұ":
                newValue = newValue.replace(newValue, "U");
                break;
            case "у":
            case "ұ":
                newValue = newValue.replace(newValue, "u");
                break;
            case "Ү":
                newValue = newValue.replace(newValue, "UE");
                break;
            case "ү":
                newValue = newValue.replace(newValue, "ue");
                break;
            case "Ф":
                newValue = newValue.replace(newValue, "F");
                break;
            case "ф":
                newValue = newValue.replace(newValue, "f");
                break;
            case "Х":
                newValue = newValue.replace(newValue, "H");
                break;
            case "х":
                newValue = newValue.replace(newValue, "h");
                break;
            case "Ч":
            case "Ш":
            case "Щ":
                newValue = newValue.replace(newValue, "SH");
                break;
            case "ч":
            case "ш":
            case "щ":
                newValue = newValue.replace(newValue, "sh");
                break;
            case "Ъ":
            case "Ь":
                newValue = newValue.replace(newValue, "");
                break;
            case "ъ":
            case "ь":
                newValue = newValue.replace(newValue, "");
                break;
            case "Ы":
                newValue = newValue.replace(newValue, "Y");
                break;
            case "ы":
                newValue = newValue.replace(newValue, "y");
                break;
            case "І":
                newValue = newValue.replace(newValue, "I");
                break;
            case "і":
                newValue = newValue.replace(newValue, "i");
                break;
            case "Ю":
                newValue = newValue.replace(newValue, "JU");
                break;
            case "ю":
                newValue = newValue.replace(newValue, "ju");
                break;
            case "Я":
                newValue = newValue.replace(newValue, "JA");
                break;
            case "я":
                newValue = newValue.replace(newValue, "ja");
                break;
            default:
                newValue = String.valueOf(text);
                break;
        }

        return newValue;
    }
}
