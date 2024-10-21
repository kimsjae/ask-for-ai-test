package org.askforai.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	@Transactional
	public User registerUser(UserRequest.RegisterDTO reqDTO) {
		Optional<User> userOp = userRepository.findByUsername(reqDTO.getUsername());

//		TODO
//		if (userOp.isPresent()) {
//			throw new Exception;
//		}
		
		User user = new User(reqDTO);
		return userRepository.save(user);
	}
	
}
