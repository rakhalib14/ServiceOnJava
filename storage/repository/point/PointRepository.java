package storage.repository.point;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import storage.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;



@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    Page<Point> findAllByOrderByCreateDateDesc(Pageable pageable);
    @Query(value = "select * from point where active=true limit 1", nativeQuery = true)
    Point getLast();
    List<Point> findByActive(Boolean active);
}

