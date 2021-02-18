package cn.edu.tsu.blog;

import cn.edu.tsu.blog.commons.dto.ArticleDto;
import cn.edu.tsu.blog.dao.ArticleDtoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private ArticleDtoDao articleDtoDao;

    @Test
    void contextLoads() {
        ArticleDto articleDto = articleDtoDao.get(1);
        System.out.println(articleDto);

    }

}
