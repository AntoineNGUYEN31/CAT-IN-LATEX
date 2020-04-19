mvn clean install
docker build -t cattex .
docker run -p 8080:8080 cattex
