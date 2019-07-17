package com.paypal.v2.checkout.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.representation.Form;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.core.MediaType;

public class TokenManager {

    public static void main(String[] args) {

        try {


            HttpAuthenticationFeature feature;
            feature = HttpAuthenticationFeature.basic("username", "password");

            Client client = Client.create();
            client.addFilter(new HTTPBasicAuthFilter("AaX2E5UgOWzrel-GoomAj3X9QKhQ-sRFyCnlE_H2Le3TTPKMoaIM_YsXlVFCCO5yOE0BXdBDr08t26JF", "ED4NiEEF84-dhQet4rpeu80ElXq3bphmeA8EaAU2KUKbsqwceL3FeXuhfUZrXO7N_p0h2Rwb8o3geT0q"));
            //Client client = Client.create();

            WebResource webResource = client
                    .resource("https://api.sandbox.paypal.com/v1/oauth2/token");


            webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

            //String input = "{\"grant_type\":\"client_credentials\"}";
            Form form = new Form();
            form.add("grant_type", "client_credentials");


            ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                    .post(ClientResponse.class,form);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus() + " Desc: " + response.toString());
            }

            System.out.println("Output from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
