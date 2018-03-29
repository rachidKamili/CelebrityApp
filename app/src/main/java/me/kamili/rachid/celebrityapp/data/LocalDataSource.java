package me.kamili.rachid.celebrityapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import me.kamili.rachid.celebrityapp.adapter.CelebrityAdapter;
import me.kamili.rachid.celebrityapp.model.Celebrity;

/**
 * Created by Admin on 3/28/2018.
 */

public class LocalDataSource extends SQLiteOpenHelper {

    public LocalDataSource(Context context) {
        super(context, LocalDataContract.NAME, null, LocalDataContract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LocalDataContract.CREATE_CELEBRITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveCelebrity(Celebrity celebrity) {

        SQLiteDatabase database = getWritableDatabase();

        //create the content value for saving the object
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDataContract.Celebrity.FIRST_NAME, celebrity.getFirstName());
        contentValues.put(LocalDataContract.Celebrity.LAST_NAME, celebrity.getLastName());
        contentValues.put(LocalDataContract.Celebrity.OCCUPATION, celebrity.getOccupation());
        contentValues.put(LocalDataContract.Celebrity.ISFAVORITE, celebrity.getFavorite()?1:0);

        //insert the object in the table
        long rowNumber = database.insert(LocalDataContract.Celebrity.TABLE
                , null, contentValues);


        database.close();
        return rowNumber;
    }

    public long editCelebrityByRowID(Celebrity celebrity){
        SQLiteDatabase database = getWritableDatabase();

        //create the content value for saving the object
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDataContract.Celebrity.FIRST_NAME, celebrity.getFirstName());
        contentValues.put(LocalDataContract.Celebrity.LAST_NAME, celebrity.getLastName());
        contentValues.put(LocalDataContract.Celebrity.OCCUPATION, celebrity.getOccupation());
        contentValues.put(LocalDataContract.Celebrity.ISFAVORITE, celebrity.getFavorite()?1:0);

        long rowNumber = database.update(
                LocalDataContract.Celebrity.TABLE,
                contentValues,
                LocalDataContract.Celebrity.KEY_ID+"="+celebrity.getRowId(),
                null
                );
        System.out.println(rowNumber+"irfeioufhe");
        System.out.println(CelebrityAdapter.mDataSource);
        return rowNumber;
    }

    public List<Celebrity> getCelebrities() {

        SQLiteDatabase database = getWritableDatabase();
        List<Celebrity> celebrities = new ArrayList<>();
        Cursor cursor = database.rawQuery(LocalDataContract.GET_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                Celebrity celebrity = new Celebrity(
                    cursor.getLong(cursor.getColumnIndexOrThrow(LocalDataContract.Celebrity.KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(LocalDataContract.Celebrity.FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndex(LocalDataContract.Celebrity.LAST_NAME)),
                    cursor.getString(cursor.getColumnIndex(LocalDataContract.Celebrity.OCCUPATION)),
                    cursor.getInt(cursor.getColumnIndex(LocalDataContract.Celebrity.ISFAVORITE)) == 1
                );

                // TODO: 3/29/2018 11111
                //celebrity.setRowId(cursor.getLong(cursor.getColumnIndex()));
                celebrities.add(celebrity);
            } while (cursor.moveToNext());
        }

        database.close();
        return celebrities;
    }


}
