package com.brianvandelden.demo;

import org.apache.camel.*;
import org.apache.camel.support.DefaultComponent;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.camel.support.DefaultProducer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class CatComponent extends DefaultComponent {

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return new DefaultEndpoint() {
            @Override
            public Producer createProducer() throws Exception {
                return new DefaultProducer(this) {
                    @Override
                    public void process(final Exchange exchange) throws Exception {
                        String body = exchange.getIn().getBody(String.class);
                        JSONArray array = new JSONArray(body);
                        JSONObject json = array.getJSONObject(0);
                        String html = "" +
                                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> <html lang=\"en\"> <head> <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"> <title>KATTEN!</title> </head> <body> <img src=\""+json.getString("url")+"\" alt=\"Kat voor release train\" width=\""+json.getInt("width")+"\" height=\""+json.getInt("height")+"\"> </body> </html>";
                        exchange.getIn().setBody(html);
                        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/html");

                    }

                    @Override
                    public void close() {

                    }

                };
            }

            @Override
            public Consumer createConsumer(Processor processor) throws Exception {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isSingleton() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            protected String createEndpointUri() {
                return "Cat";
            }
        };
    }

    @Override
    public void close() {

    }
}

