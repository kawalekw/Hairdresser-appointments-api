# System rejestracji do zakładu fryzjerskiego - API

## Logowanie
### /api/login
##### POST - Logowanie użytkownika
Przyjmuje: parametry username i password, Zwraca: token JWT

## Endpointy

### /api/services
##### GET - pobiera wszystkie usługi
Przyjmuje: nic, Zwraca: Iterable\<ServiceOptions>
##### POST - dodaje nową usługę :lock:
Przyjnuje: JSON - ServiceOption, Zwraca: ServiceOption
```
{
    "name": "Walenie wiadra",
    "duration": 20
}
```
### /api/services/{id} 
##### GET - pobiera jedną usługę
Przyjmuje: id, Zwraca: ServiceOptions

##### DELETE - usuwa wybraną usługę :lock:
Przyjmuje: id, Zwraca: StringResponse

### /api/openhours
##### GET - pobiera wszystkie definicje godzin otwarcia
Przyjmuje: nic, Zwraca: Iterable\<ServiceOptions>
##### POST - dodaje nową definicję godzin otwarcia :lock:
Przyjmuje: JSON - OpenHours, Zwraca: Obiekt Openhours
```
{
 	"appliesFrom": "2020-04-18",
 	"openFrom": [0,0,0,0,0,0,0],'
 	"openTo": [0,0,0,0,0,0,0]
 } 
```
### /api/openhours/{id}
##### GET - pobiera jedną definicje godzin otwarcia
Przyjmuje: id, Zwraca: ServiceOptions
##### DELETE - usuwa wybraną definicję :lock:
Przyjmuje: id, Zwraca: StringResponse
### /api/openhours/recent
##### GET - pobiera aktualnie obowiązującą (nie najnowszą) definicje godzin otwarcia
Przyjmuje: nic, Zwraca: ServiceOptions

### /api/schedule/{date}/{id}
##### GET - pobiera wolne godziny do rejestracji 
Przyjmuje: date - data w formacie YYYY-MM-DD, id - id usługi, Zwraca: Iterable\<String>

### /api/whoami :lock:
##### GET - pobiera username zalogowanego użytkownika
Przyjmuje: nic, Zwraca: StringResponse z loginem

### /api/dashboard/{date} :lock:
##### GET - pobiera tydzień od podanej daty
Przyjmuje: opcjonalnie date, w przypadku braku lub błedu parsowania zwróci tydziń od dziś, Zwraca: Iterable\<WorkDay>


