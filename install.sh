mvn clean install
docker build -t tomtex .
docker run -v /tmp:/tmp -p 8080:8080 tomtex
