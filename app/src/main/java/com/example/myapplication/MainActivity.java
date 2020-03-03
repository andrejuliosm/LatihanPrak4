package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String numberTelp;
    private EditText editText;
    private Button call;
    private static final int PHONE_REQUEST_CODE = 986;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call = findViewById(R.id.btn_call);
        editText = findViewById(R.id.et_main);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calling();
            }
        });
    }

    private void calling() {
        numberTelp = editText.getText().toString();
        //Check Apakah diizinkan
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            //Jika sudah diizinkan
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numberTelp));
            startActivity(intent);
        } else {
            // Jika belum diizinkan
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PHONE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PHONE_REQUEST_CODE) {
            // Untuk cek apakah sudah di izinkan
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                //Jika Sudah Di izinkan
                calling();
            else {
                //Jika tidak diizinkan
                Toast.makeText(getApplicationContext(),
                        "Aplikasi Tidak Diizinkan",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
