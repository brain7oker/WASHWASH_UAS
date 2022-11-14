package id.ac.umn.uas_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Nama;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nama = findViewById(R.id.Nama);
        Password = findViewById(R.id.Password);
        Button login = findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkNama();
                Intent i = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(i);
            }
        });

        Button register = findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    void checkNama() {
        boolean isValid = true;
        if (isEmpty(Nama)) {
            Nama.setError("You must enter the name to login");
            isValid = false;
        } else {
            if (!isEmail(Nama)) {
                Nama.setError("Enter valid Nama");
                isValid = false;
            }
        }

        if (isEmpty(Password)) {
            Password.setError("You must enter the password to login");
            isValid = false;
        } else {
            if (!isEmail(Password)) {
                Password.setError("Enter valid Password");
                isValid = false;
            }
        }

        if (isValid) {
            String namaValue = Nama.getText().toString();
            String passwordValue = Password.getText().toString();
            if (namaValue.equals("test@test.com") && passwordValue.equals("password123")){
                Intent i = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(i);
                this.finish();
            } else {
                Toast t = Toast.makeText(this, "Wrong Nama or Password!", Toast.LENGTH_SHORT);
                t.show();
            }
        }

    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}