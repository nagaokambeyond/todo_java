package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="todo")
public class ToDo {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column
    /*
    @GeneratedValue(generator = "todo_comments")  // Generator 名 (何でもよい)
    @TableGenerator(
            name = "todo_comments",  // @GeneratedValue.generator と合わせる
            table = "sqlite_sequence",  // SQLite のシーケンステーブル名と合わせる
            pkColumnName = "name",  // sqlite_sequence のシーケンスカラム名 (name 固定)
            valueColumnName = "seq",  // sqlite_sequence のシーケンス値名 (seq 固定)
            pkColumnValue = "comments",  // sqlite_sequence.name に格納されている値 (テーブル名)
            initialValue = 1,  // シーケンス初期値. 多くの場合 1
            allocationSize = 1  // AUTO INCREMENT される場合の増減値. 何故かデフォルト 50 になっているので 1 を指定する
    )    
    */
    private Long id;

    @Column(nullable = false)
    private Long status;

    @Column(nullable = false)
    private String message;

    /*
public ToDo(Long status, String message){
    this.status = status;
    this.message = message;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatus(){
        return status;
    }

    public void setStatus(Long status){
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
    */
}
