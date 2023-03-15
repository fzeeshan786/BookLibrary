package com.zeeshan.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zeeshan.booklibrary.Database.MyDatabaseHelper;
import com.zeeshan.booklibrary.model.Product;

public class UpdateActivity extends AppCompatActivity
{
    EditText id_input,title_input,author_input,pages_input;
    Button update_button, delete_button;
    MyDatabaseHelper myDatabaseHelper;
    AlertDialog.Builder ab;
   // int id;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);

        id_input = findViewById(R.id.id_input2);
        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // set action bar title after get updatedate method
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setTitle(book_name);
//        }

        // use for get data
        Bundle b = getIntent().getExtras();
        id_input.setText(""+b.getString("id"));
        title_input.setText(""+b.getString("title"));
        author_input.setText(""+b.getString("author"));
        pages_input.setText(""+b.getString("pages"));


        // #################################### UPDATE BUTTON ###############################
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int id = Integer.parseInt(id_input.getText().toString());
                String book_name = title_input.getText().toString();
                String author_name = author_input.getText().toString();
                int pages_name = Integer.parseInt(pages_input.getText().toString());

                Product product = new Product(id,book_name,author_name,pages_name);
                long result = myDatabaseHelper.updateData(product);
                if (result>0)
                {
                    Toast.makeText(UpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UpdateActivity.this, "Not Vaved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // #################################### DELETE BUTTON #################################
        delete_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ab = new AlertDialog.Builder(UpdateActivity.this);
                ab.setTitle("Alert");
                ab.setMessage("Do you want to delete");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        int id = Integer.parseInt(id_input.getText().toString());

                        long result = myDatabaseHelper.deleteRecord(id);
                        if (result>0)
                        {
                            Toast.makeText(UpdateActivity.this, "Delete Record", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(UpdateActivity.this, "Query Problem", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                ab.show();



            }
        });
       // getAndSetIntentData();
    }

//    void getAndSetIntentData()
//    {
//        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages"))
//        {
//            id = getIntent().getStringExtra("id");
//            title = getIntent().getStringExtra("title");
//            author = getIntent().getStringExtra("author");
//            pages = getIntent().getStringExtra("pages");
//
//            id_input.setText(title);
//            title_input.setText(title);
//            author_input.setText(author);
//            pages_input.setText(pages);
//
//        }
//        else
//        {
//            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
//        }
//    }

}