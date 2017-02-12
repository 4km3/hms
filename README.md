[![](https://images.microbadger.com/badges/image/rawmind/hms.svg)](https://microbadger.com/images/rawmind/hms "Get your own image badge on microbadger.com")

# Herding Microservices
The original and primary location of the document is https://4km3.github.io/hms/

## Versions

- `Latest` [(Dockerfile)](https://github.com/4km3/hms/blob/master/Dockerfile)

## Build

From [alpine-nginx][alpine-nginx], copy docs into /opt/nginx/www/hms, install reveal.js REVEAL_VERSION=3.4.1 into /opt/nginx/www/hms/reveal.js and serve them throguht port 8080.

```
docker build -t rawmind/hms:<version> .
```

## Run

```
docker run -td -p 8080:8080 rawmind/hms:<version>
```

## Update reveal.js

Just set the REVEAL_VERSION variable into the Dockerfile to the desired version and build the docker.

[alpine-nginx]: https://github.com/rawmind0/alpine-nginx/