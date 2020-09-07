package com.brianvandelden.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WebTestForwardRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\1")

        .to("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\2");
    }

}