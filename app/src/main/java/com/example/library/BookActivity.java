package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    //Setting the key as a constant
    public static final String BOOK_ID_KEY = "bookID";
    private TextView nameText, authorTxt, pagesText, descriptionText, txtDescription, txtBookName, txtAuthorName, txtPages;
    private ImageView imgBook;
    private Button btnAddToCurrentlyReading, btnAddToWantToRead, btnAddToAlreadyRead, btnAddToFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        //Initializing all the components
        initViews();
        //Getting the key of the book sent by the book RecyclerView
        Intent intent = getIntent();
        if (null != intent) {
            int bookID = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookID != -1) {
                //Stroring the book in 'incoming book'
                Book incomingBook = Utils.getInstance(this).getBookByID(bookID);
                if (null != incomingBook) {
                    //The book will be given each of its attributes
                    setData(incomingBook);
                    //These function are to check if the book is already in the activity
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                }
            }
        }


    }

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(BookActivity.this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for (Book b : alreadyReadBooks) {
            if (b.getID() == book.getID()) {
                existInAlreadyReadBooks = true;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(BookActivity.this).getWantToReadBooks();
        boolean existInWantToReadBooks = false;
        for (Book b : wantToReadBooks) {
            if (b.getID() == book.getID()) {
                existInWantToReadBooks = true;
            }
        }
        if (existInWantToReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        } else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavoriteBooks(Book book) {
        ArrayList<Book> FavoriteBooks = Utils.getInstance(BookActivity.this).getFavoriteBooks();
        boolean existInFavoriteBooks = false;
        for (Book b : FavoriteBooks) {
            if (b.getID() == book.getID()) {
                existInFavoriteBooks = true;
            }
        }
        if (existInFavoriteBooks) {
            btnAddToFavorites.setEnabled(false);
        } else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavorites(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(Book book) {
        ArrayList<Book> CurrentlyReadingBooks = Utils.getInstance(BookActivity.this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b : CurrentlyReadingBooks) {
            if (b.getID() == book.getID()) {
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtDescription.setText(book.getLongDesc());
        txtPages.setText(String.valueOf(book.getPages()));
        Glide.with(this).asBitmap().load(book.getImageURL()).into(imgBook);
    }

    private void initViews() {
        nameText = findViewById(R.id.nameText);
        authorTxt = findViewById(R.id.authorTxt);
        pagesText = findViewById(R.id.pagesText);
        descriptionText = findViewById(R.id.descriptionText);
        txtDescription = findViewById(R.id.txtDescription);
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtPages = findViewById(R.id.txtPages);

        imgBook = findViewById(R.id.imgBook);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
    }
}