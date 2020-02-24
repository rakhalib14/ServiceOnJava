package storage.repository.point;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import storage.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;



@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    Page<Point> findAllByOrderByCreateDateDesc(Pageable pageable);
    @Query(value = "select * from point where (start_date < :currentDate and end_date > :currentDate) and active=true order by create_date desc limit 1", nativeQuery = true)
    Point getCurrent(@Param("currentDate") Date currentDate);

}

