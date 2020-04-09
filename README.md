# System rejestracji do zakładu fryzjerskiego - API

## Logowanie
### /api/login
##### POST - Logowanie użytkownika
Przyjmuje: parametry username i password, Zwraca: token JWT

## Endpointy

### /api/services
##### GET - pobiera wszystkie usługi
Przyjmuje: nic,
Zwraca: Iterable\<ServiceOptions>
### /api/services/{id} 
##### GET - pobiera jedną usługę
Przyjmuje: id, Zwraca: Optional\<ServiceOptions>

### /api/openhours
##### GET - pobiera wszystkie definicje godzin otwarcia
Przyjmuje: nic, Zwraca: Iterable\<ServiceOptions>
### /api/openhours/{id}
##### GET - pobiera jedną definicje godzin otwarcia
Przyjmuje: id, Zwraca: Optional\<ServiceOptions>
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


