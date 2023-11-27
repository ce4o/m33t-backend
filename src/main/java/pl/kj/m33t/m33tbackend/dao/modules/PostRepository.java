package pl.kj.m33t.m33tbackend.dao.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kj.m33t.m33tbackend.entity.modules.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}