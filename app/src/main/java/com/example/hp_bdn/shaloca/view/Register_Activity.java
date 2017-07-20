package com.example.hp_bdn.shaloca.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp_bdn.shaloca.R;
import com.example.hp_bdn.shaloca.maneger.UserManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister ;
    private EditText edtUserName ;
    private EditText edtmail , edtPass , edtPassConfirm ;
    private TextView tv_login ;
    private LinearLayout linearLayout ;
    private  TextInputLayout usernameWrapper , passwordWrapper , emailWrapper , passconfirmWrapper ;
    public  static final    String KEY_EMAIL = "email";
    public static final String KEY_PASS = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        innitView();
    }

    private void innitView() {
        linearLayout = (LinearLayout) findViewById(R.id.linerlayout);
        edtmail = (EditText) findViewById(R.id.input_email_register);
        edtPass = (EditText) findViewById(R.id.input_password_register);
        edtPassConfirm = (EditText) findViewById(R.id.input_confrimpassword);
        edtUserName = (EditText) findViewById(R.id.input_username);
        btnRegister = (Button) findViewById(R.id.btn_creatAcc);
        tv_login = (TextView) findViewById(R.id.tv_login);
        usernameWrapper = (TextInputLayout) findViewById(R.id.layoutInputUserName);
        emailWrapper = (TextInputLayout) findViewById(R.id.layoutInputEmail);
        passwordWrapper = (TextInputLayout) findViewById(R.id.layoutInputPass);
        passconfirmWrapper = (TextInputLayout) findViewById(R.id.layoutInputPassConfirm);

        edtUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               if(!hasFocus){
                   if(edtUserName.getText().toString().length() <= 3){
                       usernameWrapper.setError(getString(R.string.Valiusername));
                   }
                   else {
                       usernameWrapper.setErrorEnabled(false);
                   }
               }
            }
        });
        edtmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    if(!UserManager.checkValiEmail(edtmail.getText().toString())){
                        emailWrapper.setError(getString(R.string.valiEmail));
                    }
                    else {
                        emailWrapper.setErrorEnabled(false);
                    }
                }
            }
        });
        edtPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(edtPass.getText().toString().length()<=6){
                        passwordWrapper.setError(getString(R.string.valiPass));
                    }
                    else {
                        passwordWrapper.setErrorEnabled(false);
                    }
                }
            }
        });
        edtPassConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus ){
                    String pass = edtPass.getText().toString();
                    String passConfirm = edtPassConfirm.getText().toString();
                    if(!pass.equals(passConfirm) || pass.isEmpty()){
                        passconfirmWrapper.setError(getString(R.string.valiPassconfirm));
                    }
                    else {
                        passconfirmWrapper.setErrorEnabled(false);
                    }
                }
            }
        });

        btnRegister.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_creatAcc :
                handlRegister();
                break;
            case R.id.tv_login :
                Intent intent = new Intent(Register_Activity.this , LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void handlRegister() {
        String email = edtmail.getText().toString();
        String username = edtUserName.getText().toString();
        String pass = edtPass.getText().toString();
        String passConfirm = edtPassConfirm.getText().toString();
         boolean chek = true ;
        if(username.length() <=3 ){
           chek = false ;
        }
        if(!UserManager.checkValiEmail(email)){
            chek = false ;
        }
        if(pass.length()<= 6){
            chek = false ;
        }
        if(!passConfirm.equals(pass)){
            chek = false ;
        }

        if(chek){
            Intent intent = new Intent(Register_Activity.this , LoginActivity.class);
            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            if(firebaseUser== null){
                                Snackbar snackbar = Snackbar.make(linearLayout,"Error Register ! " , Snackbar.LENGTH_LONG);
                                snackbar.show();

                            }
                            else {
                                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Snackbar snackbar = Snackbar.make(linearLayout,getString(R.string.sendEmailVerifEmail), Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                });
                            }

                    }
                }
            });
            intent.putExtra(KEY_EMAIL ,email);
            intent.putExtra(KEY_PASS , pass);
            startActivity(intent);
        }

    }


}
