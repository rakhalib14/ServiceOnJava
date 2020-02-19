package storage.controller;

import storage.entity.point.Point;
import storage.service.point.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/points")
public class PointController {
    
    @Autowired
    PointService pointService;
    
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(pointService.findAll());
      }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent() {
        return ResponseEntity.ok(pointService.getCurrentActive());
     }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Point point) {
        return ResponseEntity.ok(pointService.create(point));
     }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Point point) {
        return ResponseEntity.ok(pointService.edit(point));
     }
    @PostMapping("/{id}/active")
    public ResponseEntity<?> active(@PathVariable Long id) {
        return ResponseEntity.ok(announcementService.active(id));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<?> close(@PathVariable Long id) {
        return ResponseEntity.ok(announcementService.close(id));
    }

    @PostMapping("/{id}/repeat")
    public ResponseEntity<?> repeat(@PathVariable Long id) {
        return ResponseEntity.ok(announcementService.repeat(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
