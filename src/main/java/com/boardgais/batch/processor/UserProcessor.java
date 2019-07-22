package com.boardgais.batch.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.boardgais.batch.model.User;


@Component
public class UserProcessor {
	
	@Bean
	public ItemProcessor<User, User> processor()
	{
		return item -> {
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("001", "IT");
			map.put("002", "CSC");
			map.put("003", "MECH");
			
			String dept = item.getDept();
			item.setDept(map.get(dept));
			
			return item;
		};
		
	}

}
