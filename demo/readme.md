# swagger-ui
http://localhost:8090/swagger-ui/index.html

# ログイン
- POST http://localhost:8090/api/login
```
X-AUTH-TOKEN
```

# X-AUTH-TOKEN
- TokenExpiredException確認用
  ```
  Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjb20uZXhhbXBsZS5kZW1vLnRlc3QiLCJleHAiOjE2NjY3MTc3NjQsImlhdCI6MTY2NjcxNzE2NCwidXNlcm5hbWUiOiJ1c2VyIn0.qrgxILGEwdTy4yijEWd1v25vueGJ4RHy229eN40P1rw
  ```

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
