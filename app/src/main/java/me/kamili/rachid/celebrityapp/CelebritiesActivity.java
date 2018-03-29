package me.kamili.rachid.celebrityapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import me.kamili.rachid.celebrityapp.adapter.CelebrityAdapter;
import me.kamili.rachid.celebrityapp.data.LocalDataSource;
import me.kamili.rachid.celebrityapp.model.Celebrity;

public class CelebritiesActivity extends AppCompatActivity {

    private ListView lvCelebrities;
    private FloatingActionButton showFavoritesBtn;
    private FloatingActionButton hideFavoritesBtn;
    public static boolean fav=false;
    private CelebrityAdapter adapter;

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

    public void refresh(){
        LocalDataSource dataSource = new LocalDataSource(this);

        List<Celebrity> celebrityList = dataSource.getCelebrities();

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
        fav=true;
        adapter.notifyDataSetChanged();
    }

    public void hideFavorites(View view) {
        showFavoritesBtn.setVisibility(View.VISIBLE);
        hideFavoritesBtn.setVisibility(View.GONE);
        fav=false;
        adapter.notifyDataSetChanged();
    }
}
