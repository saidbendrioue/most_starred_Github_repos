package com.example.said.most_starred_github_repos;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String JSON_URL = "https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc";
    static ListView listView;
    static List<Repository> list = new ArrayList<>();
    static CustomAdapter rowAdapter;
    static ProgressDialog dialog ;
    static int page = 2;
    Button moreRepos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        dialog = new ProgressDialog(MainActivity.this);

        moreRepos = (Button)findViewById(R.id.moreRepos);
        moreRepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JsonData(listView, JSON_URL+"&page="+String.valueOf(page), getBaseContext()).execute();
                page++;
            }
        });
        listView = (ListView) findViewById(R.id.listView);
        rowAdapter = new CustomAdapter(this, R.layout.row);
        listView.setAdapter(rowAdapter);
        new JsonData(listView, JSON_URL, getBaseContext()).execute();
    }
}
