package com.example.library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static Utils instance;
    private SharedPreferences sharedPreferences;

    //Keeping the keys as constants
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";
    private static final String WANT_TO_READ_BOOKS_KEY = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";

    //Using gson and SharedPrefrences to save the books
    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("data_base", Context.MODE_PRIVATE);
        //The Utils will be a singleton class. There will be only one instance of AllBooks
        if (null == getAllBooks()) {
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        //Creating the new arrays for books in gson
        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }

    //Initializing all the data and storing in gson
    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Failed States", "Noam Chompsky", 320, "https://img1.od-cdn.com/ImageType-400/1493-1/FC9/119/78/%7BFC911978-849E-43EC-B4F7-1BE10F90CC89%7DImg400.jpg"
                , "Critique of the US", "How do computers and robots change the meaning of being human? How do we deal with the epidemic of fake news? Are nations and religions still relevant? What should we teach our children?\n" +
                "\n" +
                "Yuval Noah Harari's 21 Lessons for the 21st Century is a probing and visionary investigation into today's most urgent issues as we move into the uncharted territory of the future. As technology advances faster than our understanding of it, hacking becomes a tactic of war, and the world feels more polarized than ever, Harari addresses the challenge of navigating life in the face of constant and disorienting change and raises the important questions we need to ask ourselves in order to survive.\n" +
                "\n" +
                "In twenty-one accessible chapters that are both provocative and profound, Harari builds on the ideas explored in his previous books, untangling political, technological, social, and existential issues and offering advice on how to prepare for a very different future from the world we now live in: How can we retain freedom of choice when Big Data is watching us? What will the future workforce look like, and how should we ready ourselves for it? How should we deal with the threat of terrorism? Why is liberal democracy in crisis?\n" +
                "\n" +
                "Harari's unique ability to make sense of where we have come from and where we are going has captured the imaginations of millions of readers. Here he invites us to consider values, meaning, and personal engagement in a world full of noise and uncertainty. When we are deluged with irrelevant information, clarity is power. Presenting complex contemporary challenges clearly and accessibly, 21 Lessons for the 21st Century is essential reading."));
        books.add(new Book(2, "21 Lessons For the 21st Century", "Yuval Harari", 400, "data:image/png;base64,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"
                , "21 Lessons for the 21st Century is a book written by bestseller Israeli author Yuval Noah Harari and published in August 2018 by Spiegel & Grau in the US and by Jonathan Cape in the UK", "The United States has repeatedly asserted its right to intervene militarily against \"failed states\" around the globe. In this much-anticipated follow-up to his international bestseller Hegemony or Survival, Noam Chomsky turns the tables, showing how the United States itself shares features with other failed states―suffering from a severe \"democratic deficit,\" eschewing domestic and international law, and adopting policies that increasingly endanger its own citizens and the world. Exploring the latest developments in U.S. foreign and domestic policy, Chomsky reveals Washington's plans to further militarize the planet, greatly increasing the risks of nuclear war. He also assesses the dangerous consequences of the occupation of Iraq; documents Washington's self-exemption from international norms, including the Geneva conventions and the Kyoto Protocol; and examines how the U.S. electoral system is designed to eliminate genuine political alternatives, impeding any meaningful democracy.\n" +
                "\n" +
                "Forceful, lucid, and meticulously documented, Failed States offers a comprehensive analysis of a global superpower that has long claimed the right to reshape other nations while its own democratic institutions are in severe crisis. Systematically dismantling the United States' pretense of being the world's arbiter of democracy, Failed States is Chomsky's most focused―and urgent―critique to date."));
        books.add(new Book(3, "Harry Potter and The Sorcerer's Stone", "JK. Rowling", 223, "https://www.jkrowling.com/wp-content/uploads/2018/01/SorcerersStone_2017.png",
                "Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling.", "Featuring vivid descriptions and an imaginative story line, it followed the adventures of the unlikely hero Harry Potter, a lonely orphan who discovers that he is actually a wizard and enrolls in the Hogwarts School of Witchcraft and Wizardry. The book received numerous awards, including the British Book Award."
        ));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    //There will be only one instace of the utils class
    public static Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }

    }

    //Converting the gson file to and Array list and returning
    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();

        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null), type);
        Boolean isEmpty;
        if (null == books) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null), type);
        Boolean isEmpty;
        if (null == books) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS_KEY, null), type);
        Boolean isEmpty;
        if (null == books) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        Boolean isEmpty;
        if (null == books) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return books;
    }

    //This function will allow us to get the book we need by its ID
    public Book getBookByID(int ID) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getID() == ID) {
                    return b;
                }
            }

        }
        return null;
    }

    //Adding the book to gson database
    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Removing the array from the database, then adding the updated one
                editor.remove(ALREADY_READ_BOOKS_KEY);
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS_KEY);
                editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavorites(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    //Removing the book from the database. Similar in logic to adding
    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != book) {
            for (Book b : books) {
                if (b.getID() == book.getID()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS_KEY);
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != book) {
            for (Book b : books) {
                if (b.getID() == book.getID()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS_KEY);
                        editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavorites(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != book) {
            for (Book b : books) {
                if (b.getID() == book.getID()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != book) {
            for (Book b : books) {
                if (b.getID() == book.getID()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
