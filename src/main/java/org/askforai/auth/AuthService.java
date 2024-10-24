package org.askforai.auth;

import java.util.Optional;

import org.askforai._core.utils.JwtUtil;
import org.askforai.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
	
	private final AuthRepository authRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	@Transactional
	public String signup(AuthRequest.SignupDTO reqDTO) {
		log.info("회원가입 시도: {}", reqDTO.getUsername());
		
		Optional<User> userOp = authRepository.findByUsername(reqDTO.getUsername());
		if (userOp.isPresent()) {
			throw new IllegalArgumentException("존재하는 username입니다");
		}
		
		String encodedPassword = passwordEncoder.encode(reqDTO.getPassword());
		User user = User.builder()
				.username(reqDTO.getUsername())
				.password(encodedPassword)
				.name(reqDTO.getName())
				.email(reqDTO.getEmail())
				.build();
		
		authRepository.save(user);
		
		String token = jwtUtil.generateToken(user.getUsername());
		log.info("회원가입 성공: {}, jwt 토큰 생성 완료.", user);
		
		return token;
	}
	
	

}
