package br.ufc.mandacaru5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru5.model.Property;
import br.ufc.mandacaru5.model.Person;
import br.ufc.mandacaru5.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void save(int id, Person entity) {
		if (id != 0) {
			entity.setId(id);
		}
		else if(id==0)
		{
			entity.encodePassword();
		}

		userRepository.save(entity);
	}
	
	public void update(int id, Person entity) {
		Person user = find(id);		
		user.setName(entity.getName());
		
		userRepository.save(user);				
	}

	public void delete(int id) {
		Person user = find(id);
		userRepository.delete(user);
	}

	public Person find(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Person> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

	public List<Person> findAll() {
		return userRepository.findAll();
	}

	public Person findByName(String str) {
		if (str.length() < 1) {
			return null;
		}
		return userRepository.findFirstByName(str);
	}
	

}
