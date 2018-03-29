package me.kamili.rachid.celebrityapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.kamili.rachid.celebrityapp.data.LocalDataSource;
import me.kamili.rachid.celebrityapp.model.Celebrity;

public class CelebritiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities);
    }

    public void addCelebrity(View view) {
//        database.saveCelebrity(new Celebrity("Margot", "Robbie","Actress"));
//        database.saveCelebrity(new Celebrity("Adam", "Driver","Actor"));
//        database.saveCelebrity(new Celebrity("Kate", "McKinnon","Comedian"));
//        database.saveCelebrity(new Celebrity("Lucy", "Liu","Artist"));
//        database.saveCelebrity(new Celebrity("Ellen", "DeGeneres","Comedian"));
//        database.saveCelebrity(new Celebrity("Jim", "Parsons","Actor"));
//        database.saveCelebrity(new Celebrity("Donald", "Trump","Politician"));
//        database.saveCelebrity(new Celebrity("Kanye", "West","Singer"));
//        database.saveCelebrity(new Celebrity("Taylor", "Swift","Singer"));

        Intent intent = new Intent(this, NewCelebrityActivity.class);
        startActivity(intent);
    }
}
