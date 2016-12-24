package by.rssfeeder.repository;

import by.rssfeeder.entity.Blog;
import by.rssfeeder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findByUser(User user);

}
