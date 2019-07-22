package com.boardgais.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.boardgais.batch.model.User;

@Component
public class UserReader {
	
	@Bean
	public FlatFileItemReader<User> fileItemReader(@Value("${inputFile}") Resource resource)
	{
		FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<User>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}
	
	public LineMapper<User> lineMapper()
	{
		DefaultLineMapper<User> studentLineMapper = new DefaultLineMapper<>();
		 
        LineTokenizer studentLineTokenizer = createStudentLineTokenizer();
        studentLineMapper.setLineTokenizer(studentLineTokenizer);
 
        FieldSetMapper<User> studentInformationMapper = createStudentInformationMapper();
        studentLineMapper.setFieldSetMapper(studentInformationMapper);
 
        return studentLineMapper;
		
	}
	
	 private LineTokenizer createStudentLineTokenizer() {
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(",");
        studentLineTokenizer.setNames(new String[]{"id", "name", "dept", "salary"});
        return studentLineTokenizer;
    }
 
    private FieldSetMapper<User> createStudentInformationMapper() {
        BeanWrapperFieldSetMapper<User> studentInformationMapper = new BeanWrapperFieldSetMapper<>();
        studentInformationMapper.setTargetType(User.class);
        return studentInformationMapper;
    }

}
