package com.ramkumar.booksapp.model.pojo;

import java.io.Serializable;

/**
 * Created by Ramkumar on 7/15/2017.
 */

public class Book implements Serializable{

    private String Tittle;
    private String Authors;
    private String Image;
    private double Rating;
    private double Price;
    private Integer Pages;

    public Book(String tittle, String authors, String image, double rating,double price,Integer pages) {
        Tittle = tittle;
        Authors = authors;
        Image = image;
        Rating = rating;
        Price = price;
        Pages = pages;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String authors) {
        Authors = authors;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Integer getPages() {
        return Pages;
    }

    public void setPages(Integer pages) {
        Pages = pages;
    }
}
