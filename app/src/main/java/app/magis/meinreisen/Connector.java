package app.magis.meinreisen;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class Connector {
    private static final String DB_NAME = "meinreisen.db";
    SQLiteDatabase database;
    DBOpenHelper DBOpenHelper;

    public Connector(Context context){
        DBOpenHelper = new DBOpenHelper(context, DB_NAME, null, 1);
    }

    public void open() throws SQLException {
        database = DBOpenHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null)
            database.close();
    }

    public void open_level(int level){
        ContentValues Levels = new ContentValues();
        Levels.put("status", 1);
        open();
        database.update("levels", Levels, "level = " + level, null);
        close();
    }
}
