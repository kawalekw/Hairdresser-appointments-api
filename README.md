# System rejestracji do zakładu fryzjerskiego - API

## Endpointy 

### /api/serviceoptions
##### GET - pobiera wszystkie usługi
Przyjmuje: nic,
Zwraca: Iterable\<ServiceOptions>
### /api/serviceoptions/{id} 
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