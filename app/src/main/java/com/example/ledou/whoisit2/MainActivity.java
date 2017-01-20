package com.example.ledou.whoisit2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    //private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        //currentFragment = getFragmentManager().findFragmentById(R.id.listFragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restart:
                Toast.makeText(getApplicationContext(), "Restarting game", Toast.LENGTH_LONG).show();
                restartGame();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void restartGame()
    {
        /*PersoonBeschrijvingDataSource dataSource = new PersoonBeschrijvingDataSource(getApplicationContext());
        dataSource.writePersoonBeschrijvingenToDatabase();
        MainAdapter adapter = new MainAdapter(dataSource.getPersoonbeschrijvingen());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);*/
        MainActivityFragment fragment = new MainActivityFragment();

        //((MainActivityFragment) currentFragment).restartGame();
    }
}
