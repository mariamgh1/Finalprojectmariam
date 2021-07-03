package ghadban.mariam.finalprojectmariam.MyUl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ghadban.mariam.finalprojectmariam.MyUtils.Myvaildations;
import ghadban.mariam.finalprojectmariam.R;

public class SignUp extends AppCompatActivity {
    private EditText etFullName, etUsername, etEmail, etPassword, etConPassword;
    private Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign up");

        etFullName = findViewById(R.id.etFullName);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConPassword = findViewById(R.id.etConPassword);
        savebtn = findViewById(R.id.savebtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFrom();
            }
        });
    }

    private void validateFrom(){
        String fulln = etFullName.getText().toString();
        String usern = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        String cpass = etConPassword.getText().toString();
        String save = savebtn.getText().toString();

        boolean isOK = true;

        if (fulln.length() < 2) {
            isOK = false;
            etFullName.setError("at least two letter");
        }

        if (email.length() < 5 || (email.indexOf('@') == 0) || email.indexOf('@') >= email.length() - 2 || email.indexOf('.') == 0
                || email.indexOf('.') >= email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@')) {
            isOK = false;
            etEmail.setError("Wrong E-mail. Try again");
        }
        if(!pass.equals(cpass)){
            isOK=false;
            etConPassword.setError("Password must be the same");
        }
        if(isOK)// isok=true
        {
            //todo : create account  and return to sign in screen/ close this screen

            createNewAccount(fulln,pass,email);
        }
    }

    /**
     *  @param fulln
     * @param pass
     * @param email
     */

    private void createNewAccount(String fulln, String pass, String email)

    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        OnCompleteListener listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Successfully Signin up", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this, SignIn.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(SignUp.this, "Signing up, failed" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    etEmail.setError("signing up, failed"+task.getException().getMessage());
                }
            }
        };
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(listener);
    }
}
