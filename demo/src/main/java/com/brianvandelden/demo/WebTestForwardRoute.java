package com.brianvandelden.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class WebTestForwardRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        CatComponent cat = new CatComponent();

        getContext().addComponent("Cat", cat);

        restConfiguration()
                .component("jetty")
//                .host("localhost")
                .port(8080)
                .bindingMode(RestBindingMode.auto);


        rest("/hoi")
                .get("").to("direct:test");

        from("direct:test")
                //asd
                .log("${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("https://api.thecatapi.com/v1/images/search?bridgeEndpoint=true")
                .to("Cat:Cat");
    }
}