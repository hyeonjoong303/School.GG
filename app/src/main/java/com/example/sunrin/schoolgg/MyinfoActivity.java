package com.example.sunrin.schoolgg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MyinfoActivity extends AppCompatActivity {
    String url;
    Button check;
    ImageView Tier1;
    TextView Tier2;
    String tier;
    Button next;
    EditText name;
    EditText Scnum;
    EditText nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        nickname = findViewById(R.id.Nickname);
        check = findViewById(R.id.check_btn);
        Tier1 = findViewById(R.id.image_view);
        Tier2 = findViewById(R.id.Final_check);
        next = findViewById(R.id.next);
        name = findViewById(R.id.MyName);
        Scnum = findViewById(R.id.SchoolNumber);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("MyName", name.getText().toString());
                editor.putString("Schoolnumber", Scnum.getText().toString());
                editor.putString("Nickname", nickname.getText().toString());
                editor.putString("tier", Tier2.getText().toString());
                editor.commit();
                Intent intent=new Intent(MyinfoActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://www.op.gg/summoner/userName=";
                url += nickname.getText().toString();
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            Document doc = Jsoup.connect(url).get();
                            Log.e("doc", doc.toString());
                            tier = doc.select("div[class=TierRank]").first().text();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (tier.contains("Gold")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.gold);

                                    }
                                    else if (tier.contains("Silver")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.silver);
                                    }
                                    else if (tier.contains("Iron")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.iron);
                                    }
                                     else if (tier.contains("Platinum")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.platinum);
                                    }
                                    else if (tier.contains("Bronze")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.bronze);
                                    }
                                    else if (tier.contains("Diamond")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.diamond);
                                    }
                                    else if (tier.contains("Master")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.master);
                                    }
                                    else if (tier.contains("Grandmaster")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.grandmaster);
                                    }
                                    else if (tier.contains("Challenger")){
                                        Tier2.setText(tier);
                                        Tier1.setImageResource(R.drawable.challenger);
                                    }

                                }
                            });


                            Log.e("tier", tier);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();


            }

        });


    }
}
