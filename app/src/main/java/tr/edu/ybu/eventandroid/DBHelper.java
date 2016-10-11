package tr.edu.ybu.eventandroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by melih on 22.12.2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ybumobil";
    public static final String TABLE_WIDGETS = "widgets";
    public static final String TABLE_SOURCES = "sources";
    public static final String TABLE_WIDGET_SOURCES = "widget_sources";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAllTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String deleteQuery = "" +
                "DROP TABLE IF EXISTS " + TABLE_WIDGETS + ";\n" +
                "DROP TABLE IF EXISTS " + TABLE_WIDGET_SOURCES + ";\n" +
                "DROP TABLE IF EXISTS " + TABLE_SOURCES + ";\n";
        db.execSQL(deleteQuery);
        onCreate(db);
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        this.onUpgrade(db, 0, 1);
    }

    public String getWidgetPref(int appWidgetId) {
        final String widgetSourcesSelectQuery = "SELECT source_id FROM " + TABLE_WIDGET_SOURCES + " where widget_id=" + appWidgetId + ";";
        final String widgetIntervalSelectQuery = "SELECT widget_interval FROM " + TABLE_WIDGETS + " where widget_id=" + appWidgetId + ";";
        Cursor widgetInterval = this.getReadableDatabase().rawQuery(widgetIntervalSelectQuery, null);
        if (widgetInterval.getCount() == 0) return null;
        else widgetInterval.moveToFirst();
        Cursor widgetSources = this.getReadableDatabase().rawQuery(widgetSourcesSelectQuery, null);
        widgetSources.moveToFirst();
        StringBuilder sources = new StringBuilder();
        for (int i = 0; i < widgetSources.getCount(); i++) {
            sources.append(widgetSources.getInt(0));
            sources.append(";;;");
            widgetSources.moveToNext();
        }
        String id = String.valueOf(widgetInterval.getInt(0));
        widgetInterval.close();
        widgetSources.close();
        return id + "|||" + sources.toString();
    }

    public void saveWidgetPref(int appWidgetId, int interval, String[] sources) {
        this.createWidgetEntry(appWidgetId, interval);
        SQLiteDatabase db = getWritableDatabase();
        for (String source : sources) {
            ContentValues values = new ContentValues(2);
            values.put("widget_id", appWidgetId);
            values.put("source_id", Integer.valueOf(source));
            db.insert(TABLE_WIDGET_SOURCES, null, values);
        }
    }

    private void createWidgetEntry(int appWidgetId, int interval) {
        SQLiteDatabase db = getWritableDatabase();
        long unixTime = System.currentTimeMillis() / 1000L;
        ContentValues values = new ContentValues();
        values.put("widget_id", appWidgetId);
        values.put("widget_interval", interval);
        values.put("widget_create", unixTime);
        values.put("widget_delete", -1);
        db.insert(TABLE_WIDGETS, null, values);
    }

    public void deleteWidget(int appWidgetId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_WIDGETS, "widget_id = ?", new String[]{Integer.toString(appWidgetId)});
        db.delete(TABLE_WIDGET_SOURCES, "widget_id = ?", new String[]{Integer.toString(appWidgetId)});
    }

    public void updateSources(ArrayList<JSONObject> sources) throws JSONException {
        SQLiteDatabase db = getWritableDatabase();
        long unixTime = System.currentTimeMillis() / 1000L;
        for (JSONObject source : sources) {
            if (exists(Integer.valueOf(source.getString("sourceLink")), Records.SOURCE)) {
                continue;
            }
            ContentValues values = new ContentValues();
            values.put("source_id", source.getString("sourceLink"));
            values.put("source_name", source.getString("line1"));
            values.put("source_create", unixTime);
            db.insert(TABLE_SOURCES, null, values);
        }
    }

    public void createAllTables() {
        SQLiteDatabase db = getWritableDatabase();
        String createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_WIDGETS + " (\n" +
                "\"widget_id\" INTEGER NOT NULL,\n" +
                "\"widget_interval\" INTEGER NOT NULL,\n" +
                "\"widget_create\" INTEGER NOT NULL,\n" +
                "\"widget_delete\" INTEGER NOT NULL,\n" +
                "PRIMARY KEY (\"widget_id\") \n" +
                ");\n";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_WIDGET_SOURCES + " (\n" +
                "\"widget_sources_id\" INTEGER NOT NULL,\n" +
                "\"source_id\" INTEGER NOT NULL,\n" +
                "\"widget_id\" INTEGER NOT NULL,\n" +
                "PRIMARY KEY (\"widget_sources_id\") \n" +
                ");\n";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_SOURCES + " (\n" +
                "\"source_id\" INTEGER NOT NULL,\n" +
                "\"source_name\" TEXT(255) NOT NULL,\n" +
                "\"source_create\" INTEGER NOT NULL,\n" +
                "PRIMARY KEY (\"source_id\") \n" +
                ");\n";
        db.execSQL(createQuery);
        Welcome.log("Created the tables.");
    }

    public boolean exists(int id, Records type) {
        final String widgetIntervalSelectQuery = "SELECT " + type.field + " FROM " + type.table + " where " + type.field + "=" + id + ";";
        Cursor widgetInterval = this.getReadableDatabase().rawQuery(widgetIntervalSelectQuery, null);
        if (widgetInterval.getCount() != 0) {
            widgetInterval.close();
            return true;
        } else {
            widgetInterval.close();
            return false;
        }
    }

    enum Records {
        WIDGET(TABLE_WIDGETS, "widget_id"), SOURCE(TABLE_SOURCES, "source_id");
        public String table;
        public String field;

        Records(String table, String field) {
            this.table = table;
            this.field = field;
        }
    }
}
