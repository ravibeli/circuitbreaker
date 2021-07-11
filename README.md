# Getting Started

### Simple demonstration of spring cloud circuit breaker

Use case 1: You have integrated with multiple shipment vendor's API, which creates the shipment on e-commerce application
Now, you have been asked to implement as follows:
1. When API of Vendor-A times out, API's of Vendor-B.
2. Keep customer's happy for always shipment service to serve.
3. Below configuration demonstrate the fallback mechanism with very simple example.

Use case 2: Hypothetical situation, you want to disable circuit breaker due to some reason.
1. How to disable, when circuit-breaker logic screwed up due to some reason.


#### Spring Cloud Circuit Breaker Feature is switched ON
* Circuit breaker time limiter configured to ```4``` seconds refer the bean creation configuration class

```application.properties```
>spring.cloud.circuitbreaker.resilience4j.enabled=true

Try calling below API with delay [in seconds] ranging between 1 and 3 works fine, for 4 seconds circuit breaker activates and triggers the fallback

```http://localhost:8080/delay/3```

Try calling below API with delay [in seconds] for 4 seconds, as time limiter threshold reaches, circuit breaker activates and triggers the fallback method in which you write your logic to call API of Vendor-B.

```http://localhost:8080/delay/4```

#### Spring Cloud Circuit Breaker Feature is switched OFF
* Disabling Circuit breaker feature, in this case, only Vendor-A API will be called, disabling API call of Vendor-B sing fallback methods

```application.properties```
>spring.cloud.circuitbreaker.resilience4j.enabled=false

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.2/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Groovy Templates](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Resilience4J](https://cloud.spring.io/spring-cloud-static/spring-cloud-circuitbreaker/current/reference/html)
* [Config Client Quick Start](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_client_side_usage)
* [Function](https://cloud.spring.io/spring-cloud-function/)
* [Cloud Bootstrap](https://spring.io/projects/spring-cloud-commons)

### Additional Links
These additional references should also help you:

* [Various sample apps using Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function/tree/master/spring-cloud-function-samples)

