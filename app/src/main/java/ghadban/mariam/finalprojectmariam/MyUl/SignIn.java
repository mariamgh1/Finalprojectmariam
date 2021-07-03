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

import ghadban.mariam.finalprojectmariam.R;

public class SignIn extends AppCompatActivity {
    private EditText inEmail, inPassword;
    private Button signinbtn, signupbtn, guestbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setTitle("Login");

        //7. check if i signed in before
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null)// user signd in before
        {
            Intent i = new Intent(getBaseContext(),MapActivity.class);
            finish();
            startActivity(i);
        }

        inEmail = findViewById(R.id.inEmail);
        inPassword = findViewById(R.id.inPassword);
        signinbtn = findViewById(R.id.signinbtn);
        signupbtn = findViewById(R.id.signupbtn);
        guestbtn = findViewById(R.id.guestbtn);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, SignUp.class);
                startActivity(i);
            }
        });

        guestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, MapActivity.class);
                startActivity(i);
            }
        });

    }


    private void validateForm() {
        String Email = inEmail.getText().toString();
        String Password = inPassword.getText().toString();
        boolean isOK = true;
        if (Email.length() < 5 || (Email.indexOf('@') == 0) || Email.indexOf('@') >= Email.length() - 2 || Email.indexOf('.') == 0
                || Email.indexOf('.') >= Email.length() - 1 || Email.lastIndexOf('.') < Email.indexOf('@')) {
            isOK = false;
            inEmail.setError("Wrong E-mail syntax");
        }
//        Myvaildations myVaildations = new Myvaildations();
//        if (myVaildations.validatePasword(Password) == false) {
//            isOK = false;
//            inPassword.setError("Invalid Password");
//        }
        if(Password.length()<6) {

            isOK = false;
            inPassword.setError("Invalid Password");
        }
        if (isOK){
            signIn(Email,Password);
        }
    }

    private void signIn (String Email, String Password)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent i=new Intent(SignIn.this,MapActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(SignIn.this,"Failed", Toast.LENGTH_SHORT).show();
                    inEmail.setError(task.getException().getMessage());
                }
            }
        });
    }

    public void btn_map(View view)
    {
        startActivity(new Intent(getApplicationContext(), MapActivity.class));
    }

}