package com.albo.marveljavaexam.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

public class MarvelRSClientInterceptor implements ClientHttpRequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(MarvelRSClientInterceptor.class);
    private MarvelAuthorization authorization;

    public MarvelRSClientInterceptor(MarvelAuthorization authorization) {
        this.authorization = authorization;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        logger.info("Request original URI: {}", request.getURI());

        URI uri = UriComponentsBuilder.fromHttpRequest(request)
                .queryParam("ts", authorization.getTs())
                .queryParam("apikey", authorization.getApiKey())
                .queryParam("hash", authorization.getHash())
                .build(true).toUri();

        HttpRequest modifiedRequest = new HttpRequestWrapper(request) {

            @Override
            public URI getURI() {
                return uri;
            }
        };
        logRequestDetails(modifiedRequest);

        return execution.execute(modifiedRequest, body);
    }

    private void logRequestDetails(HttpRequest request) {
        logger.info("Headers: {}", request.getHeaders());
        logger.info("Request Method: {}", request.getMethod());
        logger.info("Request URI: {}", request.getURI());
    }
}
