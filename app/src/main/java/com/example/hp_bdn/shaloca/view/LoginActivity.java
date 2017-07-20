package com.example.hp_bdn.shaloca.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hp_bdn.shaloca.R;
import com.example.hp_bdn.shaloca.maneger.UserManager;
import com.example.hp_bdn.shaloca.presenter.LoginPrecenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
     private Button btn_cancellogin  , btn_login , btn_forgotPass , btn_register ;
    private LinearLayout linearLayout ;
     private EditText edt_passlogin , edt_emaillogin ;
     private  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private CheckBox checkBoxRememberLogin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        listenerEvent();
    }

    private void listenerEvent() {
        btn_forgotPass.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_cancellogin.setOnClickListener(this);
    }

    private void initView() {
          linearLayout = (LinearLayout) findViewById(R.id.linearLogin);
        btn_cancellogin = (Button) findViewById(R.id.btn_cancel);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_rigister);
        btn_forgotPass = (Button) findViewById(R.id.btn_forgot_password);
        edt_emaillogin = (EditText) findViewById(R.id.email);
        edt_passlogin = (EditText) findViewById(R.id.password);
        checkBoxRememberLogin = (CheckBox) findViewById(R.id.checknboxRememberLogin);
        Intent intent = getIntent();
        String pass , email ;
        pass = intent.getStringExtra(Register_Activity.KEY_PASS);
        email =intent.getStringExtra(Register_Activity.KEY_EMAIL);
        if(pass != null){
            edt_passlogin.setText(pass);
        }
        if(email != null){
            edt_emaillogin.setText(email);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel :

                break;
            case R.id.btn_forgot_password :
                break;
            case R.id.btn_login :
                String email = String.valueOf(edt_emaillogin.getText());
                String pass = String.valueOf(edt_passlogin.getText());
                if(email.isEmpty()|| pass.isEmpty()){
                    Toast.makeText(this,getString(R.string.emaiorpassnull), Toast.LENGTH_LONG).show();
                }
                if(!pass.isEmpty()&& !email.isEmpty()){
                    // pass more than 6 letter
                    if(!UserManager.ValidateLogin(email , pass)){
                        InnitAlterDialog(getString(R.string.title_dialog_error_login) ,
                                getString(R.string.mess_dialog_error_login) , R.style.Theme_AppCompat_DayNight_Dialog_Alert).create().show();
                    }
                    else {
                        handlerLogin(email, pass);
                    }
                }
                break;
            case R.id.btn_rigister :
                Intent intent = new Intent(LoginActivity.this, Register_Activity.class);
                startActivity(intent);
                break;
        }
    }

    private void handlerLogin(String email, String pass) {

             firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                          FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                           if(firebaseUser == null){
                               Snackbar snackbar = Snackbar.make(linearLayout,"Error !", Snackbar.LENGTH_LONG);
                               snackbar.show();

                           }
                           else {
                               if (!firebaseUser.isEmailVerified()) {
//                               AlertDialog.Builder builder =   InnitAlterDialog(getString(R.string.title_dialog_error_login) ,
//                                       getString(R.string.mess_dialog_error_accountUnverified ), R.layout.dialog_verified_email );
                                   handlerEmailVerified(firebaseUser);
                               }
                               else {
                                  loginsussces();
                               }
                           }
                       }
                       else {

                           Snackbar snackbar = Snackbar.make(linearLayout, getString(R.string.accountUnExist), Snackbar.LENGTH_LONG);
                           snackbar.show();
                       }
                 }
             });
    }

    private void loginsussces() {
        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(intent);
    }

    private void handlerEmailVerified(final FirebaseUser firebaseUser ) {
        final Dialog dialog = new Dialog(LoginActivity.this ,R.style.Theme_AppCompat_Light_Dialog);
        dialog.setContentView(R.layout.dialog_verified_email);
        Button btnCheckmailVerified = (Button) dialog.findViewById(R.id.btn_checkverifiedEmail);
        Button btnSendkmailVerified = (Button) dialog.findViewById(R.id.btn_sendverifiedEmail);
        dialog.setTitle(getString(R.string.title_dialog_error_login));
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        btnSendkmailVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCheckmailVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseUser.sendEmailVerification().addOnCompleteListener(LoginActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Snackbar snackbar = Snackbar.make(linearLayout,getString(R.string.sendEmailVerifEmail), Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                        else {
                            Snackbar snackbar = Snackbar.make(linearLayout,task.getException().getMessage(), Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });
                dialog.dismiss();
            }
        });

    }



    public AlertDialog.Builder InnitAlterDialog(String title , String messenge , int res){
        AlertDialog.Builder alertDialog = new  AlertDialog.Builder(this , res);
        alertDialog.setMessage(messenge);
        alertDialog.setTitle(title);
        return  alertDialog ;
    }
}
