# swagger-ui
http://localhost:8090/swagger-ui/index.html

# 正常系
- POST http://localhost:8090/api/login
- GET http://localhost:8090/api/v1/todos
- GET http://localhost:8090/api/v1/todos/1
- POST http://localhost:8090/api/v1/todos
{
  "message":"aaaa",
  "statusId":1
}
- PUT http://localhost:8090/api/v1/todos
{
  "id":2,
  "done":true
}
- http://localhost:8090/api/v1/todos/17

# 異常系
- POST http://localhost:8090/api/v1/todos
{
  "message":"aaaa"
}
- POST http://localhost:8090/api/v1/todos
{
  "message":""
}
