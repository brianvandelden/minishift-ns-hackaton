package com.brianvandelden.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WebTestForwardRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\1")
        .to("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\2");

        from("timer://foo?fixedRate=true&delay=0&period=10000")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://www.google.com")
                .log("Ik doe een request")
                .to("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\3\\test.txt");
    }

}