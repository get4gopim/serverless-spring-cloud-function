# serverless-spring-cloud-function

curl localhost:8080/greeter -H "Content-Type: text/plain" -d "World"

sls deploy
sls invoke -f members --data '{"name" : "gopi"}'
