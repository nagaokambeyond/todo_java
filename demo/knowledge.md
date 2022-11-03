# Spring Bootバージョンアップでわかったこと
- JpaRepository.getById()→JpaRepository.getReferenceById()
  - https://spring.pleiades.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html#getById-ID-

# フィールドインジェクションより、コンストラクタインジェクション
- @Service、@Repositoryを利用する際、フィールドに@AutoWiredは推奨されない
- ☓：フィールドインジェクション
  - finalではないため再代入される可能性がある
    ```java
    @AutoWired
    private ToDoRepository todo;
    ```
- ○：コンストラクタインジェクション
  - finalで宣言するため、再代入ができなくなる
  - lombokの@RequiredArgsConstructorを使うと、finalのフィールドに対するコンストラクタを自動生成してくれる
    ```java
    @RequiredArgsConstructor
    @Service
    public class ToDoServiceImpl implements ToDoService {
      final ToDoRepository todo;
    ```
# @PutMapping、@GetMappingがついているメソッドに、スコープながくてもエラーにならない

```java
@ApiResponses(value = {
  @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ToDoResponse.class)))
})
@Operation(summary = "ToDoを取得する")
@GetMapping("{id}")
ResponseEntity<ToDoResponse> getToDo(@Parameter(required = true, description = "条件") @PathVariable final Long id) {
  return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
}
```

# interfaceをimplementsしたメソッドは、引数をfinalにしても問題ない

```java
@Transactional
@Override
public void done(final ToDoDoneRequest request) {
  var entity = todo.getReferenceById(request.getId());
  if (request.getDone()) {
    entity.setDone(1);
  } else {
    entity.setDone(0);
  }
  final var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
  entity.setUpdateDatetime(LocalDateTime.now().format(dtf));
  todo.save(entity);
}
```
