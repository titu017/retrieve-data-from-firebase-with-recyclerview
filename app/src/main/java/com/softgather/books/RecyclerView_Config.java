package com.softgather.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_Config {

    //Step-1
    private Context mContext;   //this class will help for calling the activity and methods/actions of the activity




    //Step-7
    private BookAdapter mbookAdapter;


    //create a method with four parameter
    public void setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys){
        mContext = context;
        mbookAdapter = new BookAdapter(books, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mbookAdapter);



    }




    //Step-2    this class is created for inflating the view and populating its view objects
    class BookItemView extends RecyclerView.ViewHolder {

        //declare variables

        private TextView title;
        private TextView author;


        private String key;     // for holding the book record


        //Step-3 creating constructor with parameter ViewGroup
        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).  //override the parent constructor
                    inflate(R.layout.book_list_item, parent, false));   //inflate the layout inside the recyclerview

            //initializing data to view in xml
            title = (TextView) itemView.findViewById(R.id.titleTv);
            author = (TextView) itemView.findViewById(R.id.authorIv);
        }


            //Step-4 creating a public method called bind to populating/setting the data in xml
        public void bind(Book book, String key){
            title.setText(book.getTitle());
            author.setText(book.getAuthor());

            this.key = key;     //setting the numbers of the key
        }
    }

    //Step-5 create inner class responsible for creating bookitemview and passing book object and key to bind method
    class BookAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mKeys;

        // create constructor
        public BookAdapter(List<Book> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        //Step-6 create these three overriding method
        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);    //return the instance of the BookItemView and give it to view group object
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {

            holder.bind(mBookList.get(position), mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mBookList.size();    //return the size of the book list
        }
    }
}
