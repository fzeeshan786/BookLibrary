package com.zeeshan.booklibrary.model;

public class Product
{
   public Product()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public int getBook_pages() {
        return book_pages;
    }

    public void setBook_pages(int book_pages) {
        this.book_pages = book_pages;
    }




    public Product(int id, String book_title, String book_author, int book_pages) {
        this.id = id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    int id;
    String book_title;
    String book_author;
    int book_pages;

}
