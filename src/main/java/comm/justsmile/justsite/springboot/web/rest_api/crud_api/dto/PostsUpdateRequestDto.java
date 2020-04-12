package comm.justsmile.justsite.springboot.web.rest_api.crud_api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(final String title, final String content) {
        this.title = title;
        this.content = content;
    }
}