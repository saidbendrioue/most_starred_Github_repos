package com.example.said.most_starred_github_repos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonData extends AsyncTask<Void, Void, List<Repository>> {
    Context context;
    List<Repository> list;
    ListView simpleList;
    String JSON_STRING;

    public JsonData(ListView simpleList, String JSON_STRING, Context context) {
        this.list = new ArrayList<>();
        this.simpleList = simpleList;
        this.JSON_STRING = JSON_STRING;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
       MainActivity.dialog.setMessage("Processing...");
        MainActivity.dialog.show();
    }

    @Override
    protected List<Repository> doInBackground(Void... params) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            StringBuilder JSON_DATA = new StringBuilder();
            URL url = new URL(JSON_STRING);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((JSON_STRING = reader.readLine()) != null) {
                JSON_DATA.append(JSON_STRING).append("\n");
            }
            JSON_STRING = JSON_DATA.toString().trim();

            JSONObject json = new JSONObject(JSON_STRING);
            JSONArray items = json.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                String name = items.getJSONObject(i).getString("name");
                String description = items.getJSONObject(i).getString("description");
                int stars = items.getJSONObject(i).getInt("stargazers_count");
                String owner = items.getJSONObject(i).getJSONObject("owner").getString("login");
                Bitmap avatar = getBitmapFromURL(items.getJSONObject(i).getJSONObject("owner").getString("avatar_url"));
                Log.e(String.valueOf(i), name);
                Log.e(String.valueOf(i), String.valueOf(stars));
                Log.e(String.valueOf(i), description);
                Log.e(String.valueOf(i), owner);
                Log.e(String.valueOf(i), avatar.toString());
                list.add(new Repository(name, description, stars, owner, avatar));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Repository> result) {
        MainActivity.dialog.dismiss();
        for(int i =0;i<result.size();i++)
            MainActivity.rowAdapter.add(result.get(i));
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            return null;
        }
    }
}
