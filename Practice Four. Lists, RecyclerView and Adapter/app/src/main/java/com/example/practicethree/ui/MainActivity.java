package com.example.practicethree.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicethree.objects.Image;
import com.example.practicethree.R;
import com.example.practicethree.objects.RecyclerObject;
import com.example.practicethree.objects.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler);

        ArrayList<RecyclerObject> recyclerObjects = new ArrayList<>();

        recyclerObjects.add(new Text("Sample Text"));
        recyclerObjects.add(new Image(R.drawable.ic_launcher_background));

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MainAdapter(recyclerObjects);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_image) {
            adapter.addImage(new Image(R.drawable.ic_launcher_background));
            recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
            return true;
        } else if (id == R.id.action_add_text) {
            adapter.addText(new Text("Added text"));
            recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}