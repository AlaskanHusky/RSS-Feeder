package by.rssfeeder.repository;

import by.rssfeeder.entity.Blog;
import by.rssfeeder.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByBlog(Blog blog, Pageable pageable);

    Item findByBlogAndLink(Blog blog, String link);

}
