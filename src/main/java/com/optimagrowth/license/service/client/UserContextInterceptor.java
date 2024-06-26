package com.optimagrowth.license.service.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.optimagrowth.license.utils.UserContext;
import com.optimagrowth.license.utils.UserContextHolder;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(UserContextInterceptor.class);
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();
		headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
		headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
		logger.debug("UserContextInterceptor : {}", headers);
		return execution.execute(request, body);
	}

	
}
