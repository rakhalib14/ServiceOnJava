package storage.repository.point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.entity.point.Point;
import org.springframework.data.jpa.repository.Query;
import java.util.List;



@Repository
public interface PointRepository extends JpaRepository<Announcement, Long> {
    @Query(value = "select * from point where active=true limit 1", nativeQuery = true)
    Announcement getLast();
    List<Announcement> findByActive(Boolean active);
}

