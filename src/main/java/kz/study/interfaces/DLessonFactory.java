package kz.study.interfaces;

import kz.study.entity.DLesson;
import kz.study.entity.DUroven;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
public interface DLessonFactory<P extends DLesson> {

    P create(String id, String nameRus, String nameKaz, String nameLan, DUroven dUrovenId);
}
