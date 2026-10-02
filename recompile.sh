#!/bin/bash

echo "compiling..."
javac --release 10 -Xlint -d bin $(find ./* | grep .java)
#javac --release 10 -d bin $(find ./* | grep .java)

#javac  -d bin $(find ./src/* | grep .java)
echo "done"

