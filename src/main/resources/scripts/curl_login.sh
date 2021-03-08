#!/bin/bash
curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "bmrrossi",
    "password": "tyj65.8ip"
}' http://localhost:8080/weesign_api/login
