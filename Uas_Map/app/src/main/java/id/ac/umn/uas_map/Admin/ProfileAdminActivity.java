package id.ac.umn.uas_map.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.umn.uas_map.FirstActivity;
import id.ac.umn.uas_map.MainActivity;
import id.ac.umn.uas_map.ProfileActivity;
import id.ac.umn.uas_map.R;
import id.ac.umn.uas_map.RVActivity;
import id.ac.umn.uas_map.User;

public class ProfileAdminActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    BottomNavigationView nav;
    private String userID;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminprofile);
        nav = findViewById(R.id.nav_bar);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(ProfileAdminActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(ProfileAdminActivity.this, FirstActivity.class);
                        startActivity(home);
                        break;

                    case R.id.history:
                        Toast.makeText(ProfileAdminActivity.this, "History Selected", Toast.LENGTH_SHORT).show();
                        Intent hist = new Intent(ProfileAdminActivity.this, RVActivity.class);
                        startActivity(hist);
                        break;

                    case R.id.user:
                        Toast.makeText(ProfileAdminActivity.this, "Profile Selected Selected", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        logout = (Button) findViewById(R.id.signout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileAdminActivity.this,AdminActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView  emailTextView = (TextView) findViewById(R.id.email);
        final TextView  fullnameTextView = (TextView) findViewById(R.id.name);
        final TextView  ageTextView = (TextView) findViewById(R.id.age);

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String fullname = userProfile.fullname;
                    String email = userProfile.email;
                    String age = userProfile.age;

                    emailTextView.setText(email);
                    fullnameTextView.setText(fullname);
                    ageTextView.setText(age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProfileAdminActivity.this, "Salah", Toast.LENGTH_LONG).show();

            }
        });
    }

}
