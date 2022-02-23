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

public class CurrentlyReadingBooksActivity extends AppCompatActivity implements BookRecyclerViewAdapter.RemoveFromCurrentlyReading {
    @Override
    public void removeFromCurrentlyReadingFunction(Book book) {
        if (Utils.getInstance(CurrentlyReadingBooksActivity.this).removeFromCurrentlyReading(book)) {
            Toast.makeText(this, "Book removed", Toast.LENGTH_SHORT).show();
            adapter.setBooks(Utils.getInstance(CurrentlyReadingBooksActivity.this).getCurrentlyReadingBooks());
            empty.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, "currentlyReading");
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_books);
        //Setting up top back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empty = findViewById(R.id.empty);

        RecyclerView recyclerView = findViewById(R.id.bookRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (Utils.getInstance(CurrentlyReadingBooksActivity.this).getCurrentlyReadingBooks().isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.INVISIBLE);
        }


        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
    }

    //Going back if the top back button is pressed
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