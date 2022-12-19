package id.ac.umn.uas_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOEmployee dao;
    boolean isLoading=false;
    String key =null;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvactivity);
        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOEmployee();
        nav = findViewById(R.id.nav_bar);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(RVActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(RVActivity.this, FirstActivity.class);
                        startActivity(home);
                        break;

                    case R.id.history:
                        Toast.makeText(RVActivity.this, "History Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.user:
                        Toast.makeText(RVActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RVActivity.this, ProfileActivity.class);
                        startActivity(i);
                }
                return false;
            }
        });
//        Toast.makeText(this, "beta1", Toast.LENGTH_SHORT).show();
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
               LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
               int totalItem = linearLayoutManager.getItemCount();
               int lastvisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
               if(totalItem< lastvisible+3){
                   if(!isLoading){
                       isLoading=true;
                       loadData();
                   }
               }

            }
        });

//        Toast.makeText(this, "beta2", Toast.LENGTH_SHORT).show();
    }

        private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
//        Toast.makeText(this, "beta2", Toast.LENGTH_SHORT).show();
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<id.ac.umn.uas_map.Employee> emps = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    id.ac.umn.uas_map.Employee emp = data.getValue(id.ac.umn.uas_map.Employee.class);
                    emps.add(emp);
                    key = data.getKey();
                }
                adapter.setItems(emps);
                adapter.notifyDataSetChanged();
                isLoading =false;
                swipeRefreshLayout.setRefreshing(false);
//                Toast.makeText(RVActivity.this, "beta3", Toast.LENGTH_SHORT).show();
//                Toast.makeText(RVActivity.this, "beta2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}