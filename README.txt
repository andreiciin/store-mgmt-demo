-------------------------  store management tool --------------------------

I will describe here the stages of the development regarding this application:

For the database I used in-memory H2 database.
I used dependencies like spring data jpa to easily access the data using repositories,
spring security to enable security features for this application.

I created 4 entities in this application: User, Role, Product and Order. The tables assigned to these
entities are joined using ManyToMany and ManyToOne relations. I used a Model-View-Controller design
for the application, therefore for User, Order and Product I have a JpaRepository, Service class where
I write business logic like calculateTotalCost and Controller which exposes the endpoints.
I developed several endpoints which could filter, update, delete, create, order. I used postman to
test my endpoints and also developed an integration test. For each class I have written a unit test.

For error handling and logging I have loggers in some classes, each entity has its own error class,
I also developed a centralized error handling controller advice.
It intercepts exceptions thrown from controllers, allowing to define custom responses for exceptions.

The security was insured by password encoding, DTO class which hides the password, permission based on
roles and I also have a basic authentication component.

http://localhost:8080/products/search?name=House Credit
http://localhost:8080/orders/filter?date=2024-10-01&sort=desc