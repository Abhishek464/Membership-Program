# Membership Program

Backend system for tier-based membership subscriptions.

## Features
- View membership plans
- Subscribe to plan + tier
- Upgrade membership tier
- Downgrade membership tier
- Cancel membership
- Track active membership
- Automatic membership expiry
- Tier eligibility strategy
- Concurrency-safe membership updates

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Swagger
- JUnit
- Mockito

## Design Decisions

### Architecture
Layered architecture:

Controller
↓
Service
↓
Repository
↓
Database

Additional Strategy Layer for tier eligibility.

### Concurrency
Implemented optimistic locking using @Version to prevent concurrent membership mutation conflicts.

### Extensibility
Tier qualification logic implemented using Strategy Pattern.

Membership benefits configurable through MembershipTier entity.

## Assumptions
- One active membership per user
- Payment integration out of scope
- User profile service mocked
- Order metrics mocked

## Run
docker-compose up --build

OR

mvn clean install
mvn spring-boot:run

## Swagger
http://localhost:8080/swagger-ui/index.html