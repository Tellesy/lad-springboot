package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Job;
import ly.kaizen.lad.repository.JobRepository;
import ly.kaizen.lad.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> getJobsByQueue(String queue) {
        return jobRepository.findByQueueAndReservedAtIsNullOrderByAvailableAtAsc(queue);
    }

    @Override
    public List<Job> getPendingJobs(String queue) {
        return jobRepository.findByQueueAndReservedAtIsNullOrderByAvailableAtAsc(queue);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        return jobRepository.findById(id)
                .map(existingJob -> {
                    existingJob.setQueue(job.getQueue());
                    existingJob.setPayload(job.getPayload());
                    existingJob.setAttempts(job.getAttempts());
                    existingJob.setAvailableAt(job.getAvailableAt());
                    existingJob.setReservedAt(job.getReservedAt());
                    return jobRepository.save(existingJob);
                })
                .orElseThrow(() -> new IllegalArgumentException("Job not found with id: " + id));
    }

    @Override
    public void deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Job not found with id: " + id);
        }
    }
}
