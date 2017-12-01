package app.theducksneezes.com.ldc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "eventTable";
    private static final String COL1 = "name";
    private static final String COL2 = "desc";
    private static final String COL3 = "date";
    private static final String COL4 = "startMin";
    private static final String COL5 = "startHour";
    private static final String COL6 = "endMin";
    private static final String COL7 = "endHour";

    public DatabaseHelper (Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " TEXT, " +
                COL3 + " LONG, " +
                COL4 + " INT, " +
                COL5 + " INT, " +
                COL6 + " INT, " +
                COL7 + " INT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTable = "DROP IF TABLE EXISTS";
        db.execSQL(dropTable + TABLE_NAME);
        onCreate(db);
    }

    public void addData (String name, String desc, long date, int startMin, int startHour, int endMin, int endHour) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, desc);
        contentValues.put(COL3, date);
        contentValues.put(COL4, startMin);
        contentValues.put(COL5, startHour);
        contentValues.put(COL6, endMin);
        contentValues.put(COL7, endHour);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public void deleteEvent(String name, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "name = ? AND desc = ?", new String[] {name, desc});
    }
}
