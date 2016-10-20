package com.example.ienning.ncuhome.db;

/**
 * Created by ienning on 16-10-2.
 */

public class ItemBook {
    private String author;
    private String barCode;
    private String borrowDate;
    private String check;
    private String location;
    private String returnDate;
    private String title;

    public ItemBook(String author, String barCode, String borrowDate, String check, String location, String returnDate, String title) {
        this.author = author;
        this.barCode = barCode;
        this.borrowDate = borrowDate;
        this.check = check;
        this.location = location;
        this.returnDate = returnDate;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getCkeck() {
        return check;
    }

    public String getLocation() {
        return location;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getTitle() {
        return title;
    }
}
