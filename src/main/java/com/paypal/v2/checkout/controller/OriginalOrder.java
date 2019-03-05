package com.paypal.v2.checkout.controller;

import com.paypal.orders.Item;
import java.util.List;

public class OriginalOrder {

    private String intent;
    private String brandName;
    private String landingPage;
    private String shippingPreference;
    private String referenceId;
    private String description;
    private String customId;
    private String softDescriptor;
    private String currency;
    private String total;
    private String shipping;
    private String handling;
    private String taxTotal;
    private String shippingDiscount;
    private CustomerAddress address;

    private List<PPItem> items;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getSoftDescriptor() {
        return softDescriptor;
    }

    public void setSoftDescriptor(String softDescriptor) {
        this.softDescriptor = softDescriptor;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }

    public String getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(String taxTotal) {
        this.taxTotal = taxTotal;
    }

    public String getShippingDiscount() {
        return shippingDiscount;
    }

    public void setShippingDiscount(String shippingDiscount) {
        this.shippingDiscount = shippingDiscount;
    }

    public List<PPItem> getItems() {
        return items;
    }

    public void setItems(List<PPItem> items) {
        this.items = items;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public String getShippingPreference() {
        return shippingPreference;
    }

    public void setShippingPreference(String shippingPreference) {
        this.shippingPreference = shippingPreference;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public CustomerAddress getAddress() {
        return address;
    }

    public void setAddress(CustomerAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OriginalOrder{" +
                "intent='" + intent + '\'' +
                ", brandName='" + brandName + '\'' +
                ", landingPage='" + landingPage + '\'' +
                ", shippingPreference='" + shippingPreference + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", description='" + description + '\'' +
                ", customId='" + customId + '\'' +
                ", softDescriptor='" + softDescriptor + '\'' +
                ", currency='" + currency + '\'' +
                ", total='" + total + '\'' +
                ", shipping='" + shipping + '\'' +
                ", handling='" + handling + '\'' +
                ", taxTotal='" + taxTotal + '\'' +
                ", shippingDiscount='" + shippingDiscount + '\'' +
                ", address=" + address +
                ", items=" + items.toString() +
                '}';
    }
}
