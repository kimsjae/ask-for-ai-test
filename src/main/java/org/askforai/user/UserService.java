package org.askforai.user;

import java.util.Optional;

import org.askforai._core.errors.exception.Exception401;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	
	private final UserRepository userRepository;
	
	// 회원가입
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
	
	// 로그인
	public User login(UserRequest.LoginDTO reqDTO) {
		log.info("로그인 시도: username = {}, password = {}", reqDTO.getUsername(), reqDTO.getPassword());
		
		Optional<User> userOp = userRepository.findByUsername(reqDTO.getUsername());
		
		if (userOp.isPresent()) {
			User user = userOp.get();
			if (user.getPassword().equals(reqDTO.getPassword())) {
				log.info("로그인 성공!!!: username = {}, password = {}", reqDTO.getUsername(), reqDTO.getPassword());
				return user;
				
			} else {
				log.warn("로그인 실패!!!: 아이디 혹은 비밀번호가 틀렸습니다.");
				throw new Exception401("아이디 또는 비밀번호가 틀렸습니다.");
			}
		} else {
			log.warn("로그인 실패!!!: 아이디 혹은 비밀번호가 틀렸습니다.");
			throw new Exception401("아이디 또는 비밀번호가 틀렸습니다.");
		}
	
	}
	
}
