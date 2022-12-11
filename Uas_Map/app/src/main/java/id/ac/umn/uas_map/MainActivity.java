package id.ac.umn.uas_map;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText Editemail,Editpassword;
    private Button signin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(this);

        Editemail = (EditText) findViewById(R.id.email);
        Editpassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth =FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.signin:
                userLogin();
                break;


        }

    }

    private void userLogin() {

        String email =Editemail.getText().toString().trim();
        String password = Editpassword.getText().toString().trim();

        if (email.isEmpty()){
            Editemail.setError("Email is required");
            Editemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Editemail.setError("Please provide a valid email");
            Editemail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            Editpassword.setError("Password is required");
            Editpassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            Editpassword.setError("Password should be at least 6 charactes");
            Editpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));

                }else{
                    Toast.makeText(MainActivity.this, "Failed to log in", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
