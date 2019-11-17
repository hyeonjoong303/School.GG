package com.example.sunrin.schoolgg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    TextView myName, mytier, Schooln;
    ImageView mytierimage;
    String name, tier,nickname,schoolname,schoolnumber;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btn;
    Button btn1;

   int isPushed=0;


    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            moveTaskToBack(true);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        myName = findViewById(R.id.home_name);
        mytier = findViewById(R.id.home_tier);
        mytierimage = findViewById(R.id.home_image);
        btn=findViewById(R.id.gotofirend);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        isPushed=pref.getInt("isPushed",0);
        schoolname=pref.getString("school_name", "edittext");
        schoolnumber=pref.getString("Schoolnumber", "SchoolNumber");
        nickname=pref.getString("Nickname", "nickname");
        tier = pref.getString("tier", "");
        name = pref.getString("MyName", "m000000000000yname");
        mytier.setText(tier);
        myName.setText(nickname);



        if(isPushed==0){
            myRef.child(schoolname).push().setValue(new User(name,schoolnumber,nickname,tier));
            editor.putInt("isPushed",1);
            editor.commit();
            isPushed=1;

        }




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
         public void onClick(View view) {

            Intent intent=new Intent(HomeActivity.this,FriendsActivity.class);
            startActivity(intent);
        }
    });

        if (tier.contains("Gold")) {

            mytierimage.setImageResource(R.drawable.gold);

        } else if (tier.contains("Silver")) {
            mytierimage.setImageResource(R.drawable.silver);
        } else if (tier.contains("Iron")) {
            mytierimage.setImageResource(R.drawable.iron);
        } else if (tier.contains("Platinum")) {
            mytierimage.setImageResource(R.drawable.platinum);
        } else if (tier.contains("Bronze")) {
            mytierimage.setImageResource(R.drawable.bronze);
        } else if (tier.contains("Diamond")) {
            mytierimage.setImageResource(R.drawable.diamond);
        } else if (tier.contains("Master")) {
            mytierimage.setImageResource(R.drawable.master);
        } else if (tier.contains("Grandmaster")) {
            mytierimage.setImageResource(R.drawable.grandmaster);
        } else if (tier.contains("Challenger")) {
            mytierimage.setImageResource(R.drawable.challenger);


        }
        btn1=findViewById(R.id.community);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}