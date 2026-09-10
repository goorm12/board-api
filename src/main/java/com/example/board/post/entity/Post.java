package com.example.board.post.entity;

// jakarta.persistence 패키지에 있는 JPA 어노테이션을 가져옵니다.
// * 표시는 이 패키지의 여러 타입(Entity, Table, Id, Column 등)을 한 번에 import한다는 뜻입니다.
import jakarta.persistence.*;

// @Entity: 이 클래스가 JPA에서 관리하는 엔티티임을 선언합니다.
// 엔티티 객체 하나는 posts 테이블의 행(row) 하나와 대응합니다.
@Entity

// @Table: 이 엔티티와 연결할 데이터베이스 테이블을 설정합니다.
// name = "posts": 실제 테이블 이름을 posts로 지정합니다.
@Table(name = "posts")
public class Post {

    // @Id: id 필드를 테이블의 기본키(Primary Key) 컬럼으로 지정합니다.
    // 기본키는 각각의 게시글을 중복 없이 구분하는 값입니다.
    @Id

    // @GeneratedValue: 새로운 게시글을 저장할 때 기본키 값을 자동으로 생성합니다.
    // strategy: 기본키를 어떤 방식으로 생성할지 지정하는 속성입니다.
    // GenerationType.IDENTITY: PostgreSQL의 IDENTITY 기능을 사용해 DB가 id를 1, 2, 3처럼 증가시킵니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column: 이 필드를 테이블의 컬럼(column)과 연결하고 컬럼 설정을 지정합니다.
    // 컬럼은 테이블에서 id, title, content처럼 데이터의 항목을 나타냅니다.
    // nullable = false: DB에 NULL 값이 저장되는 것을 허용하지 않습니다. 즉, 제목은 필수입니다.
    // length = 200: DB 문자열 컬럼의 최대 길이를 200자로 설정합니다.
    @Column(nullable = false, length = 200)
    private String title;

    // nullable = false: 본문에 NULL 값이 저장되는 것을 허용하지 않습니다.
    // columnDefinition = "TEXT": 자동 생성되는 DB 컬럼의 자료형을 PostgreSQL TEXT로 직접 지정합니다.
    // TEXT는 게시글 본문처럼 길이가 긴 문자열을 저장할 때 사용합니다.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // nullable = false: 작성자에 NULL 값이 저장되는 것을 허용하지 않습니다.
    // length = 50: DB 문자열 컬럼의 최대 길이를 50자로 설정합니다.
    @Column(nullable = false, length = 50)
    private String author;

    // JPA가 데이터베이스 조회 결과로 객체를 생성할 때 사용하는 기본 생성자입니다.
    // 외부에서 함부로 호출하지 못하도록 protected 접근 제한자를 사용합니다.
    protected Post() {
    }

    // 게시글을 새로 만들 때 사용하는 생성자입니다.
    // id는 DB가 자동 생성하므로 생성자 매개변수에 포함하지 않습니다.
    // this.title은 현재 객체의 필드이고 title은 생성자로 전달받은 값입니다.
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // private인 id 값을 외부에서 읽기 위한 getter입니다.
    public Long getId() {
        return id;
    }

    // private인 title 값을 외부에서 읽기 위한 getter입니다.
    public String getTitle() {
        return title;
    }

    // private인 content 값을 외부에서 읽기 위한 getter입니다.
    public String getContent() {
        return content;
    }

    // private인 author 값을 외부에서 읽기 위한 getter입니다.
    public String getAuthor() {
        return author;
    }
}
