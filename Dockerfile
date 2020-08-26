FROM java:8-alpine AS build-env

RUN mkdir -p /usr/local/src/book

WORKDIR /usr/local/src/book
ADD . /usr/local/src/book
RUN ./build_jar.sh

FROM java:8-alpine
ARG VERSION
RUN apk update && \
    mkdir -p /usr/local/bin/book

WORKDIR /usr/local/bin/book

COPY --from=build-env /usr/local/src/book/build/libs/application.jar app.jar

ENTRYPOINT exec java -jar app.jar
