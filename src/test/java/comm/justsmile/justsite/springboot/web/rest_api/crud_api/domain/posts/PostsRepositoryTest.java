package comm.justsmile.justsite.springboot.web.rest_api.crud_api.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    private final static String TITLE = "테스트 게시글";
    private final static String CONTENT = "테스트 본문";
    private final static String AUTHOR = "테스트 작성자";

    @Autowired
    PostsRepository postsRepository;

    @Before
    public void 사전작업() {
        postsRepository.save(Posts.builder().title(TITLE).content(CONTENT).author("wjdrnsl@gmail.com").build());
    }

    @Test
    public void 게시글저장_불러오기() {
        final List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        Assert.assertEquals(posts.getTitle(), TITLE);
        Assert.assertEquals(posts.getContent(), CONTENT);

    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        postsRepository.save(Posts.builder().title(TITLE).content(CONTENT).author(AUTHOR).build());

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        System.out.println("createData : " + posts.getCreatedDate() + "\n modifyDate : " + posts.getModifiedDate());
        Assert.assertTrue(posts.getCreatedDate().isBefore(tomorrow) && posts.getCreatedDate().isAfter(yesterday));
        Assert.assertTrue(posts.getModifiedDate().isBefore(tomorrow) && posts.getModifiedDate().isAfter(yesterday));
    }

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }
}
