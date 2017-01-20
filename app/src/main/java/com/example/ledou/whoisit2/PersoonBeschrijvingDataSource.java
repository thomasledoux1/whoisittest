package com.example.ledou.whoisit2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ledou on 19/01/2017.
 */

public class PersoonBeschrijvingDataSource {
    private SQLiteDatabase db;
    private DatabaseProvider.DatabaseHelper dbHelper;
    private DatabaseProvider databaseProvider = new DatabaseProvider();
    private String[] allColumns = {DatabaseProvider.ID, DatabaseProvider.FOTO, DatabaseProvider.NAAM, DatabaseProvider.BESCHRIJVING};
    private List<PersoonBeschrijving> persoonBeschrijvings = new ArrayList<>();
    private Context context;

    public PersoonBeschrijvingDataSource(Context context) {
        dbHelper = new DatabaseProvider.DatabaseHelper(context);
        this.context = context;
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void writePersoonBeschrijvingenToDatabase() {
        context.getContentResolver().delete(DatabaseProvider.CONTENT_URI, null, null);
        for(int i = 0; i<DataClass.persoonDetails.length;i++)
        {
            String naam = DataClass.persoonNamen[i];
            String beschrijving = DataClass.persoonDetails[i];
            int foto = DataClass.persoonFoto[i];
            PersoonBeschrijving pb = new PersoonBeschrijving(foto, beschrijving, naam);
            createPersoonBeschrijvingInDatabase(pb);
            persoonBeschrijvings.add(pb);
        }

    }

    public void removePersoonBeschrijving(PersoonBeschrijving pb)
    {
        Uri uri = Uri.parse(DatabaseProvider.CONTENT_URI + "/" + pb.getId());
        context.getContentResolver().delete(uri, null,null);

    }

    private void createPersoonBeschrijvingInDatabase(PersoonBeschrijving pb){
        ContentValues values = new ContentValues();

        values.put(DatabaseProvider.NAAM, pb.getNaam());
        values.put(DatabaseProvider.FOTO, pb.getFoto());
        values.put(DatabaseProvider.BESCHRIJVING, pb.getBeschrijving());
        context.getContentResolver().insert(DatabaseProvider.CONTENT_URI, values);

    }

    public ArrayList<PersoonBeschrijving> getPersoonbeschrijvingen() {
        ArrayList<PersoonBeschrijving> persoonBeschrijvingen = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DatabaseProvider.CONTENT_URI, allColumns, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            PersoonBeschrijving pb = cursorToPersoonBeschrijving(cursor);
            persoonBeschrijvingen.add(pb);
            cursor.moveToNext();
        }
        cursor.close();
        return persoonBeschrijvingen;
    }

    private PersoonBeschrijving cursorToPersoonBeschrijving(Cursor cursor){
        PersoonBeschrijving pb = new PersoonBeschrijving();
        pb.setId(cursor.getLong(0));
        pb.setBeschrijving(cursor.getString(3));
        pb.setFoto(cursor.getInt(1));
        pb.setNaam(cursor.getString(2));
        return pb;
    }
}
