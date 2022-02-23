package com.example.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AlreadyReadBookActivity extends AppCompatActivity implements BookRecyclerViewAdapter.RemoveFromAlreadyRead {
    @Override
    public void removeFromAlreadyReadFunction(Book book) {
        if (Utils.getInstance(AlreadyReadBookActivity.this).removeFromAlreadyRead(book)) {
            Toast.makeText(this, "Book removed", Toast.LENGTH_SHORT).show();
            adapter.setBooks(Utils.getInstance(AlreadyReadBookActivity.this).getAlreadyReadBooks());
            empty.setVisibility(View.VISIBLE);


        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, "alreadyRead");
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empty= findViewById(R.id.empty);
        RecyclerView recyclerView = findViewById(R.id.bookRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
        if (Utils.getInstance(AlreadyReadBookActivity.this).getAlreadyReadBooks().isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}