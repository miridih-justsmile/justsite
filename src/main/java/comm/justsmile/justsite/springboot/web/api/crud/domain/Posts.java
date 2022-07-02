package comm.justsmile.justsite.springboot.web.api.crud.domain;

import comm.justsmile.justsite.springboot.web.global.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(final String title, final String content, final String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(final String title, final String content) {
        this.title = title;
        this.content = content;
    }
}
