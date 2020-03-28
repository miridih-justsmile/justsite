package comm.justsmile.justsite.springboot.web.rest_api.crud_api.repository;

import comm.justsmile.justsite.springboot.web.rest_api.crud_api.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
