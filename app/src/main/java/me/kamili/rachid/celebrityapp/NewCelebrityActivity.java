package me.kamili.rachid.celebrityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import me.kamili.rachid.celebrityapp.adapter.CelebrityAdapter;
import me.kamili.rachid.celebrityapp.data.LocalDataSource;
import me.kamili.rachid.celebrityapp.model.Celebrity;

public class NewCelebrityActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etOccupation;
    private CheckBox cbFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_celebrity);

        initToolbar();
        initInputs();
    }

    private void initInputs() {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etOccupation = findViewById(R.id.etOccupation);
        cbFavorite = findViewById(R.id.cbFavorite);
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
                addCelebrity();
                return true;
        }

        return true;
    }

    private void addCelebrity() {

        LocalDataSource dataSource = new LocalDataSource(this);

        Celebrity celebrity = new Celebrity(
                etFirstName.getText().toString(),
                etLastName.getText().toString(),
                etOccupation.getText().toString(),
                cbFavorite.isChecked()
        );

        long rowNumber = dataSource.saveCelebrity(celebrity);
        celebrity.setRowId(rowNumber);

        CelebrityAdapter.mDataSource.add(celebrity);

        if(rowNumber>-1)
            finish();
        else
            Toast.makeText(this, String.valueOf(rowNumber), Toast.LENGTH_SHORT).show();

    }
}
