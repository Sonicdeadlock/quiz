package io.sonicdeadlock.quiz.input;

import io.sonicdeadlock.quiz.Question;
import io.sonicdeadlock.quiz.Quiz;
import io.sonicdeadlock.quiz.controller.QuizController;
import io.sonicdeadlock.quiz.util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import static io.sonicdeadlock.quiz.util.QuizInputUtil.getQuiz;

/**
 * Created by Alex on 9/28/2016.
 */
public class ModifyQuizInput {
    public static void Start(){
      Quiz quiz = getQuiz();
        modifyQuiz(quiz);
        QuizController.getInstance().save();
    }

    private static void modifyQuiz(Quiz quiz){
        if(InputUtil.queryUserForBoolean("Would you like to change the name of the quiz?("+quiz.getName()+")",false)){
            quiz.setName(InputUtil.queryUserForString("Please enter new name of the quiz:"));
        }
        if(InputUtil.queryUserForBoolean("Would you like to change the alias of the quiz?("+quiz.getAlias()+")",false)){
            quiz.setAlias(InputUtil.queryUserForString("Please enter the new alias of the quiz:"));
        }
        ArrayList<Integer> removeIndices = new ArrayList<Integer>();
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question question = quiz.getQuestions().get(i);
            if(InputUtil.queryUserForBoolean("Would you like to modify or remove question #"+(i+1)+"? ("+question.getQuestion()+")",false)){
                if(InputUtil.queryUserForBoolean("Would you like to remove question #"+(i+1)+"? ("+question.getQuestion()+")",false)){
                    removeIndices.add(i);
                }else{
                    modifyQuestion(question);
                }

            }
        }
        for (int i = removeIndices.size() - 1; i >= 0; i--) {
            int index = removeIndices.get(i);
            quiz.getQuestions().remove(index);
        }
        if (InputUtil.queryUserForBoolean("Would you like to add a question?",false)){
          CreateQuizInput.addQuestions(quiz);
        }

    }

    private static void modifyQuestion(Question question){
        if(InputUtil.queryUserForBoolean("Would you like to change the name of the question?("+question.getQuestion()+")",false)){
            question.setQuestion(InputUtil.queryUserForString("Please enter the new name of the question:"));
        }
        if(InputUtil.queryUserForBoolean("Would you like to change the correct answer?("+question.getCorrectAnswer()+")",false)){
            question.setCorrectAnswer(InputUtil.queryUserForString("Please enter the new correct answer:"));
        }
        if(InputUtil.queryUserForBoolean("Would you like to change any of the incorrect answers?",false)){
            modifyIncorrectAnswers(question);
        }
    }

    private static void modifyIncorrectAnswers(Question question){
        List<String> incorrectAnswers = question.getIncorrectAnswers();
        ArrayList<Integer> removeIndices = new ArrayList<Integer>();
        for (int i = 0; i < incorrectAnswers.size(); i++) {
            if(InputUtil.queryUserForBoolean("Would you like to remove the answer ("+incorrectAnswers.get(i)+")?",false)){
                removeIndices.add(i);
            }else if(InputUtil.queryUserForBoolean("Would you like to change the answer ("+incorrectAnswers.get(i)+")?",false)){
                incorrectAnswers.set(i,InputUtil.queryUserForString("Please enter the new incorrect answer"));
            }
        }
        for (int i = removeIndices.size() - 1; i >= 0; i--) {
            int index = removeIndices.get(i);
            incorrectAnswers.remove(index);
        }
        if(InputUtil.queryUserForBoolean("Would you like to add incorrect answers?",false)){
            for (String incorrectAnswer : CreateQuizInput.getIncorrectAnswers()) {
                question.getIncorrectAnswers().add(incorrectAnswer);
            }
        }
    }



}
