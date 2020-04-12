package comm.justsmile.justsite.springboot.web.rest_api.crud_api.dto;

import comm.justsmile.justsite.springboot.web.rest_api.crud_api.domain.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(final Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}