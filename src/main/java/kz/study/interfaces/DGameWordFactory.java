package kz.study.interfaces;

import kz.study.entity.DGameWord;
import kz.study.entity.DGameWordAnswer;
import kz.study.entity.DLesson;

/**
 * @author kussein-at
 * @since 08.05.2017.
 */
public interface DGameWordFactory<P extends DGameWord> {
    P create(String id, DLesson dLesson, String question, DGameWordAnswer dGameWordAnswer, String answer);
}