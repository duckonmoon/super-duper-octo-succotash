package com.example.rkrit.test.fordb;

import android.content.Context;
import android.database.Cursor;

import com.example.rkrit.test.entity.TagsForMaps;
import com.example.rkrit.test.tables.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkrit on 06.10.17.
 */

public final class DBService {

    private static final String [] projetion = {
            Table.Tag._ID,
            Table.Tag.LATITUDE,
            Table.Tag.LONGTITUDE
    };
    private DBService(){}
    public static Cursor getAllEntities(Context context){
        TestDBHelper testDBHelper = new TestDBHelper(context);
        return testDBHelper.getWritableDatabase().query(
                Table.Tag.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
                );
    }

    public static ArrayList<TagsForMaps> getAllTags(Cursor cursor)
    {
        ArrayList<TagsForMaps> tagsForMapsArrayList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            TagsForMaps tagsForMaps = new TagsForMaps();
            tagsForMaps.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Table.Tag._ID)));
            tagsForMaps.setLatitude(cursor.getLong(cursor.getColumnIndexOrThrow(Table.Tag.LATITUDE)));
            tagsForMaps.setLongitude(cursor.getLong(cursor.getColumnIndexOrThrow(Table.Tag.LONGTITUDE)));
            tagsForMapsArrayList.add(tagsForMaps);
        }
        return tagsForMapsArrayList;
    }
}
