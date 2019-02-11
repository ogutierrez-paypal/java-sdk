package com.paypal.v2.checkout.controller;

import com.paypal.orders.PurchaseUnit;

public class OriginalOrder {

    private String intent;
    private String brandName;
    private String landingPage;
    private String shippingPreference;
    //private PurchaseUnit

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
}
