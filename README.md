# Holidays API – Contenerización API + BD

## Cómo ejecutar (Docker Compose)
```bash
docker compose up --build
```
Servicios:
- API: http://localhost:8080
- PostgreSQL: postgres:5432 (usuario: holidays / pass: holidays / DB: holidaysdb)

## Endpoints
- Validar si una fecha es festiva
  ```http
  GET /api/holidays/validate?countryId=1&date=2023-06-12
  ```

- Listar festivos del año
  ```http
  GET /api/holidays/1/2023
  ```

- Generar y guardar calendario del año
  ```http
  POST /api/calendar/generate?countryId=1&year=2023
  ```

- Consultar calendario clasificado
  ```http
  GET /api/calendar/1/2023
  ```

## Pruebas rápidas (cURL)
```bash
# 1) ¿12 de junio de 2023 es festivo?
curl "http://localhost:8080/api/holidays/validate?countryId=1&date=2023-06-12"

# 2) Lista de festivos 2023
curl "http://localhost:8080/api/holidays/1/2023"

# 3) Generar calendario 2023
curl -X POST "http://localhost:8080/api/calendar/generate?countryId=1&year=2023"

# 4) Obtener calendario 2023
curl "http://localhost:8080/api/calendar/1/2023"
```

## Semillas
- País: Colombia (id = 1)
- Tipos de festivos (1..4)
- Festivos de Colombia precargados (fijos, puente, basados en Pascua)
```
