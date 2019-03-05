package com.paypal.v2.checkout.controller;


import com.braintreepayments.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.v2.checkout.util.CaptureOrder;
import com.paypal.v2.checkout.util.CreateOrder;
import com.paypal.v2.checkout.util.GetOrder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PayPalController {


    @GetMapping("/health")
    public String getAllNotes() {
        return "PayPal SDK with Java";
    }

    @GetMapping("/test-create-order")
    public String createOrderTest() {
        CreateOrder createOrder = new CreateOrder();
        try {
            return createOrder.createOrder(true).toString();
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    @GetMapping("/front-create-order")
    @ResponseBody
    public Map<String, String> createOrder() {
        System.out.println("Entering original order from FRONT: ");
        CreateOrder createOrder = new CreateOrder();
        try {
            return createOrder.createOrder(true);
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    //@CrossOrigin(origins = "http://192.168.64.2")
    @PostMapping("/create-order")
    @ResponseBody
    public Map<String, String> createOrder(@RequestBody OriginalOrder originalOrder) {
        System.out.println("Entering original order: "+ originalOrder.toString());
        CreateOrder createOrder = new CreateOrder();
        try {
            return createOrder.createOrder(originalOrder);
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    @PostMapping("/capture-order")
    @ResponseBody
    public Map<String, String> captureOrder(@RequestBody Map<String, Object> map) {
        System.out.println("Capature Controller, order value: "+ map.get("orderID"));
        CaptureOrder captureOrder = new CaptureOrder();
        try {
            return captureOrder.captureOrder(map.get("orderID").toString(),true);
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    @GetMapping("/get-order")
    public String getOrder() {
        GetOrder getOrder = new GetOrder();
        try {
            return getOrder.getOrder("48825896P16666046");
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }


    @GetMapping("/capture-order")
    public String captureOrder() {
        CaptureOrder captureOrder = new CaptureOrder();
        try {
            return captureOrder.captureOrder("48825896P16666046",true).toString();
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

}
