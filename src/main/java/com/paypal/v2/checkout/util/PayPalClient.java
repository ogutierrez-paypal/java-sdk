package com.paypal.v2.checkout.util;


import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class PayPalClient {

    /**
     *Set up the PayPal Java SDK environment with PayPal access credentials.
     *This sample uses SandboxEnvironment. In production, use LiveEnvironment.
     */
    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "AdmG-UiDltjlWw_sHj9Tmk9wByzrVLDR2hfWrVCDB02muWVUnla-FbuPg8MAmCJvoUE-TVW6Ti2U1eEi",
            "EAM3PBronXttOiAkymJaaImpP8K2wbxiaFPyRjucdSUu6_MEGfmbN1JDbVL2MnXzfTN4FAU_UgKAfV3r");

    /**
     *PayPal HTTP client instance with environment that has access
     *credentials context. Use to invoke PayPal APIs.
     */
    PayPalHttpClient client = new PayPalHttpClient(environment);

    /**
     *Method to get client object
     *
     *@return PayPalHttpClient client
     */
    public PayPalHttpClient client() {
        return this.client;
    }
}