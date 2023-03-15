package com.zeeshan.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zeeshan.booklibrary.Database.MyDatabaseHelper;
import com.zeeshan.booklibrary.model.Product;

public class AddActivity extends AppCompatActivity
{
    EditText id_input;
    EditText title_input, author_input, pages_input;
    Button add_button;
    MyDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        databaseHelper = new MyDatabaseHelper(this);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        id_input = findViewById(R.id.id_input);
        add_button = findViewById(R.id.add_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                int id = Integer.parseInt(id_input.getText().toString());
                String title = title_input.getText().toString();
                String author = author_input.getText().toString();
                int pages = Integer.parseInt(pages_input.getText().toString());

                Product product = new Product(id,title,author,pages);
                long result = databaseHelper.insertBook(product);


//                if (id.isEmpty() || title.isEmpty() || author.isEmpty() || pages.isEmpty())
//                {
//                    Toast.makeText(AddActivity.this, "fill all text", Toast.LENGTH_SHORT).show();
//                }

                if (result>0)
                {
                    Toast.makeText(AddActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}