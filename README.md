# System rejestracji do zakładu fryzjerskiego - API

## Logowanie
### /api/login
##### POST - Logowanie użytkownika
Przyjmuje: JSON username i password, Zwraca: token JWT
```
{
	"username":"admin",
	"password":"admin1"
}
```
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

### /api/whoami 
##### GET - pobiera username zalogowanego użytkownika :lock:
Przyjmuje: nic, Zwraca: StringResponse z loginem

### /api/dashboard/{date} 
##### GET - pobiera tydzień od podanej daty :lock:
Przyjmuje: opcjonalnie date, w przypadku braku lub błedu parsowania zwróci tydziń od dziś, Zwraca: Iterable\<WorkDay>

### /api/day/{date}
##### GET - pobiera dzień :lock:
Przyjmuje: date, Zwraca: WorkDay
##### PATCH - zmienia godziny otwarcia w tym dniu :lock:
Przyjmuje: date i JSON z nowymi gozinami, Zwraca: WorkDay
```
{
	"openFrom":300,
	"openTo":600
}
```