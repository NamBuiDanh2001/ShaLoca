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

import static com.example.hp_bdn.shaloca.constant.EntityConstant.VALID_EMAIL_ADDRESS_REGEX;

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

    public boolean ValidateLogin(String email, String password) {
        boolean valiEmail = checkValiEmail(email);
        boolean valiPass = checkValiPass(password);
        if(valiEmail&&valiPass){
            return true ;
        }
        return  false;

    }

    private boolean checkValiPass(String password) {
          if(password.length() < EntityConstant.Min_Char_Password){
              return false ;
          }
          return  true ;
    }

    private boolean checkValiEmail(String email) {
        Matcher matcher = EntityConstant.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

}
