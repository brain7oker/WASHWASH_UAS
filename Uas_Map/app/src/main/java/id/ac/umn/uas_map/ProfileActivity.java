package id.ac.umn.uas_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    TextView nama;
    TextView email;
    Button signout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        signout = findViewById(R.id.signOut);
        mAuth = FirebaseAuth.getInstance();
        signout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        });
    }

}
