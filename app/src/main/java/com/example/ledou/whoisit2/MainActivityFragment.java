package com.example.ledou.whoisit2;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.content.CursorLoader;
import android.widget.Toast;

/**
 * Created by ledou on 22/11/2016.
 */

public class MainActivityFragment extends Fragment {
    public static final String TAG = "MainActivityFragment";

    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;

    private PersoonBeschrijvingDataSource dataSource;

    private ArrayList<PersoonBeschrijving> persoonBeschrijvings;

    private int currentIndex;

    protected RecyclerView.LayoutManager layoutManager;

    public static MainOnclickListener mainOnClickListener;

    private DatabaseProvider dp = new DatabaseProvider();

    public MainActivityFragment(){}

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dataSource = new PersoonBeschrijvingDataSource(this.getContext());
        dataSource.open();
        dataSource.writePersoonBeschrijvingenToDatabase();
        persoonBeschrijvings = dataSource.getPersoonbeschrijvingen();
        dataSource.close();
        mainOnClickListener = new MainOnclickListener(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final MainAdapter adapter = new MainAdapter(persoonBeschrijvings);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                String personSwipedName = "";
                //Remove swiped item from list and notify the RecyclerView
                if(swipeDir==ItemTouchHelper.LEFT)
                {
                    personSwipedName = persoonBeschrijvings.get(viewHolder.getAdapterPosition()).getNaam();
                    dataSource.open();
                    dataSource.removePersoonBeschrijving(persoonBeschrijvings.get(viewHolder.getAdapterPosition()));
                    dataSource.close();
                    persoonBeschrijvings.remove(viewHolder.getAdapterPosition());


                    if (!persoonBeschrijvings.isEmpty()){
                        Toast.makeText(rootView.getContext(), "Person "
                                + personSwipedName + " removed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(rootView.getContext(), "Person "
                                + personSwipedName + " removed, this was the last person in the game. Restart if you want to play again.", Toast.LENGTH_LONG).show();
                    }
                }
                adapter.notifyItemRemoved(viewHolder.getLayoutPosition());


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return rootView;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MainOnclickListener implements View.OnClickListener{

        private final Context context;

        public MainOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            currentIndex = recyclerView.getChildAdapterPosition(v);
            Log.i(TAG,"Clicked on : "+currentIndex);
        }
    }


}
