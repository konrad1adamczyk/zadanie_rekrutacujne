## Introduction
You are building an auditing microservice for an e-commerce platform.

The service receives events from the Kafka topic `users`, which represents user registrations.
The JSON data is structured as follows:

```json
{ 
    "login":"someLogin",   
    "creationTime":"2018-06-20T22:51:20.030201" 
}
```

Once the event is received, it is transformed to the Java object `RegisteredUserEvent` (via the Jackson library) and stored in the relational H2 database via Spring Data JPA.

## Problem statement
- Change the implementation logic of the events for registered users
    - Follow the [tolerant reader principle](https://martinfowler.com/bliki/TolerantReader.html) - successfully parse JSON messages even if they contain additional fields.
    - Make sure that `login` field is unique (add a uniqueness constraint to `User` object representing a table)
- Add implementation for events representing product orders of a given user.
    - Events are received from Kafka topic `orders` in the following JSON format:

    ```json
    { 
        "userLogin":"someLogin", 
        "productId":"someId",    
        "creationTime":"2018-06-20T22:51:20.030201" 
    }
    ```
    - JSON fields explained:
        - userLogin = registered user login
        - productId = id of the product
        - creationTime = creation time of this event in UTC format
    - Events should be stored using `ProductOrderRepository`
    - Processing should follow tolerant reader principle.

## Hints
Analyze the implementation logic of processing `RegisteredUserEvent`.

There are skeleton classes `ProductOrderEventReceiver`, `ProductOrder`, `ProductOrderEventProcessor` that you should implement.

There is end to end test for `RegisteredUserEvent` that checks general requirements (without all the details).
