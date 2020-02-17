package storage.service.point;

import storage.entity.point.Point;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import storage.repository.point.PointRepository;
import java.util.List;
import java.util.Optional;

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


}

