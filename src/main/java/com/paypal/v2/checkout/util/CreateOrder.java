package com.paypal.v2.checkout.util;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.braintreepayments.http.HttpResponse;

import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Item;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetails;
import com.paypal.v2.checkout.controller.OriginalOrder;
import org.json.JSONObject;

/*
 *
 *1. Import the PayPal SDK client that was created in `Set up the Server SDK`.
 *This step extends the SDK client.  It's not mandatory to extend the client, you can also inject it.
 */
public class CreateOrder extends PayPalClient {

    //2. Set up your server to receive a call from the client
    /**
     *Method to create order
     *
     *@param debug true = print response data
     *@return HttpResponseOrder response received from API
     *@throws IOException Exceptions from API if any
     */
    public Map<String, String> createOrder(boolean debug) throws IOException {
        Map<String, String> map = new HashMap<>();
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(buildRequestBody());
        //3. Call PayPal to set up a transaction
        HttpResponse<Order> response = client().execute(request);
        System.out.println("Response: " + response.toString());
        // if (true) {
        if (response.statusCode() == 201) {
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Status: " + response.result().status());
            System.out.println("Order ID: " + response.result().id());
            System.out.println("Intent: " + response.result().intent());
            System.out.println("Links: ");
            for (LinkDescription link : response.result().links()) {
                System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
            }
            System.out.println("Total Amount: " + response.result().purchaseUnits().get(0).amount().currencyCode()
                    + " " + response.result().purchaseUnits().get(0).amount().value());


            map.put("statusCode" , response.statusCode()+"");
            map.put("status" , response.result().status());
            map.put("order_id" , response.result().id());

            //return response.result().id();
        } else {
            System.out.println("Response: " + response.toString());
            map.put("Reponse",response.toString());
            //return response.toString();
        }

        return map;
        //}
    }

    public Map<String, String> createOrder(OriginalOrder originalOrder) throws IOException {
        Map<String, String> map = new HashMap<>();
        System.out.println("Create Order: " + originalOrder.getIntent());
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(buildRequestBody(originalOrder));
        //3. Call PayPal to set up a transaction
        HttpResponse<Order> response = client().execute(request);
        System.out.println("Response: " + response.toString());
       // if (true) {
            if (response.statusCode() == 201) {
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Status: " + response.result().status());
                System.out.println("Order ID: " + response.result().id());
                System.out.println("Intent: " + response.result().intent());
                System.out.println("Links: ");
                for (LinkDescription link : response.result().links()) {
                    System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
                }
                System.out.println("Total Amount: " + response.result().purchaseUnits().get(0).amount().currencyCode()
                        + " " + response.result().purchaseUnits().get(0).amount().value());


                map.put("statusCode" , response.statusCode()+"");
                map.put("status" , response.result().status());
                map.put("order_id" , response.result().id());

                //return response.result().id();
            } else {
                System.out.println("Response: " + response.toString());
                map.put("Reponse",response.toString());
                //return response.toString();
            }
        return map;
        //}

    }


    /**
     *Method to generate sample create order body with CAPTURE intent
     *
     *@return OrderRequest with created order request
     */
    private OrderRequest buildRequestBody(OriginalOrder originalOrder) {
        String currency = "MXN";

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.intent(originalOrder.getIntent());

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName(originalOrder.getBrandName())
                .landingPage(originalOrder.getLandingPage())
                .shippingPreference(originalOrder.getShippingPreference());
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId("PUHF")
                .description("Sporting Goods")
                .customId("CUST-HighFashions")
                .softDescriptor("HighFashions")
                .amount(new AmountWithBreakdown().currencyCode(currency).value("500.00")
                        .breakdown(new AmountBreakdown().itemTotal(new Money().currencyCode(currency).value("500.00"))
                                .shipping(new Money().currencyCode(currency).value("10.00"))
                                .handling(new Money().currencyCode(currency).value("0.00"))
                                .taxTotal(new Money().currencyCode(currency).value("0.00"))
                                .shippingDiscount(new Money().currencyCode(currency).value("10.00"))))
                .items(new ArrayList<Item>() {
                    {
                        add(new Item().name("T-shirt").description("Green XL").sku("sku01")
                                .unitAmount(new Money().currencyCode(currency).value("200.00"))
                                .tax(new Money().currencyCode(currency).value("0.00")).quantity("1")
                                .category("PHYSICAL_GOODS"));
                        add(new Item().name("Shoes").description("Running, Size 10.5").sku("sku02")
                                .unitAmount(new Money().currencyCode(currency).value("300.00"))
                                .tax(new Money().currencyCode(currency).value("0.00")).quantity("1")
                                .category("PHYSICAL_GOODS"));
                    }
                })
                .shipping(new ShippingDetails().name(new Name().fullName("Isaac"))
                        .addressPortable(new AddressPortable().addressLine1("Grecia 151").addressLine2("203")
                                .adminArea2("CDMX").adminArea1("CDMX").postalCode("02090").countryCode("MX")));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    private OrderRequest buildRequestBody() {
        String currency = "MXN";

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.intent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("Oasisaac Java Example")
                .landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId("PUHF")
                .description("Sporting Goods")
                .customId("CUST-HighFashions")
                .softDescriptor("HighFashions")
                .amount(new AmountWithBreakdown().currencyCode(currency).value("500.00")
                        .breakdown(new AmountBreakdown().itemTotal(new Money().currencyCode(currency).value("500.00"))
                                .shipping(new Money().currencyCode(currency).value("10.00"))
                                .handling(new Money().currencyCode(currency).value("0.00"))
                                .taxTotal(new Money().currencyCode(currency).value("0.00"))
                                .shippingDiscount(new Money().currencyCode(currency).value("10.00"))))
                .items(new ArrayList<Item>() {
                    {
                        add(new Item().name("T-shirt").description("Green XL").sku("sku01")
                                .unitAmount(new Money().currencyCode(currency).value("200.00"))
                                .tax(new Money().currencyCode(currency).value("0.00")).quantity("1")
                                .category("PHYSICAL_GOODS"));
                        add(new Item().name("Shoes").description("Running, Size 10.5").sku("sku02")
                                .unitAmount(new Money().currencyCode(currency).value("300.00"))
                                .tax(new Money().currencyCode(currency).value("0.00")).quantity("1")
                                .category("PHYSICAL_GOODS"));
                    }
                })
                .shipping(new ShippingDetails().name(new Name().fullName("Isaac"))
                        .addressPortable(new AddressPortable().addressLine1("Grecia 151").addressLine2("203")
                                .adminArea2("CDMX").adminArea1("CDMX").postalCode("02090").countryCode("MX")));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    /**
     *This is the driver function that invokes the createOrder function to create
     *a sample order.
     */
    public static void main(String args[]) {
        try {
            new CreateOrder().createOrder(true);
        } catch (com.braintreepayments.http.exceptions.HttpException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
