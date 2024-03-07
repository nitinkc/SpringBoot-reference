package com.spring.reference.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    @Value("${my.http.pool.maxTotalConnections: 50}")
    private int httpPoolMaxConnections;

    @Value("${my.http.pool.maxPerRoute: 25}")
    private int httpPoolMaxPerRouteConnections;

    @Value("${my.http.pool.validateAfterInactivity: 50}")
    private int httpPoolValidateAfterInactivity;

    @Value("${my.http.pool.evictIdleConnTime: 2000}")
    private int httpPoolIdleConnEvictTime;



/*    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // Disable SSL certificate validation
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        @SuppressWarnings("deprecation")
        PoolingHttpClientConnectionManager connManager = PoolingHttpClientConnectionManagerBuilder
                .create()
                .setSSLSocketFactory(csf)
                .setMaxConnTotal(httpPoolMaxConnections)
                .setMaxConnPerRoute(httpPoolMaxPerRouteConnections)
                .setValidateAfterInactivity(TimeValue.of(httpPoolValidateAfterInactivity, TimeUnit.SECONDS))
                .build();

        // Bind HTTP connection pool metrics for Micrometer (Prometheus)
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .evictExpiredConnections()
                .evictIdleConnections(TimeValue.ofMilliseconds(httpPoolIdleConnEvictTime))
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplateBuilder()
                .requestFactory(() -> requestFactory)
                .interceptors(new RestTemplateHeadersPropagatorInterceptor())
                .build();
    }*/
// Content-Length header is no longer set in RestTemplate, therefore Content-Length header needs to be removed
		//header.remove(HttpHeaders.CONTENT_LENGTH);

/*    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofSeconds(httpClientConnectTimeout))
                .setResponseTimeout(Timeout.ofSeconds(httpClientReadTimeout))
                .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(httpClientPoolMaxConnections);
        connManager.setDefaultMaxPerRoute(httpClientPoolMaxPerRouteConnections);
        connManager.setValidateAfterInactivity(TimeValue.ofSeconds(httpClientPoolValidateAfterInactivity));
        connManager.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(Timeout.ofSeconds(httpClientReadTimeout)).build());


        CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setConnectionManager(connManager)
                .evictExpiredConnections()
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);

        return builder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(requestFactory))
                .build();
    }*/


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Configure ObjectMapper to ignore unknown properties
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
