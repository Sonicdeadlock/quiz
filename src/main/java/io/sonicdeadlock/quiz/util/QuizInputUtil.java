package io.sonicdeadlock.quiz.util;

import io.sonicdeadlock.quiz.Quiz;
import io.sonicdeadlock.quiz.controller.QuizController;

import java.util.List;

/**
 * Created by Alex on 9/28/2016.
 */
public class QuizInputUtil {
    public static Quiz getQuiz(){
        QuizController quizController =QuizController.getInstance();
        List<Quiz> quizzes = quizController.getQuizzes();
        System.out.println("Please select a quiz:");
        for (int i = 0; i < quizzes.size(); i++) {
            Quiz q = quizzes.get(i);
            System.out.println((i+1)+") "+q.getName());
            if(q.getAlias()!=null && !"".equalsIgnoreCase(q.getAlias())){
                System.out.println("\tAKA. "+q.getAlias());
            }
        }
        Quiz quiz;
        do{
            String input =InputUtil.queryUserForString("");
            quiz = getQuiz(input);
        }while (quiz==null);
        quiz.reset();
        return quiz;
    }

    public static Quiz getQuiz(String query){
        QuizController quizController =QuizController.getInstance();
        List<Quiz> quizzes = quizController.getQuizzes();
        for (Quiz q :
                quizzes) {
            if(q.getName().equalsIgnoreCase(query) || q.getAlias().equalsIgnoreCase(query))
                return q;
        }
        try {
            int index  = Integer.parseInt(query)-1;//minus one because the listing starts at one
            return quizzes.get(index);
        }catch (Exception e){

        }
        return null;
    }
}
