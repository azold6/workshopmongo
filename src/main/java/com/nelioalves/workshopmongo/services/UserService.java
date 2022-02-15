package com.nelioalves.workshopmongo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.nelioalves.workshopmongo.services.exceptions.ObjectNotFoundException;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) throws ObjectNotFoundException {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}

	public User insert(User obj){
		return repo.save(obj);
	}

	public User fromDto(UserDTO objDTO){
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

	public void delete(String id){
		Optional<User> obj = repo.findById(id);
		repo.delete(obj.orElseThrow(() -> new ObjectNotFoundException("Não encontrado")));
	}

	//ARRUMAR AMANHÃ
	public void update(User obj){
		Optional<User> temp = repo.findById(obj.getId());
		temp.get().setName(obj.getName());
		temp.get().setEmail(obj.getEmail());
		repo.delete(temp.get());
		repo.save(temp.get());
	}
}
