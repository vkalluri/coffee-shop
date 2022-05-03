# Coffee Shop
This coffee shop program is built as a Spring Boot Micro service with inbuilt H2 as the database.
 
The application can be started using the inbuilt Tomcat server or can also be created as a WAR and deployed on any Java compatible application server.

The following command can be used to compile the application. Java and Maven should be available before hand.

`mvn clean install`

When we use the following command, inbuilt Tomcat server will be started with port as 8080.

`mvn spring-boot:run`

The following are the end points that one can access:

	> /coffeeshop/getAllCoffees (GET)
	> /coffeeshop/updateCoffee  (PUT)
	> /coffeeshop/deleteCoffee  (DELETE)
	> /coffeeshop/placeOrder    (POST)
	> /coffeeshop/getSummary    (GET)