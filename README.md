# System rejestracji do zakładu fryzjerskiego - API

## Endpointy 

### /api/serviceoptions
##### GET - pobiera wszystkie usługi
Przujmuje: nic,
Zwraca: Iterable\<ServiceOptions>
### /api/serviceoptions/{id} 
##### GET - pobiera jedną usługę
Przujmuje: id, Zwraca: Optional\<ServiceOptions>

### /api/openhours
##### GET - pobiera wszystkie definicje godzin otwarcia
Przujmuje: nic, Zwraca: Iterable\<ServiceOptions>
### /api/openhours/{id}
##### GET - pobiera jedną definicje godzin otwarcia
Przujmuje: id, Zwraca: Optional\<ServiceOptions>
### /api/openhours/recent
##### GET - pobiera aktualnie obowiązującą (nie najnowszą) definicje godzin otwarcia
Przujmuje: nic, Zwraca: ServiceOptions