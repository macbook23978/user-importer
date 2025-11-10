#!/bin/bash

echo ">>>>>> Avvio CSV importer..."
cd ~/workspace/user-importer
mvn clean install
mvn exec:java -Dexec.mainClass="com.example.App"
