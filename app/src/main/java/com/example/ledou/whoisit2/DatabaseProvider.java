package com.example.ledou.whoisit2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ledou on 22/11/2016.
 */

public class DatabaseProvider extends ContentProvider{

    private static final String TAG = "DatabaseProvider";

    private static final String DATABASE_NAME = "persoondata.db";
    private static final int DATABASE_VERSION =2;
    private static final String PERSOON_TABLE_NAME = "persoon";

    public static final String NAAM = "naam";
    public static final String FOTO = "foto";
    public static final String BESCHRIJVING = "beschrijving";

    static final String PROVIDER_NAME = "com.example.ledou.whoisit2.DatabaseProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/personen";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static final int PERSONEN = 1;
    private static final int PERSOON_ID = 2;

    private static SQLiteDatabase db;
    private static HashMap<String, String> sPersonenProjectionMap;

    protected static final UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(PROVIDER_NAME, "personen", PERSONEN);
        sUriMatcher.addURI(PROVIDER_NAME, "personen/#", PERSOON_ID);

        sPersonenProjectionMap = new HashMap<String, String>();
        sPersonenProjectionMap.put(PersoonBeschrijving.PersoonBeschrijvingColumns._ID, PersoonBeschrijving.PersoonBeschrijvingColumns._ID);
        sPersonenProjectionMap.put(PersoonBeschrijving.PersoonBeschrijvingColumns.beschrijving, PersoonBeschrijving.PersoonBeschrijvingColumns.beschrijving);
        sPersonenProjectionMap.put(PersoonBeschrijving.PersoonBeschrijvingColumns.foto, PersoonBeschrijving.PersoonBeschrijvingColumns.foto);
        sPersonenProjectionMap.put(PersoonBeschrijving.PersoonBeschrijvingColumns.naam, PersoonBeschrijving.PersoonBeschrijvingColumns.naam);
        sPersonenProjectionMap.put(PersoonBeschrijving.PersoonBeschrijvingColumns.CREATED_DATE, PersoonBeschrijving.PersoonBeschrijvingColumns.CREATED_DATE);
        sPersonenProjectionMap.put(PersoonBeschrijving.PersoonBeschrijvingColumns.MODIFIED_DATE, PersoonBeschrijving.PersoonBeschrijvingColumns.MODIFIED_DATE);

    }




    private static class DatabaseHelper extends SQLiteOpenHelper {


        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + PERSOON_TABLE_NAME + " ("
            + PersoonBeschrijving.PersoonBeschrijvingColumns._ID + " INTEGER PRIMARY KEY,"
            + PersoonBeschrijving.PersoonBeschrijvingColumns.foto + " INTEGER,"
            + PersoonBeschrijving.PersoonBeschrijvingColumns.beschrijving + " TEXT,"
            + PersoonBeschrijving.PersoonBeschrijvingColumns.naam + " TEXT,"
            + PersoonBeschrijving.PersoonBeschrijvingColumns.CREATED_DATE + " INTEGER,"
            + PersoonBeschrijving.PersoonBeschrijvingColumns.MODIFIED_DATE + " INTEGER" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " +
                    PERSOON_TABLE_NAME);
            onCreate(db);
        }


    }

    private DatabaseHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        db = mOpenHelper.getWritableDatabase();
        return (db==null)?false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(PERSOON_TABLE_NAME);
        switch(sUriMatcher.match(uri))
        {
            case PERSONEN :
                qb.setProjectionMap(sPersonenProjectionMap);
                break;
            case PERSOON_ID :
                qb.setProjectionMap(sPersonenProjectionMap);
                qb.appendWhere(PersoonBeschrijving.PersoonBeschrijvingColumns._ID + "=" + uri.getPathSegments().get(1));
                break;
            default :
                throw new IllegalArgumentException("Unknwon URI " + uri);
        }

        String orderBy;
        if(TextUtils.isEmpty(sortOrder))
        {
            orderBy = PersoonBeschrijving.PersoonBeschrijvingColumns.DEFAULT_SORT_ORDER;

        }
        else{
            orderBy = sortOrder;
        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch(sUriMatcher.match(uri))
        {
            case PERSONEN :
                return PersoonBeschrijving.PersoonBeschrijvingColumns.CONTENT_TYPE;
            case PERSOON_ID :
                return PersoonBeschrijving.PersoonBeschrijvingColumns.CONTENT_ITEM_TYPE;
            default :
                throw new IllegalArgumentException("Uknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        if(sUriMatcher.match(uri) != PERSONEN)
        {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues values;
        if(initialValues!=null)
        {
            values = new ContentValues(initialValues);
        }
        else{
            values = new ContentValues();
        }
        Long now = Long.valueOf(System.currentTimeMillis());

        if(values.containsKey(PersoonBeschrijving.PersoonBeschrijvingColumns.CREATED_DATE)==false)
        {
            values.put(PersoonBeschrijving.PersoonBeschrijvingColumns.CREATED_DATE, now);
        }

        if(values.containsKey(PersoonBeschrijving.PersoonBeschrijvingColumns.MODIFIED_DATE)==false)
        {
            values.put(PersoonBeschrijving.PersoonBeschrijvingColumns.MODIFIED_DATE, now);
        }

        if(values.containsKey(PersoonBeschrijving.PersoonBeschrijvingColumns.beschrijving)==false)
        {
            Resources r = Resources.getSystem().getSystem();
            values.put(PersoonBeschrijving.PersoonBeschrijvingColumns.beschrijving, "Geen beschrijving");
        }

        if(values.containsKey(PersoonBeschrijving.PersoonBeschrijvingColumns.foto) == false)
        {
            values.put(PersoonBeschrijving.PersoonBeschrijvingColumns.foto, 0);
        }

        if(values.containsKey(PersoonBeschrijving.PersoonBeschrijvingColumns.naam) == false)
        {
            values.put(PersoonBeschrijving.PersoonBeschrijvingColumns.naam, "Geen naam");
        }
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long rowId = db.insert(PERSOON_TABLE_NAME, PersoonBeschrijving.PersoonBeschrijvingColumns.beschrijving, values);
        if(rowId>0)
        {
            Uri noteUri = ContentUris.withAppendedId(PersoonBeschrijving.PersoonBeschrijvingColumns.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);

            return noteUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch(sUriMatcher.match(uri))
        {
            case PERSONEN :
                count = db.delete(PERSOON_TABLE_NAME, where, whereArgs);
                break;
            case PERSOON_ID :
                String noteId = uri.getPathSegments().get(1);
                count = db.delete(PERSOON_TABLE_NAME, PersoonBeschrijving.PersoonBeschrijvingColumns._ID + "=" + noteId
                + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
                break;
            default :
                throw new IllegalArgumentException(("Unknown URI " + uri));


        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase  db = mOpenHelper.getWritableDatabase();
        int count;
        switch(sUriMatcher.match(uri))
        {
            case PERSONEN :
                count = db.update(PERSOON_TABLE_NAME, values, where, whereArgs);
                break;
            case PERSOON_ID :
                String noteId = uri.getPathSegments().get(1);
                count = db.update(PERSOON_TABLE_NAME, values, PersoonBeschrijving.PersoonBeschrijvingColumns._ID + "=" + noteId
                + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
                break;
            default :
                throw new IllegalArgumentException("Unknown uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    public void fillDatabase(){
        ArrayList<PersoonBeschrijving> persoonBeschrijvingen = new ArrayList<>();
        ContentValues values = new ContentValues();
        for(int i = 0; i<DataClass.persoonDetails.length;i++)
        {
            values.put(NAAM, DataClass.persoonNamen[i]);
            values.put(FOTO, DataClass.persoonFoto[i]);
            values.put(BESCHRIJVING, DataClass.persoonDetails[i]);
        }
        insert(CONTENT_URI, values);
    }



}
