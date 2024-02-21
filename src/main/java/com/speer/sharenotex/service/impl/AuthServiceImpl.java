package com.speer.sharenotex.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.speer.sharenotex.model.User;
import com.speer.sharenotex.repository.UserRepository;
import com.speer.sharenotex.requesdto.UserRegistrationRequestDto;
import com.speer.sharenotex.service.AuthService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the {@link AuthService} interface providing authentication-related operations.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	

	@Override
	public Boolean addNewUser(UserRegistrationRequestDto userRegistrationRequestDto) {
		User user = new User();
		user.setEmail(userRegistrationRequestDto.email());
		user.setName(userRegistrationRequestDto.name());
		user.setPassword(passwordEncoder.encode(userRegistrationRequestDto.password()));
		user.setRole("User");
		userRepository.save(user);
		return true;

	}


}
