package com.example.library;

//This is the book object, and all its attributes
public class Book {
    private int ID;
    private String name;
    private String author;
    private int pages;
    private String imageURL;
    private String shortDesc;
    private String longDesc;
    private Boolean isExpanded;

    public Book(int ID, String name, String author, int pages, String imageURL, String shortDesc, String longDesc) {
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.imageURL = imageURL;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        isExpanded = false;
    }

    public Boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", imageURL='" + imageURL + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }
}
