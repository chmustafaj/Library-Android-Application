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

public class WantToReadBooksActivity extends AppCompatActivity implements BookRecyclerViewAdapter.RemoveFromWantToRead {
    //Creating the function for the callBack Interface
    @Override
    public void removeFromWantToReadFunction(Book book) {
        if (Utils.getInstance(WantToReadBooksActivity.this).removeFromWantToRead(book)) {
            Toast.makeText(this, "Book removed", Toast.LENGTH_SHORT).show();
            adapter.setBooks(Utils.getInstance(WantToReadBooksActivity.this).getWantToReadBooks());
            empty.setVisibility(View.VISIBLE);


        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, "wantToRead");
    TextView empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empty = findViewById(R.id.empty);

        RecyclerView recyclerView = findViewById(R.id.bookRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getWantToReadBooks());
        if (Utils.getInstance(WantToReadBooksActivity.this).getWantToReadBooks().isEmpty()) {
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