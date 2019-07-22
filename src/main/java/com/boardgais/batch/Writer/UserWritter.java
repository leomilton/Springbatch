package com.boardgais.batch.Writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.boardgais.batch.model.User;
import com.boardgais.batch.repository.UserRepository;



@Component
public class UserWritter {
	
	@Autowired
	UserRepository userRepository;
	
	@Bean
	public ItemWriter<User> writter()
	{
		return item -> {
			userRepository.saveAll(item);
		};
	}

}
