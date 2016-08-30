package com.example.ienning.ncuhome.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ienning on 16-7-26.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_GRADE = "create table Grade ( id integer primary key autoincrement, "
            + "lessonName text, "
            + "score real, "
            + "credit real, "
            + "term text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GRADE);
        Toast.makeText(mContext , "test is ok", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
