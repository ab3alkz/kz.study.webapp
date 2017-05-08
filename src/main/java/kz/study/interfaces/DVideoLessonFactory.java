package kz.study.interfaces;

import kz.study.entity.DLesson;
import kz.study.entity.DUroven;
import kz.study.entity.DVideoLesson;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
public interface DVideoLessonFactory<P extends DVideoLesson> {

    P create(String id, String nameRus, String nameKaz, String nameLan, String link, String descRus, String descKaz, String descLan, DLesson dLesson);
}