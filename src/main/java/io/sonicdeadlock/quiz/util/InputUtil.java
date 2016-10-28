package io.sonicdeadlock.quiz.util;


import java.util.Scanner;

public class InputUtil {
    private static Scanner keyboardIn = new Scanner(System.in);


    public static String queryUserForString(String query){
        System.out.println(query);
        return keyboardIn.nextLine();
    }

    public static int queryUserForInt(String query){
        boolean found = false;
        int result=0;
        do{
            String input =  queryUserForString(query);
            try{
                result = Integer.parseInt(input);
                found = true;
            }catch (NumberFormatException nfe){

            }
        }while (!found);
        return result;
    }

    public static double queryUserForDouble(String query){
        boolean found = false;
        double result=0;
        do{
            String input =  queryUserForString(query);
            try{
                result = Double.parseDouble(input);
                found = true;
            }catch (NumberFormatException nfe){

            }
        }while (!found);
        return result;
    }

    public static boolean queryUserForBoolean(String query){
        boolean found = false;
        boolean result=false;
        query = query + "(Y/N)";
        do{
            String input =  queryUserForString(query);
            if("yes".equalsIgnoreCase(input) || "y".equalsIgnoreCase(input) || "true".equalsIgnoreCase(input)){
                result = true;
                found = true;
            }else if("no".equalsIgnoreCase(input) || "n".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input)){
                result = false;
                found = true;
            }
        }while (!found);
        return result;
    }

    public static boolean queryUserForBoolean(String query,boolean defaultValue){
        boolean found = false;
        boolean result=false;
        if(defaultValue)
            query = query + "(Y/n)";
        else
            query = query+"(y/N)";
        do{
            String input =  queryUserForString(query);
            if(input == null || "".equalsIgnoreCase(input)){
                result = defaultValue;
                found = true;
            }
            else if("yes".equalsIgnoreCase(input) || "y".equalsIgnoreCase(input) || "true".equalsIgnoreCase(input)){
                result = true;
                found = true;
            }else if("no".equalsIgnoreCase(input) || "n".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input)){
                result = false;
                found = true;
            }
        }while (!found);
        return result;
    }
}
