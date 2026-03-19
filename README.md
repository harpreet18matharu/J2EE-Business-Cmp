# Employee REST API (Jakarta EE)

This is the same structure as the CoffeeShop REST API, but using **Employee** data.

## Entity
Employee fields:
- `id` (auto-generated)
- `firstName`
- `lastName`
- `email`

## Security (Basic Auth)
Two hardcoded users in `BasicAuthFilter`:
- **admin / adminpass**  -> role `admin` (can do CRUD)
- **user / userpass**    -> role `user`  (READ only)

## Endpoints (base path: `/api`)
- `GET /api/employees` (user + admin)
- `GET /api/employees/{id}` (user + admin)
- `POST /api/employees` (admin only)
- `PUT /api/employees/{id}` (admin only)
- `DELETE /api/employees/{id}` (admin only)

## Example JSON
```json
{
  "firstName": "Harpreet",
  "lastName": "Kaur",
  "email": "harpreet@example.com"
}
```

## Run / Deploy
This project builds a **WAR** (Jakarta EE). Deploy it to a Jakarta EE server (e.g., Payara, WildFly, GlassFish).

Typical build:
- Windows: `mvnw.cmd clean package`
- Mac/Linux: `./mvnw clean package`

Then deploy the generated `target/*.war` to your server.

## Postman
In Postman, set Authorization to **Basic Auth** and use either:
- admin/adminpass (to test POST/PUT/DELETE)
- user/userpass (to test GET only)
