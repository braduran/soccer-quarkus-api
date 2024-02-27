## Soccer API
API that allow us manage live and history soccer matches. \
_Java version_: 21 \
_Framework_: quarkus \
_Database_: H2

It uses the next quarkus extensions:
- Hibernate ORM with Panache
- H2 (Inmemory DB)
- Hibernate Validator
- Reactive Jackson
- Reactive JSON-B

### Running locally
1. Clone repository
2. Run application into cloned repository:
* Windows
> ./gradlew.bat quarkusDev
* Linux
> ./gradlew quarkusDev
3. Postman collection in **src/main/resources/soccer api_collection.json**

### Enpoints
#### 1. Create match
> POST http://localhost:8080/match/create
```json
{
    "local": "Barcelona",
    "visitor": "Milan",
    "stadium": "Camp nou",
    "competition": "Champions",
    "attendeesNumber": 25000,
    "date": "2024-09-01"
}
```
Response
```json
{
  "attendeesNumber": 25000,
  "competition": "Champions",
  "date": "2024-09-01",
  "local": "Barcelona",
  "minute": 0,
  "stadium": "Camp nou",
  "state": "START",
  "visitor": "Milan"
}
```

#### 2. Update match
> PUT http://localhost:8080/match/update
```json
{
      "local": "Barcelona",
      "visitor": "Milan",
      "date": "2024-09-01",
      "localScore": 1,
      "visitorScore": 0,
      "minutes": 20,
      "state": "IN_PROGRESS"
}
```
Response
```json
{
  "attendeesNumber": 25000,
  "competition": "Champions",
  "date": "2024-09-01",
  "local": "Barcelona",
  "minute": 20,
  "stadium": "Camp nou",
  "state": "IN_PROGRESS",
  "visitor": "Milan"
}
```

#### 3. List matches
> GET http://localhost:8080/match/list

Response
```json
[
  {
    "attendeesNumber": 25000,
    "competition": "Champions",
    "date": "2024-09-01",
    "local": "Barcelona",
    "minute": 20,
    "stadium": "Camp nou",
    "state": "IN_PROGRESS",
    "visitor": "Milan"
  }
  ...
]
```

#### 4. Filter matches by state
> GET http://localhost:8080/match/state \
> **params:** state=IN_PROGRESS

Response
```json
[
  {
    "attendeesNumber": 25000,
    "competition": "Champions",
    "date": "2024-09-01",
    "local": "Barcelona",
    "minute": 20,
    "stadium": "Camp nou",
    "state": "IN_PROGRESS",
    "visitor": "Milan"
  }
  ...
]
```

#### 5. History of team matches 
> GET http://localhost:8080/match/team \
> **params:** team=_Milan_&page=_1_

Response
```json
{
  "page": 1,
  "totalPages": 2,
  "data": [
    {
      "attendeesNumber": 25000,
      "competition": "Champions",
      "date": "2024-09-01",
      "id": 1,
      "local": "Barcelona",
      "localScore": 1,
      "minutes": 20,
      "stadium": "Camp nou",
      "state": "IN_PROGRESS",
      "visitor": "Milan",
      "visitorScore": 0
    }
    ...
  ]
}
```