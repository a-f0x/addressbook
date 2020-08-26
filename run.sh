#!/usr/bin/env sh
docker build . --tag address_book:latest
docker-compose up