package com.softgather.books;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FirebaseDatabaseHelper {
    //Step 1    initializing the classes
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBook;
    private List<Book> books = new ArrayList<>();




    //Step 4

    public interface DataStatus{    //this interface is created to do the process with DataSnapshot oject
        void DataIsLoaded(List<Book> books, List<String> keys); // it's created for synchronizing the data
        void DataIsInserted();  //for detecting if the data is inserted
        void DataIsUpdated();   //for detecting if the data is updated
        void DataIsDeleted();   //for detecting if the data is deleted

    }



    //Step 2    create constructor
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBook = mDatabase.getReference("books");   //this "books" is the same name as the name of the database inside firebase realtime database
    }

    //Step 3 create manually this readBooks method
    public void readBooks(final DataStatus dataStatus){       //DataStatus interface parameter is added after step-4
        //onDataChange and onCancelled overriding method will be called automatically if addValueEventListener is called upon
        mReferenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();  // clear the booklist from the database
                List<String> keys = new ArrayList<>();      // keys is the list of strings where all attributes of the database will be loaded

                for(DataSnapshot keyNode : dataSnapshot.getChildren()){     //keyNode is the attribute from the database
                    keys.add(keyNode.getKey());     //for getting the keys from the keyNodes(means attributes)

                    Book book = keyNode.getValue(Book.class);    //creating book object and initialize it from the nodes value

                    books.add(book);    //Adding books to the Book List
                }

                dataStatus.DataIsLoaded(books, keys);   //data is loaded in real-time

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
