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

public class RegisterActivity extends AppCompatActivity {
    EditText Nama;
    EditText Phone;
    EditText Password;
    EditText Password2;
    EditText Email;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Nama = findViewById(R.id.Nama);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Password2 = findViewById(R.id.Password2);
        Phone = findViewById(R.id.Phone);
        Register = findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                Intent login = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(login);
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
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

        if (isEmail(Email) == false) {
            Email.setError("Enter valid email!");
        }
    }
}
