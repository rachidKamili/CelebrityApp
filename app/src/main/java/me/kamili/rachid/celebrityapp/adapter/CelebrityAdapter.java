package me.kamili.rachid.celebrityapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.kamili.rachid.celebrityapp.CelebritiesActivity;
import me.kamili.rachid.celebrityapp.EditCelebrityActivity;
import me.kamili.rachid.celebrityapp.R;
import me.kamili.rachid.celebrityapp.data.LocalDataSource;
import me.kamili.rachid.celebrityapp.model.Celebrity;

/**
 * Created by Admin on 3/28/2018.
 */

public class CelebrityAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    public static List<Celebrity> mDataSource;

    public CelebrityAdapter(Context context, List<Celebrity> celebrities) {
        mContext = context;
        mDataSource = celebrities;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.celebrity_item, viewGroup, false);

        final Celebrity celebrity = (Celebrity) getItem(i);

        TextView firstName = rowView.findViewById(R.id.firstName);
        firstName.setText(celebrity.getFirstName());

        TextView lastName = rowView.findViewById(R.id.lastName);
        lastName.setText(celebrity.getLastName());

        TextView occupation = rowView.findViewById(R.id.occupation);
        occupation.setText(celebrity.getOccupation());

        final ImageButton isFav = rowView.findViewById(R.id.isFav);
        if (celebrity.getFavorite())
            isFav.setImageResource(R.drawable.ic_favorite_red_24dp);
        else
            isFav.setImageResource(R.drawable.ic_favorite_border_red_24dp);
        isFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            celebrity.setFavorite(!celebrity.getFavorite());
            if (celebrity.getFavorite())
                isFav.setImageResource(R.drawable.ic_favorite_red_24dp);
            else
                isFav.setImageResource(R.drawable.ic_favorite_border_red_24dp);

            LocalDataSource dataSource = new LocalDataSource(mContext);
            long rowNumber = dataSource.editCelebrityByRowID(celebrity);
            }
        });

        rowView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, EditCelebrityActivity.class);
                i.putExtra("toBeChanged",celebrity);
                mContext.startActivity(i);
            }
        });

        return rowView;
    }


}
