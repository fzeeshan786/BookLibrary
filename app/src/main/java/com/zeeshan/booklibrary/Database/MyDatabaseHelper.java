package com.zeeshan.booklibrary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zeeshan.booklibrary.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    public Context context;
    // version
    public static final int DATABASE_VERSION = 1;

    // database
    public static final String DATABASE_NAME = "BookLibrary";

    // table name
    public static final String TABLE_NAME = "my_library";

    // column
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "book_title";
    public static final String COLUMN_AUTHOR = "book_author";
    public static final String COLUMN_PAGES = "book_pages";

    // ffffffffffffffffffffffffffffff CREATE DATABASE ffffffffffffffffffffffffffff
    public MyDatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        this.context = context;

    }

    // FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF CREATE TABLE FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" NUMBER PRIMARY KEY,"+COLUMN_TITLE+" TEXT,"+COLUMN_AUTHOR+" TEXT,"+COLUMN_PAGES+" NUMBER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    // ############################### INSERT RECORD ###########################
    public long insertBook(Product product)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, product.getId());
        values.put(COLUMN_TITLE, product.getBook_title());
        values.put(COLUMN_AUTHOR, product.getBook_author());
        values.put(COLUMN_PAGES, product.getBook_pages());

        return db.insert(TABLE_NAME,null,values);
    }
    // ############################### INSERT RECORD ###########################

    // ############################ get all data ################################
    public List<Product> getAllData()
    {
        SQLiteDatabase db = getReadableDatabase();
        List<Product> container = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do
            {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setBook_title(cursor.getString(1));
                product.setBook_author(cursor.getString(2));
                product.setBook_pages(cursor.getInt(3));

                container.add(product);
            }
            while (cursor.moveToNext());
        }
        return container;
    }

    // ##################################### UPDATE RECORD ######################################
    public long updateData(Product product)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,product.getId());
        values.put(COLUMN_TITLE,product.getBook_title());
        values.put(COLUMN_AUTHOR,product.getBook_author());
        values.put(COLUMN_PAGES,product.getBook_pages());

        return db.update(TABLE_NAME,values,COLUMN_ID+"=?",new String[]{String.valueOf(product.getId())});
    }

    // ########################### delete data #######################################
    public long deleteRecord(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{String.valueOf(id)});
    }
}
