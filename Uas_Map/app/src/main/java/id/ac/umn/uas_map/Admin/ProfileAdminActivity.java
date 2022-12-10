package id.ac.umn.uas_map.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import id.ac.umn.uas_map.MainActivity;
import id.ac.umn.uas_map.R;

public class ProfileAdminActivity extends AppCompatActivity {
    TextView nama;
    TextView email;
    Button signout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminprofile);
        signout = findViewById(R.id.signOut);
        mAuth = FirebaseAuth.getInstance();
        signout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(ProfileAdminActivity.this, MainActivity.class));
        });
    }

}
