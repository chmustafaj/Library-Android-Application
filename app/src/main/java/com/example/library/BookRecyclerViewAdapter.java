package com.example.library;

import static com.example.library.BookActivity.BOOK_ID_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {
    //initializing CallBack interfaces for each of the activities. This will refresh the recycler views each time an item is deleted, as the function for deleting them
    //is in the activity itself, as opposed to the adapter
    public interface RemoveFromWantToRead {
        void removeFromWantToReadFunction(Book book);
    }

    public interface RemoveFromFavorites {
        void removeFromFavoritesFunction(Book book);
    }

    public interface RemoveFromCurrentlyReading {
        void removeFromCurrentlyReadingFunction(Book book);
    }

    public interface RemoveFromAlreadyRead {
        void removeFromAlreadyReadFunction(Book book);
    }

    private RemoveFromWantToRead removeFromWantToRead;
    private RemoveFromFavorites removeFromFavorites;
    private RemoveFromCurrentlyReading removeFromCurrentlyReading;
    private RemoveFromAlreadyRead removeFromAlreadyRead;
    private static final String TAG = "BookRecyclerViewAdapter";

    private ArrayList<Book> books = new ArrayList();
    private Context mContext;
    private String parentActivity;

    public BookRecyclerViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public BookRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookRecyclerViewAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtName.setText(books.get(position).getName());
        Glide.with(mContext).asBitmap().load(books.get(position).getImageURL()).into(holder.imageBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting the book activity when the cardView (named parent) is clicked. Also putting in the ID for later use in the Book Activity
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getID());
                mContext.startActivity(intent);
            }
        });
        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDesc.setText(books.get(position).getShortDesc());

        //This part of the code is to enable the expandable card view
        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
            //The delete button will not show in the All Books activity

            if (parentActivity.equals("allBooks")) {
                holder.btnDelete.setVisibility(View.GONE);
            } else if (parentActivity.equals("wantToRead")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                //Creating the delete button
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //A conformation dialogue for a better UI experience
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    removeFromWantToRead = (RemoveFromWantToRead) mContext;
                                    removeFromWantToRead.removeFromWantToReadFunction(books.get(position));
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                } catch (ClassCastException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            } else if (parentActivity.equals("currentlyReading")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    removeFromCurrentlyReading = (RemoveFromCurrentlyReading) mContext;
                                    removeFromCurrentlyReading.removeFromCurrentlyReadingFunction(books.get(position));
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                } catch (ClassCastException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            } else if (parentActivity.equals("alreadyRead")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    removeFromAlreadyRead = (RemoveFromAlreadyRead) mContext;
                                    removeFromAlreadyRead.removeFromAlreadyReadFunction(books.get(position));
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                } catch (ClassCastException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            } else if (parentActivity.equals("favoriteBooks")) {

                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    removeFromFavorites = (RemoveFromFavorites) mContext;
                                    removeFromFavorites.removeFromFavoritesFunction(books.get(position));
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                } catch (ClassCastException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imageBook;
        private TextView txtName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelativeLayout;
        private TextView txtAuthor, txtDesc, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imageBook = itemView.findViewById(R.id.imageBook);
            txtName = itemView.findViewById(R.id.txtName);

            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelativeLayout = itemView.findViewById(R.id.expandedRelativeLayout);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDesc = itemView.findViewById(R.id.txtShortDesc);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            //The isExpanded boolean will change its value each time the up or down arrow is clicked
            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
