package ly.kaizen.lad.service;

import ly.kaizen.lad.model.ActivityLog;

import java.util.List;
import java.util.Optional;

public interface ActivityLogService {

    ActivityLog createActivityLog(ActivityLog activityLog);

    Optional<ActivityLog> getActivityLogById(Long id);

    List<ActivityLog> getAllActivityLogs();

    List<ActivityLog> getActivityLogsBySubject(String subjectType, Long subjectId);

    void deleteActivityLog(Long id);
}
