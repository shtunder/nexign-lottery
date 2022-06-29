# Lottery
Test assignment for the Java Developer position in Nexign

## Create participant
POST: http://localhost:8080/lottery/participant

Request Body
```
{
    "name": "Andrey",
    "age": 26,
    "city": "Saint-Petersburg"
}
```
Response Body
```
{
    "timestamp": "2022-06-29T21:33:51.0731477",
    "status": 201,
    "message": "Participant was created.",
    "path": "lottery/participant",
    "color": "green",
    "data": {
        "name": "Andrey",
        "age": 26,
        "city": "Saint-Petersburg"
    }
}
```

## Get all participants 
GET: http://localhost:8080/lottery/participant

Response Body
```
{
    "timestamp": "2022-06-29T21:34:29.1311462",
    "status": 200,
    "message": "All participants were found.",
    "path": "lottery/participant",
    "color": "green",
    "data": [
        {
            "name": "Andrey",
            "age": 26,
            "city": "Saint-Petersburg"
        },
        {
            "name": "Alexander",
            "age": 22,
            "city": "Saint-Petersburg"
        }
    ]
}
```

## Find winner 
GET: http://localhost:8080/lottery/start

Response Body
```
{
    "timestamp": "2022-06-29T21:37:07.4691492",
    "status": 200,
    "message": "Winner was found.",
    "path": "lottery/start",
    "color": "green",
    "data": {
        "name": "Alexander",
        "age": 22,
        "city": "Saint-Petersburg",
        "prize": 128
    }
}
```

## Get all winners 
GET: http://localhost:8080/lottery/winners

Response Body
```
{
    "timestamp": "2022-06-29T21:37:29.462147",
    "status": 200,
    "message": "All winners were found.",
    "path": "lottery/winners",
    "color": "green",
    "data": [
        {
            "name": "Alexander",
            "age": 22,
            "city": "Saint-Petersburg",
            "prize": 128
        }
    ]
}
```
