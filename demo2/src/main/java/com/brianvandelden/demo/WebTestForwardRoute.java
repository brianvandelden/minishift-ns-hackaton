package com.brianvandelden.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class WebTestForwardRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("jetty")
//                .host("localhost")
                .port(8080)
                .bindingMode(RestBindingMode.auto);


        rest("/hoi")
                .get("/proxy").to("direct:test")
                .get("/test").to("direct:echo");

        from("direct:echo")
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setBody(simple("${env:HOST}"));

        from("direct:test")
                //asd
                .log("${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://${env:HOST}/${env:HTTPPATH}?bridgeEndpoint=true");
    }

}