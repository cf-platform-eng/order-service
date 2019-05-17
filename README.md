# order-service
Order and Holding microservice refactored from the SpringTrader application, as described in Part 4 of the *Refactoring a Monolith into a Cloud-Native Application* blog series.

This is a simple REST service that provides Order and Holding data for Spring Trader. It registers itself with [Eureka](https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance). The Eureka url is configured via the [manifest.yml](https://github.com/cf-platform-eng/order-service/blob/part4/manifest.yml) file.

To get the source code, run the following from a clean directory:

```bash
git clone git@github.com:cf-platform-eng/order-service.git
cd order-service
git checkout part4
```

Build the service using the maven conventions (from the root directory of the project):

```bash
mvn clean install
```

To run the service locally you can take advantage of the maven spring-boot plugin:

```bash
mvn spring-boot:run
```

To deploy to cloud foundry, edit the manifest.yml file to give the app a unique name and to point it to your Eureka instance, and then perform a cf push.
