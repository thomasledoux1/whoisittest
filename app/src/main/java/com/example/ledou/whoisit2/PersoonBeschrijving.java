package com.example.ledou.whoisit2;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ledou on 22/11/2016.
 */

public final class PersoonBeschrijving {

    public static final String AUTHORITY = "com.example.ledou.whoisit2.PersoonBeschrijving";

    private long id;
    private String naam, beschrijving;
    private int foto;

    public PersoonBeschrijving(){}

    public PersoonBeschrijving(int foto, String beschrijving, String naam)
    {
        this.foto= foto;
        this.beschrijving = beschrijving;
        this.naam = naam;
    }

    public int getFoto() {
        return foto;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public long getId(){
        return id;
    }

    public void setFoto(int foto){
        this.foto = foto;
    }

    public void setNaam(String naam){
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving){
        this.beschrijving = beschrijving;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public static final class PersoonBeschrijvingColumns implements BaseColumns{
        private PersoonBeschrijvingColumns(){}

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/personen");
        public static final String DEFAULT_SORT_ORDER = "modified DESC";
        public static final String foto = "foto";
        public static final String naam = "naam";

        public static final String beschrijving = "beschrijving";

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.note";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.note";

    }



}
