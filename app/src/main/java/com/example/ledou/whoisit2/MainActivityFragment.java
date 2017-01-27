package com.example.ledou.whoisit2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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
import butterknife.OnClick;

import android.content.CursorLoader;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by ledou on 22/11/2016.
 */

public class MainActivityFragment extends Fragment {
    public static final String TAG = "MainActivityFragment";

    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;
    public TextView cardName;

    private PersoonBeschrijvingDataSource dataSource;

    private ArrayList<PersoonBeschrijving> persoonBeschrijvings;

    private int currentIndex = 0;

    protected RecyclerView.LayoutManager layoutManager;

    public static MainOnclickListener mainOnClickListener;

    private DatabaseProvider dp = new DatabaseProvider();
    private Activity activity;

    public MainActivityFragment(){}

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dataSource = new PersoonBeschrijvingDataSource(this.getContext());
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("counter", 0);
            dataSource.open();
            persoonBeschrijvings = dataSource.getPersoonbeschrijvingen();
            dataSource.close();
        }
        else {


            dataSource.open();
            dataSource.writePersoonBeschrijvingenToDatabase();
            persoonBeschrijvings = dataSource.getPersoonbeschrijvingen();
            dataSource.close();
        }
        mainOnClickListener = new MainOnclickListener(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        activity = getActivity();
        final MainAdapter adapter = new MainAdapter(persoonBeschrijvings, activity);
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
                    recyclerView.removeViewAt(viewHolder.getAdapterPosition());

                    if (!persoonBeschrijvings.isEmpty()){
                        Toast.makeText(rootView.getContext(), "Person "
                                + personSwipedName + " removed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(rootView.getContext(), "Person "
                                + personSwipedName + " removed, this was the last person in the game. Restart if you want to play again.", Toast.LENGTH_LONG).show();
                    }
                }
                adapter.notifyDataSetChanged();


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return rootView;
    }

    public void restartGame(){
        dataSource.writePersoonBeschrijvingenToDatabase();
        persoonBeschrijvings = dataSource.getPersoonbeschrijvingen();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final MainAdapter adapter = new MainAdapter(persoonBeschrijvings, activity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putInt("counter", currentIndex);
    }

    private class MainOnclickListener implements View.OnClickListener{

        private final Context context;

        public MainOnclickListener(Context context) {
            this.context = context;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            currentIndex = recyclerView.getChildAdapterPosition(v);
            new AlertDialog.Builder(context)
                    .setTitle("Information " + persoonBeschrijvings.get(currentIndex).getNaam())
                    .setMessage(persoonBeschrijvings.get(currentIndex).getBeschrijving())
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();

        }
    }


}
