package com.example.said.most_starred_github_repos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.said.most_starred_github_repos.Repository;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public CustomAdapter(Context context, int resource) {
        super(context, resource);
    }

    public  void add(Repository object){
        super.add(object);
        list.add(object);
    }
    @Override
    public  int getCount(){
        return list.size();
    }
    @Override
    public  Object getItem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row ;
        row = convertView;
        ReposHolder reposHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row,parent,false);
            reposHolder = new ReposHolder();

            reposHolder.name = row.findViewById(R.id.name);
            reposHolder.description = row.findViewById(R.id.description);
            reposHolder.owner = row.findViewById(R.id.owner);
            reposHolder.stars = row.findViewById(R.id.stars);
            reposHolder.avatar = row.findViewById(R.id.avatar);

            row.setTag(reposHolder);

        }else {
            reposHolder = (ReposHolder)row.getTag();
        }

        Repository r = (Repository)this.getItem(position);
        reposHolder.name.setText(r.getName());
        reposHolder.description.setText(r.getDescription());
        reposHolder.owner.setText(r.getOwner());
        reposHolder.avatar.setImageBitmap(r.getAvatar());
        reposHolder.stars.setText(String.valueOf(r.getStars()));

        return row;
    }
    static class ReposHolder {
        TextView name, owner, description,stars;
        ImageView avatar;
    }
}
