package com.zeeshan.booklibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zeeshan.booklibrary.Database.MyDatabaseHelper;
import com.zeeshan.booklibrary.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        myDatabaseHelper = new MyDatabaseHelper(this);

        add_button = findViewById(R.id.floatingActionButton);

        add_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        //myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

      //  storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this,  book_id, book_title, book_author, book_pages);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(customAdapter);

        // get data
        List<Product> list = myDatabaseHelper.getAllData();
        for(Product product:list)
        {
            book_id.add(String.valueOf(product.getId()));
            book_title.add(product.getBook_title());
            book_author.add(product.getBook_author());
            book_pages.add(String.valueOf(product.getBook_pages()));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 )
        {
            recreate();
           // customAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finishAffinity();
    }
}