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

public class FavoriteBooksActivity extends AppCompatActivity implements BookRecyclerViewAdapter.RemoveFromFavorites {
    @Override
    public void removeFromFavoritesFunction(Book book) {
        if (Utils.getInstance(FavoriteBooksActivity.this).removeFromFavorites(book)) {
            Toast.makeText(this, "Book removed", Toast.LENGTH_SHORT).show();
            adapter.setBooks(Utils.getInstance(FavoriteBooksActivity.this).getFavoriteBooks());
            empty.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, "favoriteBooks");
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empty = findViewById(R.id.empty);
        if (Utils.getInstance(FavoriteBooksActivity.this).getFavoriteBooks().isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.INVISIBLE);
        }
        RecyclerView recyclerView = findViewById(R.id.bookRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getFavoriteBooks());
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