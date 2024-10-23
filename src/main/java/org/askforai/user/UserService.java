package org.askforai.user;

import org.askforai._core.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	// 회원가입
	@Transactional
	public User signup(UserRequest.SignupDTO reqDTO) {
		log.info("회원가입 시도: {}", reqDTO.getUsername());
		if (userRepository.findByUsername(reqDTO.getUsername()).isPresent()) {
			log.warn("회원가입 실패: 사용자 이름이 이미 존재합니다. {}", reqDTO.getUsername());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자 이름이 이미 존재합니다.");
		}
		
		User user = User.builder()
				.username(reqDTO.getUsername())
				.password(passwordEncoder.encode(reqDTO.getPassword()))
				.email(reqDTO.getEmail())
				.build();
		
		userRepository.save(user);
		log.info("회원가입 성공: {}", reqDTO.getUsername());
		
		return user;
	}
	
	// 로그인
	public String signin(UserRequest.SigninDTO reqDTO) {
	    log.info("로그인 시도: {}", reqDTO.getUsername());
	    User user = userRepository.findByUsername(reqDTO.getUsername())
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 사용자 이름 또는 비밀번호."));

	    // JWT 생성 후 반환
	    log.info("로그인 성공: {}", reqDTO.getUsername());
	    return jwtUtil.generateToken(user.getUsername());
	}
	
	// OAuth2 로그인
    public String signin(UserRequest.OAuth2SigninDTO reqDTO) {
        log.info("OAuth2 로그인 시도: {}", reqDTO.getUsername());
        User user = userRepository.findByUsername(reqDTO.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자가 존재하지 않습니다."));

        // JWT 생성 후 반환
        log.info("OAuth2 로그인 성공: {}", reqDTO.getUsername());
        return jwtUtil.generateToken(user.getUsername());
    }
	
}
