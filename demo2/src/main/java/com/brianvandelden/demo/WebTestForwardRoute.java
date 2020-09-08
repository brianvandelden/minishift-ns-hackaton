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
                .get("/test").to("direct:test2");

        from("direct:test2")
                .transform().constant("hallo");

        from("direct:test")
                .log("${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://minishift-ns-hackaton:8080/hello?bridgeEndpoint=true");
    }

}