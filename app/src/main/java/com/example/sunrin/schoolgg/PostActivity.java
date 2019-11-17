package com.example.sunrin.schoolgg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {
    TextView title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        title = findViewById(R.id.post_title);
        content = findViewById(R.id.post_content);

        Intent intent=getIntent();
        String sTitle = intent.getStringExtra("title");
        String sContent=intent.getStringExtra("content");

        title.setText(sTitle);
        content.setText(sContent);

    }
}
