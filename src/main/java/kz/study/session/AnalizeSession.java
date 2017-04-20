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
}