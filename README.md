[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=badge&logo=linkedin&logoColor=white)](http://linkedin.com/in/dmytro-trotsenko-97a6211a5)

# Product Shop App with PostgreSQL, Spring Web and Spring Security

This project is a simple product shop application with:  **Spring Boot**, **Spring Web**, **Spring Security**,
**PostgreSQL**, **JPA (Hibernate)**, **Flyway**, and **Thymleaf**.
### Project structure


| Folder                        | Description                                                   |
|-------------------------------|---------------------------------------------------------------|
| deploy                        | Contains the docker-compose.yml used to setup the application |
| src/main/java/com/productShop | Spring boot application ProductShopApp.java                   |

### How to build and run

In order to build the application you need to have the following software products installed:
- JDK >= 17
- docker & docker compose

### Exploring the Product Shop App

The server will start at <http://localhost:8080>.

Home page : <http://localhost:8080/home> <br/>
![home](readme_img/home.png)

Login page : <http://localhost:8080/login?logout>

Role Admin: <br/>
User Name: admin <br/>
Password: admin

Role User: <br/>
User Name: user <br/>
Password: user

![login](readme_img/Login.png)

Product service page : <http://localhost:8080/products> <br/>
![ProductService](readme_img/ProductService.png)

If you click on the link <http://localhost:8080/products> without being authorized as an admin : <br/>
![Err403](readme_img/Err403.png)

Add product page : <http://localhost:8080/products/add> <br/>
![Add product](readme_img/AddProduct.png)

Find product by id : <br/>
![Search](readme_img/Search.png)