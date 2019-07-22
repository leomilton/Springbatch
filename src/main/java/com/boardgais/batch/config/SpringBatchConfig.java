package com.boardgais.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.boardgais.batch.model.User;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class SpringBatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired 
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job job(ItemReader<User> itemReader, ItemProcessor<User, User> processor, ItemWriter<User> writer)
	{
		Step step = stepBuilderFactory.get("ETL-File-Load")
				.<User,User>chunk(100)
				.reader(itemReader)
				.processor(processor)
				.writer(writer)
				.build();
		
		return jobBuilderFactory
				.get("ETL-Job")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();
	}

}
