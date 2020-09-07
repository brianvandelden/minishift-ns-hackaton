package com.brianvandelden.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class WebTestForwardRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\1")
                .to("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\2");

//        from("timer://foo?fixedRate=true&delay=0&period=10000")
//                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
//                .to("http://demo1:8080/hello")
//                .log("${body}")
//                .to("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\3\\");
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

//                        .to("file://C:\\Users\\Niek Arends\\Documents\\Workspaces\\minishift-ns-hackaton\\demo2\\3\\");
    }

}