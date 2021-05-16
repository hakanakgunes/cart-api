# Atlavik Cart API Challenge

## For Build maven command
mvn clean install -X -f pom.xml

## For start maven command
mvn spring-boot:run

## After run app for Swagger URL
http://localhost:8082/swagger-ui.html for checking endpoints.

##Game Create URL
http://<host>:<port>/games create new game and return game id.

##Game Play URL
http://<host>:<port>/games/{gameId}/pits/{pitId} play game url. Plus return board status.

##Game Play Rule
When game created start Player first. And turn according to Kalah rules.
Player first play with between 1 and 6.
Player second play with between 8 and 13.
Player can't play opponent pits kalah or undefined pit or get exceptions.
When game is over api return 404 and give who wins information.
When game is over you need to create new one for play again.

## Application pit stones
Stones number can change application.properties with stone-count.

## Application storage info
Application store in local cache. Expire time write after a hours and capacity is 100 games.