#!/bin/bash

http_response=$(curl -s -o response.txt -w "%{response_code}" http://localhost:5000/products)
if [ $http_response != "200" ]; then
    echo "Test FAILED"
    exit 1
else
    echo "Test PASSED"
    echo "Server returned:"
    cat ./response.txt | jq
    rm ./response.txt
fi
