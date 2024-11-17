package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.ActivityLog;
import ly.kaizen.lad.repository.ActivityLogRepository;
import ly.kaizen.lad.service.ActivityLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public ActivityLog createActivityLog(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    @Override
    public Optional<ActivityLog> getActivityLogById(Long id) {
        return activityLogRepository.findById(id);
    }

    @Override
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogRepository.findAll();
    }

    @Override
    public List<ActivityLog> getActivityLogsBySubject(String subjectType, Long subjectId) {
        return activityLogRepository.findAll()
                .stream()
                .filter(log -> log.getSubjectType().equals(subjectType) && log.getSubjectId().equals(subjectId))
                .toList();
    }

    @Override
    public void deleteActivityLog(Long id) {
        if (activityLogRepository.existsById(id)) {
            activityLogRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ActivityLog not found with id: " + id);
        }
    }
}
