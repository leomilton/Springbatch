package com.boardgais.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
public class BatchApplication {
	
	@Autowired JobLauncher jobLauncher;
	
	@Autowired Job job;

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}
	
	@Scheduled(cron = "${cron.schedule}")
	  public void runJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters param =
		          new JobParametersBuilder()
		              .addString("partitionerJob", String.valueOf(System.currentTimeMillis()))
		              .toJobParameters();

		      JobExecution execution = jobLauncher.run(job, param);
		      System.out.println(execution.getStatus()); 
	  }
}