package me.kamili.rachid.celebrityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import me.kamili.rachid.celebrityapp.data.LocalDataSource;
import me.kamili.rachid.celebrityapp.model.Celebrity;

public class EditCelebrityActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

    private EditText editFirstName;
    private EditText editLastName;
    private EditText editOccupation;
    private CheckBox editFavorite;

    private Celebrity currentCelebrity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_celebrity);

        initToolbar();
        initInputs();
        getDataFromIntent(savedInstanceState);
    }

    private void getDataFromIntent(Bundle savedInstanceState) {
        Intent intent = getIntent();
        currentCelebrity = (Celebrity) intent.getSerializableExtra("toBeChanged");
        editFirstName.setText(currentCelebrity.getFirstName());
        editLastName.setText(currentCelebrity.getLastName());
        editOccupation.setText(currentCelebrity.getOccupation());
        editFavorite.setChecked(currentCelebrity.getFavorite());
    }

    private void initInputs() {
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editOccupation = findViewById(R.id.editOccupation);
        editFavorite = findViewById(R.id.editFavorite);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addCel:
                editCelebrity();
                return true;
        }

        return true;
    }

    private void editCelebrity(){
        currentCelebrity.setFirstName(editFirstName.getText().toString());
        currentCelebrity.setLastName(editLastName.getText().toString());
        currentCelebrity.setOccupation(editOccupation.getText().toString());
        currentCelebrity.setFavorite(editFavorite.isChecked());

        LocalDataSource dataSource = new LocalDataSource(this);
        long rowNumber = dataSource.editCelebrityByRowID(currentCelebrity);
        if(rowNumber>-1)
            finish();
    }
}
