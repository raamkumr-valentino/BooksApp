
package com.ramkumar.booksapp.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaleInfo {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("saleability")
    @Expose
    private String saleability;
    @SerializedName("isEbook")
    @Expose
    private Boolean isEbook;
    @SerializedName("listPrice")
    @Expose
    private ListPrice listPrice;
    @SerializedName("retailPrice")
    @Expose
    private RetailPrice retailPrice = new RetailPrice();
    @SerializedName("buyLink")
    @Expose
    private String buyLink;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SaleInfo() {
    }

    /**
     * 
     * @param retailPrice
     * @param saleability
     * @param listPrice
     * @param offers
     * @param buyLink
     * @param isEbook
     * @param country
     */
    public SaleInfo(String country, String saleability, Boolean isEbook, ListPrice listPrice, RetailPrice retailPrice, String buyLink, List<Offer> offers) {
        super();
        this.country = country;
        this.saleability = saleability;
        this.isEbook = isEbook;
        this.listPrice = listPrice;
        this.retailPrice = retailPrice;
        this.buyLink = buyLink;
        this.offers = offers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public Boolean getIsEbook() {
        return isEbook;
    }

    public void setIsEbook(Boolean isEbook) {
        this.isEbook = isEbook;
    }

    public ListPrice getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice listPrice) {
        this.listPrice = listPrice;
    }

    public RetailPrice getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(RetailPrice retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}
