package me.kamili.rachid.celebrityapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import me.kamili.rachid.celebrityapp.adapter.CelebrityAdapter;
import me.kamili.rachid.celebrityapp.data.LocalDataSource;
import me.kamili.rachid.celebrityapp.model.Celebrity;

public class CelebritiesActivity extends AppCompatActivity {

    private static final int WRITE_EXTERNAL_STORAGE = 1;
    private ListView lvCelebrities;
    private FloatingActionButton showFavoritesBtn;
    private FloatingActionButton hideFavoritesBtn;
    public static boolean fav = false;
    private CelebrityAdapter adapter;
    private List<Celebrity> celebrityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities);

        lvCelebrities = findViewById(R.id.lvCelebrities);

//        celebrityList.add(new Celebrity((long) 1,"Margot", "Robbie","Actress"));
//        celebrityList.add(new Celebrity((long) 2,"Adam", "Driver","Actor"));
//        celebrityList.add(new Celebrity((long) 3,"Kate", "McKinnon","Comedian"));
//        celebrityList.add(new Celebrity((long) 4,"Lucy", "Liu","Artist"));
//        celebrityList.add(new Celebrity((long) 5,"Ellen", "DeGeneres","Comedian"));
//        celebrityList.add(new Celebrity((long) 6,"Jim", "Parsons","Actor",true));
//        celebrityList.add(new Celebrity((long) 7,"Donald", "Trump","Politician"));
//        celebrityList.add(new Celebrity((long) 8,"Kanye", "West","Singer"));
//        celebrityList.add(new Celebrity((long) 9,"Taylor", "Swift","Singer"));
        initButtons();
    }

    private void initButtons() {
        showFavoritesBtn = findViewById(R.id.showFavoritesBtn);
        hideFavoritesBtn = findViewById(R.id.hideFavoritesBtn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        LocalDataSource dataSource = new LocalDataSource(this);

        celebrityList = dataSource.getCelebrities();

        adapter = new CelebrityAdapter(this, celebrityList);
        lvCelebrities.setAdapter(adapter);
        //lvCelebrities.invalidateViews();
    }

    public void addCelebrity(View view) {
        Intent intent = new Intent(this, NewCelebrityActivity.class);
        startActivity(intent);
    }

    public void showFavorites(View view) {
        showFavoritesBtn.setVisibility(View.GONE);
        hideFavoritesBtn.setVisibility(View.VISIBLE);
        fav = true;
        adapter.notifyDataSetChanged();
    }

    public void hideFavorites(View view) {
        showFavoritesBtn.setVisibility(View.VISIBLE);
        hideFavoritesBtn.setVisibility(View.GONE);
        fav = false;
        adapter.notifyDataSetChanged();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }

    private void showWritePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE);
        } else {
            Toast.makeText(this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }
    }

    public void writeToFile(View v) {

        //if(!isExternalStorageWritable())
        showWritePermission();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String data = gson.toJson(celebrityList);
        FileOutputStream outputStream;
        try {
            String absolutePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            outputStream = new FileOutputStream(new File(absolutePath + "/celebrities.json"), false);
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(this, "celebrities.json saved on Download folder", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Couldn't save celebrities.json on Download folder", Toast.LENGTH_LONG).show();
        }
    }

    public void readFromFile(View v) {

        //showFileChooser();

        String results = "";
        try {
            String absolutePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            InputStream inputStream = new FileInputStream(new File(absolutePath + "/celebrities.json"));

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            results = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        System.out.println(results);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Type type = new TypeToken<List<Celebrity>>() {
        }.getType();
        List<Celebrity> celebrities = gson.fromJson(results, type);
        LocalDataSource dataSource = new LocalDataSource(this);
        for (Celebrity c : celebrities)
            dataSource.saveCelebrity(c);

        refresh();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Uri uri = data.getData();

                String results="";
                try {
                    //String absolutePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                    //assert uri != null;
                    InputStream inputStream = new FileInputStream(new File(uri.getPath()));

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ( (receiveString = bufferedReader.readLine()) != null ) {
                        stringBuilder.append(receiveString);
                    }
                    inputStream.close();
                    results = stringBuilder.toString();
                } catch (FileNotFoundException e) {
                    Log.e("login activity", "File not found: " + e.toString());
                } catch (IOException e) {
                    Log.e("login activity", "Can not read file: " + e.toString());
                }
                System.out.println(results);

            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*");      //all files
        //intent.setType("text/json");   //JSON files only
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 1);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }*/

}
