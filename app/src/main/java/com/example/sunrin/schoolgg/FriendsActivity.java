package com.example.sunrin.schoolgg;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FriendsActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    String schoolname;
    ListView listView;
    ArrayList<User> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        listView = findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        schoolname = pref.getString("school_name", "edittext");
        friends = new ArrayList<>();
        final FriendsAdapter mAdapter = new FriendsAdapter(friends, this);
        listView.setAdapter(mAdapter);


        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                Collections.sort(friends,sort);
                mAdapter.notifyDataSetChanged();

            }
        };

        myRef.child(schoolname).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final User user = dataSnapshot.getValue(User.class);
                Log.e("friend's name", user.nickname);


                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        String url = "https://www.op.gg/summoner/userName=";
                        url += user.nickname;
                        try {
                            Document doc = Jsoup.connect(url).get();
                            Log.e("url", url);
                            Elements ranking = doc.select("span[class=ranking]");

                            for (Element e : ranking) {
                                Log.e("ranking: ", Integer.parseInt(change(e.text())) + "");
                                user.ranking = Integer.parseInt(change(e.text()));
                                friends.add(0, user);
                                Message msg = handler.obtainMessage();
                                handler.sendMessage(msg);


                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();
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

    }

    public String change(String data) {

        return data.replaceAll("\\,", "");

    }
    private final static Comparator<User> sort=new Comparator<User>() {
        @Override
        public int compare(User user, User t1) {
            return Integer.compare(user.ranking,t1.ranking);
        }
    };
}

