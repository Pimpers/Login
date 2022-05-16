package com.oz.date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisteActivity extends AppCompatActivity
{
private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText mEtmail,mEtPwd;
    private Button mBtnRegister;
 @Override
    protected void onCreate(Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);

        mFirebaseAuth =FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("android");

        mEtmail =findViewById(R.id.et_email);
        mEtPwd =findViewById(R.id.et_pwd);
        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
             //회원가입 처리 시작
                String strEmail = mEtmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                //파이어 베이스  auth 실행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail,strPwd ).addOnCompleteListener(RegisteActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                    if  (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                        UserAccount account =new UserAccount();
                        account.setIdToken(firebaseUser.getUid());
                        account.setEmailId(firebaseUser.getEmail());
                        account.setPassword(strPwd);

                        mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                        Toast.makeText(RegisteActivity.this,"회원가입에 성공하셧습니다",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisteActivity.this,"회원가입에 실패하셨습니다",Toast.LENGTH_SHORT).show();
                    }
                    }
                });

            }
        });

    }
}