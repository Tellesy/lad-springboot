package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    Job createJob(Job job);

    Optional<Job> getJobById(Long id);

    List<Job> getJobsByQueue(String queue);

    List<Job> getPendingJobs(String queue);

    Job updateJob(Long id, Job job);

    void deleteJob(Long id);
}
