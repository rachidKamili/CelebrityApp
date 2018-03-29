package me.kamili.rachid.celebrityapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import me.kamili.rachid.celebrityapp.data.LocalDataSource;
import me.kamili.rachid.celebrityapp.model.Celebrity;

public class CelebritiesActivity extends AppCompatActivity {

    private ListView lvCelebrities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities);

        lvCelebrities = findViewById(R.id.lvCelebrities);
//        final ArrayList<Celebrity> celebrityList = Celebrity.getRecipesFromFile("Celebrity.json", this);
        final ArrayList<Celebrity> celebrityList=new ArrayList<>();

        celebrityList.add(new Celebrity((long) 1,"Margot", "Robbie","Actress"));
        celebrityList.add(new Celebrity((long) 2,"Adam", "Driver","Actor"));
        celebrityList.add(new Celebrity((long) 3,"Kate", "McKinnon","Comedian"));
        celebrityList.add(new Celebrity((long) 4,"Lucy", "Liu","Artist"));
        celebrityList.add(new Celebrity((long) 5,"Ellen", "DeGeneres","Comedian"));
        celebrityList.add(new Celebrity((long) 6,"Jim", "Parsons","Actor",true));
        celebrityList.add(new Celebrity((long) 7,"Donald", "Trump","Politician"));
        celebrityList.add(new Celebrity((long) 8,"Kanye", "West","Singer"));
        celebrityList.add(new Celebrity((long) 9,"Taylor", "Swift","Singer"));

        CelebrityAdapter adapter = new CelebrityAdapter(this, celebrityList);
        lvCelebrities.setAdapter(adapter);

//        lvCelebrities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "you clicked" + i, Toast.LENGTH_LONG).show();
//            }
//        });

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
