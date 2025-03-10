# **Product Management System**

## **Table of Contents**
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Binary Tree Integration](#binary-tree-integration)
- [API Endpoints](#api-endpoints)

---

## **Introduction**

The Product Management System (PMS) is a Spring Boot application designed to help manage products efficiently. It features a RESTful API, database integration, and an innovative binary tree structure for product categorization and retrieval.

---

## **Features**
- Add, update, delete, and search products.
- Binary tree implementation for efficient categorization.
- Multiple profiles (Development and Production) for flexible deployment.
- API documentation using Swagger.
- Modular and scalable architecture.
- Github Actions for CI/CD

---

## **Technologies Used**
- **Backend:** Spring Boot (REST API, Spring Data JPA)
- **Database:** MySQL
- **Binary Tree:** Custom implementation in Java
- **Tools:** Maven, IntelliJ IDEA, Docker

## **Binary Tree Integration**

The application uses a custom binary tree structure for product categorization. The tree supports:
- **Deletion:** Products are efficiently removed while maintaining tree integrity.
- **Search:** Quick lookup by product name.

The binary tree is populated during application startup using data from the database

---

## **API Endpoints**

| HTTP Method | Endpoint                        | Description                    |
|-------------|---------------------------------|--------------------------------|
| `GET`       | `/api/products`                 | Fetch all products             |
| `POST`      | `/api/products`                 | Add a new product              |
| `PUT`       | `/api/products/{id}`            | Update a product               |
| `DELETE`    | `/api/products/{id}`            | Delete a product               |
| `GET`       | `/api/products/{id}`            | Fetch a product by ID          |
| `GET`       | `/api/products/tree/{name}`     | Fetch a product by name        |
| `DELETE`    | `/api/products/tree/{name}`     | Delete a product by name       |
| `Get`       | `/api/products/tree/sort`       | Fetch sorted paginated products |
| `Get`       | `/api/products/category/{name}` | Fetch category products        |
| `POST`      | `/api/category`                 | Add a category                 |
| `Get`       | `/api/category`                 | Fetch category                 |
| `Get`       | `/api/reviews/{productId`       | Fetch product reviews          |
| `POST`      | `/api/reviews/{productId`       | Add review to product   |
