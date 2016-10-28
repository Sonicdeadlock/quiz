package io.sonicdeadlock.quiz;


import io.sonicdeadlock.quiz.controller.QuizController;
import io.sonicdeadlock.quiz.input.RootInput;
import io.sonicdeadlock.quiz.util.PropertiesLoader;

public class Runner {
    private static final String DEFAULT_QUIZ_SAVE_LOCATION_PROPERTY = "defaultQuizSave";

    public static void main(String[] args) {
        String saveLocation;
        if(args.length>0)
            saveLocation = args[0];
        else
            saveLocation = PropertiesLoader.getProperty(DEFAULT_QUIZ_SAVE_LOCATION_PROPERTY);
        QuizController.getInstance().setSaveLocation(saveLocation);
        RootInput.Start();

    }
}
