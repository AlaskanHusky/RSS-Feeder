package by.rssfeeder.service;

import by.rssfeeder.entity.Blog;
import by.rssfeeder.entity.Role;
import by.rssfeeder.entity.User;
import by.rssfeeder.repository.BlogRepository;
import by.rssfeeder.repository.ItemRepository;
import by.rssfeeder.repository.RoleRepository;
import by.rssfeeder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class InitDbService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init() {

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        User userAdmin = new User();
        userAdmin.setEnabled(true);
        userAdmin.setName("admin");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userAdmin.setPassword(encoder.encode("admin"));
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleAdmin);
        roles.add(roleUser);
        userAdmin.setRoles(roles);
        userRepository.save(userAdmin);

        Blog blogSerials = new Blog();
        blogSerials.setName("TUT.by Grodno");
        blogSerials.setUrl("https://news.tut.by/rss/geonews/grodno.rss");
        blogSerials.setUser(userAdmin);
        blogRepository.save(blogSerials);

    }

}
