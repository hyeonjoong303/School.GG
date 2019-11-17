package com.example.sunrin.schoolgg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends BaseAdapter {
    ArrayList<User> friends;
    Context context;

    public FriendsAdapter(ArrayList<User> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int i) {
        return friends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.friends_item, viewGroup, false);
        }
        TextView name = convertView.findViewById(R.id.item_name);
        TextView nickname = convertView.findViewById(R.id.item_nickname);
        TextView schoolnumber = convertView.findViewById(R.id.item_number);
        TextView tier = convertView.findViewById(R.id.item_tier);
        ImageView rank = convertView.findViewById(R.id.item_image);

        name.setText(friends.get(i).getName());
        nickname.setText(friends.get(i).getNickname());
        schoolnumber.setText(friends.get(i).getSchoolnumber());
        tier.setText(friends.get(i).getTier());

        if (friends.get(i).getTier().contains("Gold")) {

            rank.setImageResource(R.drawable.gold);

        } else if (friends.get(i).getTier().contains("Silver")) {
            rank.setImageResource(R.drawable.silver);
        } else if (friends.get(i).getTier().contains("Iron")) {
            rank.setImageResource(R.drawable.iron);
        } else if (friends.get(i).getTier().contains("Platinum")) {
            rank.setImageResource(R.drawable.platinum);
        } else if (friends.get(i).getTier().contains("Bronze")) {
            rank.setImageResource(R.drawable.bronze);
        } else if (friends.get(i).getTier().contains("Diamond")) {
            rank.setImageResource(R.drawable.diamond);
        } else if (friends.get(i).getTier().contains("Master")) {
            rank.setImageResource(R.drawable.master);
        } else if (friends.get(i).getTier().contains("Grandmaster")) {
            rank.setImageResource(R.drawable.grandmaster);
        } else if (friends.get(i).getTier().contains("Challenger")) {
            rank.setImageResource(R.drawable.challenger);
        }


        return convertView;
    }
}

