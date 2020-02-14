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
}

