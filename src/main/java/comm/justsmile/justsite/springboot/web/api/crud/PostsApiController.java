package comm.justsmile.justsite.springboot.web.api.crud;

import comm.justsmile.justsite.springboot.web.api.crud.dto.PostsListResponseDto;
import comm.justsmile.justsite.springboot.web.api.crud.dto.PostsResponseDto;
import comm.justsmile.justsite.springboot.web.api.crud.dto.PostsSaveRequestDto;
import comm.justsmile.justsite.springboot.web.api.crud.dto.PostsUpdateRequestDto;
import comm.justsmile.justsite.springboot.web.api.crud.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody final PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable final Long id, @RequestBody final PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable final Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable final Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}