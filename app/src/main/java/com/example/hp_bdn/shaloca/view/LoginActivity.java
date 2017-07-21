package com.example.hp_bdn.shaloca.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private static final int CODE_REGISTER = 100;
    private Button btn_cancellogin  , btn_login , btn_forgotPass , btn_register ;
    private LinearLayout linearLayout ;
     private EditText edt_passlogin , edt_emaillogin ;
     private  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private CheckBox checkBoxRememberLogin ;
    private TextInputLayout wapperEmail , wapperPass ;
    private  ProgressDialog progressDialog ;
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
        wapperEmail = (TextInputLayout) findViewById(R.id.wapperEmailLogin);
        wapperPass = (TextInputLayout) findViewById(R.id.wapperPassLogin);
        btn_cancellogin = (Button) findViewById(R.id.btn_cancel);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_rigister);
        btn_forgotPass = (Button) findViewById(R.id.btn_forgot_password);
        edt_emaillogin = (EditText) findViewById(R.id.email);
        edt_passlogin = (EditText) findViewById(R.id.password);
        checkBoxRememberLogin = (CheckBox) findViewById(R.id.checknboxRememberLogin);

        edt_emaillogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(edt_emaillogin.getText().toString().isEmpty()){
                        wapperEmail.setError(getString(R.string.hint_email));
                    }
                    else {
                        wapperEmail.setErrorEnabled(false);
                    }
                }
            }
        });
        edt_passlogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    if(edt_passlogin.getText().toString().isEmpty()){
                        wapperPass.setError(getString(R.string.hint_password));
                    }
                    else {
                        wapperPass.setErrorEnabled(false);
                    }
                }
            }
        });

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
                Toast.makeText(this, "chua lam ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login :
                final String email = String.valueOf(edt_emaillogin.getText());
                final String pass = String.valueOf(edt_passlogin.getText());
                progressDialog  = new ProgressDialog(this , android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.setTitle(getString(R.string.titleprocessBarRegister));
                progressDialog.setMessage(getString(R.string.messProcessBarRegister));
//                progressDialog.setProgressStyle();
                progressDialog.show();

                if(!pass.isEmpty()&& !email.isEmpty()){
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    handlerLogin(email, pass);
                                    // onLoginFailed();
                                    progressDialog.dismiss();

                                }
                            },1000);

                }
                break;
            case R.id.btn_rigister :
                Intent intent = new Intent(LoginActivity.this, Register_Activity.class);
                startActivityForResult(intent , CODE_REGISTER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK && requestCode== CODE_REGISTER){
            String pass , email ;
            pass = data.getStringExtra(Register_Activity.KEY_PASS);
            email =data.getStringExtra(Register_Activity.KEY_EMAIL);
            if(email != null && pass != null){
                Snackbar snackbar = Snackbar.make(linearLayout,getString(R.string.sendEmailVerifEmail), Snackbar.LENGTH_LONG);
                snackbar.setDuration(4000);
                snackbar.show();
                edt_emaillogin.setText(email);
                edt_passlogin.setText(pass);
            }

        }


    }

    private void handlerLogin(String email, String pass){
             firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                          final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                           if(firebaseUser == null){
                               Snackbar snackbar = Snackbar.make(linearLayout,"Error !", Snackbar.LENGTH_LONG);
                               snackbar.setDuration(4000);
                               snackbar.show();

                           }
                           else {
                               if (!firebaseUser.isEmailVerified()) {
//                               AlertDialog.Builder builder =   InnitAlterDialog(getString(R.string.title_dialog_error_login) ,
//                                       getString(R.string.mess_dialog_error_accountUnverified ), R.layout.dialog_verified_email );
//                                   handlerEmailVerified(firebaseUser);
                                   Snackbar snackbar = Snackbar.make(linearLayout, getString(R.string.mess_dialog_error_accountUnverified), Snackbar.LENGTH_LONG);
                                    snackbar.setDuration(5000);
                                    snackbar.setActionTextColor(Color.RED);
                                    snackbar.setAction(getString(R.string.trySendEmailVerified), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                             firebaseUser.sendEmailVerification().addOnCompleteListener(LoginActivity.this, new OnCompleteListener<Void>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<Void> task) {
                                                       if(task.isSuccessful()){
                                                           Toast.makeText(LoginActivity.this, "Send Email Complete !", Toast.LENGTH_SHORT).show();
                                                       }
                                                       else {
                                                           Toast.makeText(LoginActivity.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                       }
                                                 }
                                             });
                                        }
                                    });
                                   snackbar.show();
                               }
                               else {
                                  loginsussces();
                               }
                           }
                       }
                       else {

                           Log.i("error ", "onComplete: " + task.getException().getMessage() + "string "+ task.getException().toString());

                           Snackbar snackbar = Snackbar.make(linearLayout, task.getException().getMessage(), Snackbar.LENGTH_LONG);
                           snackbar.setDuration(4000);
                           snackbar.show();
                       }
                 }
             });
    }

    private void loginsussces() {
        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(intent);
    }



    public AlertDialog.Builder InnitAlterDialog(String title , String messenge , int res){
        AlertDialog.Builder alertDialog = new  AlertDialog.Builder(this , res);
        alertDialog.setMessage(messenge);
        alertDialog.setTitle(title);
        return  alertDialog ;
    }
}
