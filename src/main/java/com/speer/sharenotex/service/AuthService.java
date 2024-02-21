package com.speer.sharenotex.service;

import com.speer.sharenotex.requesdto.UserRegistrationRequestDto;

/**
 * Service interface defining authentication-related operations.
 */
public interface AuthService {

	/**
	 * Adds a new user based on the provided registration details.
	 *
	 * @param userRegistrationRequestDto DTO containing user registration details.
	 * @return True if the user is successfully added; false otherwise.
	 */
	Boolean addNewUser(UserRegistrationRequestDto userRegistrationRequestDto);


}
