package id.ac.umn.uas_map;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    EditText Nama;
    EditText Phone;
    Button Location;
    Button Number;
    Button Time;
    Button Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Nama = findViewById(R.id.Nama);
        Phone = findViewById(R.id.Phone);
        Location = findViewById(R.id.Location);
        Number = findViewById(R.id.Number);
        Time = findViewById(R.id.Time);
        Order = findViewById(R.id.Order);

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                Intent login = new Intent(OrderActivity.this, OrderActivity.class);
                startActivity(login);
            }
        });

        Button Time = findViewById(R.id.Time);
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Time = new Intent(OrderActivity.this, DatetimeActivity.class);
                startActivity(Time);
            }
        });
    }

    boolean isPhone(EditText text) {
        CharSequence Phone = text.getText().toString();
        return (!TextUtils.isEmpty(Phone) && Patterns.PHONE.matcher(Phone).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(Nama)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(Phone)) {
            Phone.setError("Phone number is required!");
        }

    }
}
