package io.sonicdeadlock.quiz.input;

import io.sonicdeadlock.quiz.Quiz;
import io.sonicdeadlock.quiz.controller.QuizController;
import io.sonicdeadlock.quiz.util.InputUtil;

import java.util.List;

/**
 * Created by Alex on 9/25/2016.
 */
public class RootInput {
    private static final String OPERATION_QUERY = "What would you like to do?\n1) Create Quiz\n2) Modify a Quiz\n3) Take a quiz";
    public static void Start(){
        while (true){
            Operation operation = getOperation();
            if(operation==Operation.CREATE_QUIZ){
                QuizController quizController = QuizController.getInstance();
                List<Quiz> quizzes = quizController.getQuizzes();
                Quiz newQuiz = CreateQuizInput.getQuiz();
                quizzes.add(newQuiz);
                quizController.save();
            }else if(operation== Operation.TAKE_QUIZ){
                TakeQuizInput.Start();
            }else if(operation==Operation.EDIT_QUIZ){
                ModifyQuizInput.Start();
            }
        }
    }

    private  static Operation getOperation(){
        Operation operation=null;
        do{
            String input = InputUtil.queryUserForString(OPERATION_QUERY).toLowerCase();
            if("1".equalsIgnoreCase(input) || "Create Quiz".equalsIgnoreCase(input) || "Create".equalsIgnoreCase(input)){
                operation = Operation.CREATE_QUIZ;
            }else if("2".equalsIgnoreCase(input) || "Modify a Quiz".equalsIgnoreCase(input) || "Modify quiz".equalsIgnoreCase(input) || "modify".equalsIgnoreCase(input)){
                operation = Operation.EDIT_QUIZ;
            }else if("3".equalsIgnoreCase(input) || "Take a quiz".equalsIgnoreCase(input) || "take quiz".equalsIgnoreCase(input) || "take".equalsIgnoreCase(input)){
                operation = Operation.TAKE_QUIZ;
            }

        }while (operation==null);
        return operation;
    }


}
