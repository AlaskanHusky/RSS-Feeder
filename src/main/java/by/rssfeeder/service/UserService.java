package by.rssfeeder.service;

import by.rssfeeder.entity.Blog;
import by.rssfeeder.entity.Item;
import by.rssfeeder.entity.Role;
import by.rssfeeder.entity.User;
import by.rssfeeder.repository.BlogRepository;
import by.rssfeeder.repository.ItemRepository;
import by.rssfeeder.repository.RoleRepository;
import by.rssfeeder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public User findOneWithBlogs(int id) {
        User user = findOne(id);
        List<Blog> blogs = blogRepository.findByUser(user);
        for (Blog blog : blogs) {
            List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Sort.Direction.DESC, "publishedDate"));
            blog.setItems(items);
        }
        user.setBlogs(blogs);
        return user;
    }

    public User findOneWithBlogs(String name) {
        User user = userRepository.findByName(name);
        return findOneWithBlogs(user.getId());
    }

    public void save(User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<Role>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);

        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    public User findOne(String username) {
        return userRepository.findByName(username);
    }
}
