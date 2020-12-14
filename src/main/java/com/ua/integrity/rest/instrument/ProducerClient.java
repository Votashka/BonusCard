package com.ua.integrity.rest.instrument;

import javax.ws.rs.Produces;
import java.io.Serializable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class ProducerClient implements Serializable {
    @Produces
    @InjClient
    public Client client() {
        return ClientBuilder.newClient();
    }
}