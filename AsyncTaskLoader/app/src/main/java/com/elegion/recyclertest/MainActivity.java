package com.elegion.recyclertest;

import android.app.LoaderManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.OnItemClickListener, LoaderManager.LoaderCallbacks<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecyclerFragment.newInstance())
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onItemClick(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        getLoaderManager().restartLoader(1, bundle, this)
                .forceLoad();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new Loader(this, args.getString("id"));
    }

    @Override
    public void onLoadFinished(android.content.Loader loader, String data) {
        if (data != null) {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + data)));
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader loader) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cancel_loading) {
            try {
                getLoaderManager().getLoader(1).cancelLoad();
                return true;
            } catch (NullPointerException e) {
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
