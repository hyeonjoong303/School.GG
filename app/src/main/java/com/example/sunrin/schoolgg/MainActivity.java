package com.example.sunrin.schoolgg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    Button btn1;
    String SchoolName;
    String nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext=findViewById(R.id.school_name);
        btn1 = findViewById(R.id.btn1);



        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SchoolName=pref.getString("school_name", "");
        nickname = pref.getString("Nickname", "nickname");
        if(SchoolName.equals("")){

        }
        else if(!nickname.equals("")){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent=new Intent(MainActivity.this,MyinfoActivity.class);
            startActivity(intent);
            Toast.makeText(this, "학교 정보가 존재합니다", Toast.LENGTH_SHORT).show();
        }

        edittext.setText(SchoolName);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,edittext.getText().toString(), Toast.LENGTH_SHORT).show();

                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("school_name", edittext.getText().toString());
                editor.commit();


                Intent intent=new Intent(MainActivity.this,MyinfoActivity.class);
                startActivity(intent);
            }
        });
    }
}



