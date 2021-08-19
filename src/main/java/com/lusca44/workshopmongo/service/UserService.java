package com.lusca44.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lusca44.workshopmongo.domain.User;
import com.lusca44.workshopmongo.dto.UserDTO;
import com.lusca44.workshopmongo.repository.UserRepository;
import com.lusca44.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public User insert(User user) {
		return userRepo.insert(user);
	}

	public void delete(String id) {
		findById(id);
		userRepo.deleteById(id);
	}

	public User update(User user) {
		Optional<User> newUser = userRepo.findById(user.getId());
		updateData(newUser, user);
		return userRepo.save(newUser.get());
	}
	
	private void updateData(Optional<User> newUser, User user) {
		newUser.get().setName(user.getName());
		newUser.get().setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	
}
