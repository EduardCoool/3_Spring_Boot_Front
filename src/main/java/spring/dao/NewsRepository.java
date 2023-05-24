package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
