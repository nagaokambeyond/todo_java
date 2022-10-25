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
