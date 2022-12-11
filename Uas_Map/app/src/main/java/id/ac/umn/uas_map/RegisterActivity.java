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
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NonNls;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener{


    private TextView banner;
    private EditText Editage,Editfullname,Editemail,Editpassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    Button registerbutton;
//    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerbutton = findViewById(R.id.registerbutton);
        registerbutton.setOnClickListener(this);

        Editage = (EditText) findViewById(R.id.age);
        Editfullname = (EditText) findViewById(R.id.fullname);
        Editemail = (EditText) findViewById(R.id.email);
        Editpassword = (EditText) findViewById(R.id.password);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerbutton:
                registeruser();
                break;
        }

    }

    private void registeruser() {
        String email = Editemail.getText().toString().trim();
        String age = Editage.getText().toString().trim();
        String fullname = Editfullname.getText().toString().trim();
        String password = Editpassword.getText().toString().trim();

        if (fullname.isEmpty()){
            Editfullname.setError("Full name is required");
            Editfullname.requestFocus();
            return;
        }

        if (age.isEmpty()){
            Editage.setError("Age is required");
            Editage.requestFocus();
            return;
        }

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

        progressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullname,age,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this, "User has been registered", Toast.LENGTH_LONG).show();
                                                progressbar.setVisibility(View.GONE);
                                            }else
                                                Toast.makeText(RegisterActivity.this, "Failed to register , Try again", Toast.LENGTH_LONG).show();
                                            progressbar.setVisibility(View.GONE);
                                        }
                                    });
                        }
                    }
                });

    }
}