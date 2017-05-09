package kz.study.interfaces;

import kz.study.entity.DAudioLesson;
import kz.study.entity.DGrammarLesson;
import kz.study.entity.DLesson;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
public interface DGrammarLessonFactory<P extends DGrammarLesson> {

    P create(String id, String nameRus, String nameKaz, String nameLan, String descRus, String descKaz, String descLan, DLesson dLesson);
}