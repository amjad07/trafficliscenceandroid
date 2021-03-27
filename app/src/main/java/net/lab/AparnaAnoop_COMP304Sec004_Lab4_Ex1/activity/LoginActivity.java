package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.R;

public class LoginActivity extends AppCompatActivity {
public static final String MY_PREFS_NAME = "MyPrefsFile";
EditText edit_txt;
Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_txt = findViewById(R.id.edit_txt);
        submit = findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_txt.getText().toString().length()>0){
                    // MY_PREFS_NAME - a static String variable like:

                    //shared perefrences to store the value of examiner id
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("ExaminerId", edit_txt.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this,"Enter the Examiner ID",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}