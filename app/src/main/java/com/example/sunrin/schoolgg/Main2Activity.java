package com.example.sunrin.schoolgg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listView1;
   ArrayList<Board> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        posts=new ArrayList<>();
      listView1 =(ListView) findViewById(R.id.listview1);
        Button btn1;
        final EditText ed1,ed2;
        btn1 =findViewById(R.id.put);
        ed1 = findViewById(R.id.top_name);
        ed2 = findViewById(R.id.content);

        final ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        listView1.setAdapter(adapter) ;


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Main2Activity.this,PostActivity.class);
                intent.putExtra("title",posts.get(i).tittle);
                intent.putExtra("content",posts.get(i).content);
                startActivity(intent);
            }
        });
        DatabaseReference database =FirebaseDatabase.getInstance().getReference();
        database.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, @Nullable String s) {
                Board board = dataSnapshot.getValue(Board.class);
                posts.add(board);
                adapter.add(board.gettitle());
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                Board board = new Board(ed1.getText().toString(), ed2.getText().toString());
                database.child("message").push().setValue(board);

            }
        });






    }

}
