package storage.service.point;

import storage.entity.point.Point;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import storage.repository.point.PointRepository;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class PointService {
    @Autowired
    PointRepository pointRepository;

    public Point findById(Long id) {
        Optional<Point> optionalPoint = pointRepository.findById(id);
        if(optionalPoint.isPresent()) {
            return optionalPoint.get();
        }
        return null;
    }
    
    public List<Point> findAll() {
        List<Point> points = pointRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return points;
    }
    
    public Point getCurrentActive() {
        Point point = pointRepository.getLast();
        if(point != null && point.getActive()) {
            return point;
        }
        return null;
    }
    
    public Point create(Point point) {
        Point current = getCurrentActive();
        if(current != null) {
            close(current);
        }
        point.setActive(true);
        point.setCreateDate(new Date());
        return pointRepository.save(point);
    }
    
    public Point edit(Point point) {
        Point dbPoint = findById(point.getId());
        dbPoint.setTitle(point.getTitle());
        dbPoint.setContent(point.getContent());
        return pointRepository.save(dbPoint);
    }

    public Point close(Long id) {
        Point point = findById(id);
        return close(point);
    }

    public Point active(Long id) {
        List<Point> activePoints = pointRepository.findByActive(true);
        for(Point point: activePoints) {
            point.setActive(false);
        }
        pointRepository.saveAll(activePoints);

        Point point = findById(id);
        point.setActive(true);
        return pointRepository.save(point);
    }

    public Point close(Point point) {
        point.setActive(false);
        return pointRepository.save(point);
    }

    public Point repeat(Long id) {
        Point point = findById(id);
        close(point);
        Point newPoint = new Point();
        newPoint.setTitle(point.getTitle());
        newPoint.setContent(point.getContent());
        return create(newPoint);
    }

    public void delete(Long id) {
        pointRepository.deleteById(id);
    }

}

