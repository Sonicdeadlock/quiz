package io.sonicdeadlock.quiz.input;

import io.sonicdeadlock.quiz.Question;
import io.sonicdeadlock.quiz.Quiz;
import io.sonicdeadlock.quiz.controller.QuizController;
import io.sonicdeadlock.quiz.util.InputUtil;

import java.util.List;

import static io.sonicdeadlock.quiz.util.QuizInputUtil.getQuiz;

/**
 * Created by Alex on 9/28/2016.
 */
public class TakeQuizInput {

    public static void Start(){
        Quiz quiz = getQuiz();
        takeQuiz(quiz);
        System.out.println("You scored "+quiz.getScore()+"%");
    }



    private static void takeQuiz(Quiz quiz){
        while (quiz.hasNextQuestion()){
            Question question = quiz.getNextQuestion();
            StringBuilder questionBuilder = new StringBuilder();
            questionBuilder.append(question.getQuestion()).append('\n');
            List<String> shuffledAnswers = question.getShuffledAnswers();
            for (int i = 0; i < shuffledAnswers.size(); i++) {
                questionBuilder.append(i+1)
                        .append(") ")
                        .append(shuffledAnswers.get(i))
                        .append('\n');
            }
            String answer = InputUtil.queryUserForString(questionBuilder.toString());
            boolean isCorrect =quiz.answerCurrentQuestion(answer);
            if(isCorrect)
                System.out.println("Correct!");
            else
                System.out.println("Incorrect");
        }
        QuizController.getInstance().save();
    }




}
