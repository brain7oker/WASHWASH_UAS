package id.ac.umn.uas_map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class OrderActivity extends AppCompatActivity {
    EditText Nama;
    EditText Phone;
    EditText Loc;
    EditText Car;
    EditText Time;
    Button Location;
    Button Number;
    Button Order;
    String[] items =  {"1","2","3","4"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    private static final int REQUEST_CODE = 22;
    Button btnpicture;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        EditText edit_name = findViewById(R.id.Nama);
        EditText edit_phone = findViewById(R.id.Phone);
        EditText edit_loc = findViewById(R.id.Location);
        EditText edit_car = findViewById(R.id.Number);
        EditText edit_image = findViewById(R.id.Time);

//        EditText edit_time = findViewById(R.id.edit_name);
        DAOEmployee dao = new DAOEmployee();
//        Nama = findViewById(R.id.Nama);
//        Phone = findViewById(R.id.Phone);
//        Location = findViewById(R.id.Location);

//        Car = findViewById(R.id.Number);
//        Time = findViewById(R.id.Time);
        Order = findViewById(R.id.Order);


        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

//        Toast.makeText(OrderActivity.this, "beta1", Toast.LENGTH_SHORT).show();
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        Order.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //checkDataEntered();
                Employee emp = new Employee(edit_name.getText().toString(), edit_phone.getText().toString(),edit_car.getText().toString(), edit_loc.getText().toString(), edit_image.getText().toString());
                dao.add(emp).addOnSuccessListener(suc ->
                {
                    Toast.makeText(OrderActivity.this, "Inserted", Toast.LENGTH_SHORT).show();

                }).addOnFailureListener(er ->
                {
                    Toast.makeText(OrderActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
                Intent order = new Intent(OrderActivity.this, SuccessActivity.class);
                startActivity(order);
            }
        });


        btnpicture = findViewById(R.id.btncamera_id);
        imageView = findViewById(R.id.imageView1);

        btnpicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CODE);
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

        if (isEmpty(Loc)) {
            Toast t = Toast.makeText(this, "Location is required!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(Car)) {
            Toast t = Toast.makeText(this, "Number is required!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(Time)) {
            Toast t = Toast.makeText(this, "Time is required!", Toast.LENGTH_SHORT);
            t.show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }else{
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
