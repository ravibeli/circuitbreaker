# Spring Cloud Circuit Breaker Enable/Disable Feature Demonstration

### Simple demonstration of spring cloud circuit breaker 

Use case 1: You have integrated with multiple shipment vendor's API, which creates the shipment on e-commerce application
Now, you have been asked to implement as follows:
1. When API of Shipping Service-A times out, API's of Shipping Service-B.
2. Keep customer's happy for always shipment service to serve.
3. Below configuration demonstrate the fallback mechanism with very simple example.
![image](https://github.com/ravibeli/circuitbreaker/assets/928202/3bc88f3b-d67f-42be-bcf8-418afc9dcc89)

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
* Disabling Circuit breaker feature, in this case, only Vendor-A API will be called, disabling API call of Vendor-B using fallback methods

```application.properties```
>spring.cloud.circuitbreaker.resilience4j.enabled=false
