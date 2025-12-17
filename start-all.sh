#!/bin/bash

echo "Starting Crop Service..."
(cd cropservice && mvn spring-boot:run) &

echo "Starting Weather Service..."
(cd weatherservice && mvn spring-boot:run) &

echo "Starting Growth Service..."
(cd growthservice && mvn spring-boot:run) &

echo "Starting Frontend..."
(cd frontend && mvn spring-boot:run) &

wait