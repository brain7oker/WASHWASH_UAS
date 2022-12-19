package id.ac.umn.uas_map.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import id.ac.umn.uas_map.FirstActivity;
import id.ac.umn.uas_map.MainActivity;
import id.ac.umn.uas_map.OrderActivity;
import id.ac.umn.uas_map.R;
import id.ac.umn.uas_map.RVActivity;

public class AdminActivity extends AppCompatActivity {

    BottomNavigationView nav;
    ImageButton wash;
    ImageButton detail;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        nav = findViewById(R.id.nav_bar);
        wash = findViewById(R.id.wash);
        detail = findViewById(R.id.detail);
        mAuth = FirebaseAuth.getInstance();
        wash.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        detail.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(AdminActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.history:
                        Toast.makeText(AdminActivity.this, "History Selected", Toast.LENGTH_SHORT).show();
                        Intent hist = new Intent(AdminActivity.this, RVActivity.class);
                        startActivity(hist);
                        break;

                    case R.id.user:
                        Intent i = new Intent(AdminActivity.this, ProfileAdminActivity.class);
                        startActivity(i);
                }
                return false;
            }
        });
        wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });


        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(AdminActivity.this, MainActivity.class));
        }
    }

}
