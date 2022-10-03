# swagger-ui
http://localhost:8090/swagger-ui/index.html

# Spring Bootバージョンアップでわかったこと
- JpaRepository.getById()→JpaRepository.getReferenceById()
  - https://spring.pleiades.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html#getById-ID-

# 正常系
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
