package com.speer.sharenotex.config;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.speer.sharenotex.exception.NotFoundException;
import com.speer.sharenotex.model.User;
import com.speer.sharenotex.repository.UserRepository;

import lombok.RequiredArgsConstructor;


/**
 * In this class, we are retrieving user details from userDao and assigning
 * them to the Spring Security's UserDetails object.
 * @author mdsharif
 *
 */
@Component
@RequiredArgsConstructor
public class SpringUserDetailsService implements UserDetailsService {

	
	private final UserRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userInfo = userDao.findByEmail(email);
		return userInfo.map(SpringUserDetails::new)
				.orElseThrow(() -> new NotFoundException("USER_DONT_EXIST", email));

	}

}
