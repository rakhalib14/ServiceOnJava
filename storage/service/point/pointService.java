package storage.service.point;

import storage.entity.point.Point;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import storage.repository.point.PointRepository;
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
    
    public Page<Point> getAllPoints(Pageable pageable)
    {
        Page<Point> pagedResult = pointRepository.findAllByOrderByCreateDateDesc(pageable);
        return pagedResult;
    }
    
    public Point getActive() {
        Point point = pointRepository.getCurrent(new Date());
        return announcement;

    }
    
    public Point create(Point point) {
        point.setActive(true);
        point.setCreateDate(new Date());
        return pointRepository.save(point);
    }
    
    public Point edit(Point point) {
        Point dbPoint = findById(point.getId());
        dbPoint.setStartDate(point.getStartDate());
        dbPoint.setEndDate(point.getEndDate());
        dbPoint.setTitle(point.getTitle());
        dbPoint.setContent(point.getContent());
        dbPoint.setActive(point.getActive());
        return pointRepository.save(dbPoint);
    }


    public Point repeat(Long id) {
        Point point = findById(id);
        Point newPoint = new Point();
        newPoint.setTitle(point.getTitle());
        newPoint.setContent(point.getContent());
        newPoint.setStartDate(point.getStartDate());
        newPoint.setEndDate(point.getEndDate());
        return create(newPoint);
    }

    public void delete(Long id) {
        pointRepository.deleteById(id);
    }

}

