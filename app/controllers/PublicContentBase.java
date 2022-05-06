package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;

public class PublicContentBase extends Controller {

    private static final int PASSWORD_MIN_LENGTH = 8;

    public static void register(){
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type){
        if (!isPasswordValid(password)){
            flash.error("Password is not secure enough. Should be at least 8 characters long.");
            register();
        } else {
            User u = new User(username, HashUtils.getMd5(password), type, -1);
            u.save();
            registerComplete();
        }
    }

    public static void registerComplete(){
        render();
    }


    private static boolean isPasswordValid(String password){
        return password != null && password.length() >= PASSWORD_MIN_LENGTH;
    }
}
