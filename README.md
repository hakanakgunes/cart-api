#Cart API

## For Build maven command
mvn clean install -X -f pom.xml

## Copy cart-api-0.0.1-SNAPSHOT.jar file to under src/main/docker directory.
cp target/cart-api-0.0.1-SNAPSHOT.jar src/main/docker

## For start application
docker-compose up

## For stop application
docker-compose down

## After run app for Swagger URL
GET http://localhost:8082/swagger-ui.html for checking endpoints.

## APIKEY control and value
Api-key stored at application.propeties.
You should add this param "?apiKey=APIKEY" at every call.

##Cart Create URL
POST http://localhost:8082/api/carts?apiKey=APIKEY
Add Request body json format.
body instance: {
"countryCode": "USA",
"currency": "US"
}

##Get Carts URL
GET http://localhost:8082/api/carts?apiKey=APIKEY

##Get Cart URL
GET http://localhost:8082/api/carts/{cartId}?apiKey=APIKEY

##Add Product to Cart
Get Specific cartId from Get Carts endpoint.
PUT http://localhost:8082/api/carts/{cartId}/products?apiKey=APIKEY
Add Request body json format.
Body instance :
{
"description": "Apple Iphode 12",
"category": "ELECTRONICS",
"price": 3750.05
}

## Get Products
Get Specific cartId from Get Carts endpoint.
GET http://localhost:8082/api/carts/{cartId}/products?apiKey=APIKEY

## Get Product
Get Specific cartId from Get Carts endpoint.
Get productId from Get Products endpoints.
GET http://localhost:8082/api/carts/{cartId}/products/{productId}?apiKey=APIKEY

##Delete Product
Get Specific cartId from Get Carts endpoint.
Get productId from Get Products endpoints.
http://localhost:8082/api/carts/{cartId}/products/{productId}?apiKey=APIKEY