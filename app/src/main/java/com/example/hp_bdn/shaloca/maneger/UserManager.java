package com.example.hp_bdn.shaloca.maneger;

import android.support.annotation.NonNull;

import com.example.hp_bdn.shaloca.constant.EntityConstant;
import com.example.hp_bdn.shaloca.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by HP_BDN on 19-Jul-17.
 */

public class UserManager {
    private User user ;
    private static  UserManager instance ;
    private  UserManager(){
        user = new User();
    }
    public static UserManager getInstance(){
        if(instance== null){
              instance = new UserManager();
        }
        return  instance ;
    }

    public static boolean ValidateLogin(String email, String password) {
        boolean valiEmail = checkValiEmail(email);
        boolean valiPass = checkValiPass(password);
        if(valiEmail && valiPass){
            return true ;
        }
        return  false;

    }

    public static boolean checkValiPass(String password) {
          if(password.length() < EntityConstant.Min_Char_Password){
              return false ;
          }
          return  true ;
    }

    public static boolean checkValiEmail(String email) {
      Matcher matcher = Pattern.compile(EntityConstant.EMAIL_PATTERN).matcher(email);
        return matcher.matches();
    }

}
