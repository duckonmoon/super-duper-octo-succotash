package com.example.rkrit.test.tables;

import android.provider.BaseColumns;

/**
 * Created by rkrit on 06.10.17.
 */

public final class Table {
    private Table(){}

    public static class Tag implements BaseColumns{
        public static final String TABLE_NAME = "tag";
        public static final String LATITUDE = "latitude";
        public static final String LONGTITUDE = "longtitude";
    }
}
