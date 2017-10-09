package com.example.rkrit.test.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rkrit.test.R;
import com.example.rkrit.test.entity.TagsForMaps;
import com.example.rkrit.test.fordb.DBService;
import com.example.rkrit.test.fordb.TestDBHelper;
import com.example.rkrit.test.tables.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class DBFragment extends Fragment {

    private ListView listView;
    private ArrayList<TagsForMaps> tagsForMapss;
    private Button add_Smth;
    private EditText longt;
    private EditText latit;
   // private static final  TagsForMaps [] tagsForMaps = {new TagsForMaps(2,44,55),
         //   new TagsForMaps(1,55,88),
          //  new TagsForMaps(3 , 99 , 44)};

    private OnFragmentInteractionListener mListener;

    public DBFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TestDBHelper testDBHelper = new TestDBHelper(getActivity());
        final SQLiteDatabase sqLiteDatabase = testDBHelper.getWritableDatabase();

        View view = inflater.inflate(R.layout.fragment_db, container, false);
        listView = view.findViewById(R.id.list_view);
        tagsForMapss = DBService.getAllTags(DBService.getAllEntities(getActivity()));
        /*for (int i = 0; i < tagsForMaps.length; i++)
        {
            tagsForMapss.add(tagsForMaps[i]);
            //ContentValues values = new ContentValues();
            //values.put(Table.Tag.LATITUDE,tagsForMaps[i].getLatitude());
            //values.put(Table.Tag.LONGTITUDE,tagsForMaps[i].getLongitude());

            //Log.e("Err","" + sqLiteDatabase.insert(Table.Tag.TABLE_NAME, null, values));
        }*/

        listView.setAdapter(new ArrayAdapter<TagsForMaps>(getActivity(),R.layout.row,tagsForMapss));

        add_Smth = (Button) view.findViewById(R.id.add_tag);
        longt = view.findViewById(R.id.longtitude);
        latit = view.findViewById(R.id.latitude);

        add_Smth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    ContentValues values = new ContentValues();
                    values.put(Table.Tag.LATITUDE,Double.parseDouble(latit.getText().toString()));
                    values.put(Table.Tag.LONGTITUDE,Double.parseDouble(longt.getText().toString()));

                    Log.i("Info","" + sqLiteDatabase.insert(Table.Tag.TABLE_NAME, null, values));

                    tagsForMapss = DBService.getAllTags(DBService.getAllEntities(getActivity()));
                    listView.setAdapter(new ArrayAdapter(getActivity(),R.layout.row,tagsForMapss));
                }
            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TagsForMaps tagsForMaps = (TagsForMaps) parent.getAdapter().getItem(position);
                Uri location = Uri.parse(String.format("geo: %1$f , %2$f ?z=14",tagsForMaps.getLatitude(),tagsForMaps.getLongitude()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
           // mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

    }
}
