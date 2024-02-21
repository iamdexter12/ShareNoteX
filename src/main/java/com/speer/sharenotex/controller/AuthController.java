package com.speer.sharenotex.controller;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.speer.sharenotex.config.JwtService;
import com.speer.sharenotex.exception.NotFoundException;
import com.speer.sharenotex.requesdto.LoginRequestDto;
import com.speer.sharenotex.requesdto.UserRegistrationRequestDto;
import com.speer.sharenotex.service.AuthService;
import com.speer.sharenotex.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller class for handling authentication-related endpoints. Provides API
 * endpoints for user registration and obtaining access tokens.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "API endpoints for Auth")
public class AuthController {

	private final AuthService authService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	/**
	 * Endpoint for user registration.
	 *
	 * @param userRegistrationRequestDto DTO containing user registration details.
	 * @return ResponseEntity indicating the success or failure of the registration
	 *         process.
	 */

	@PostMapping("/signup")
	@Operation(summary = "Register a new user", description = "Endpoint to register a new user")
	ResponseEntity<String> signup(@Valid @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
		return Optional.of(authService.addNewUser(userRegistrationRequestDto)).filter(Boolean::booleanValue)
				.map(result -> ResponseEntity.ok(Constants.USER_REGISTER_SUCCESS))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.USER_REGISTER_FAIL));
	}

	/**
	 * Endpoint for obtaining an access token by user login.
	 *
	 * @param loginRequestDto DTO containing user login details.
	 * @return ResponseEntity containing the access token or an error response.
	 */
	@PostMapping("/login")
	@Operation(summary = "Get an access token", description = "Endpoint for getting an access token")
	ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password()));

		if (authenticate.isAuthenticated()) {

			return ResponseEntity.ok(jwtService.generateToken(loginRequestDto));

		} else {
			throw new NotFoundException("USER_DONT_EXIST", loginRequestDto.email());
		}

	}

	@GetMapping("/welcome")
	String welcome() {
		return "Welcome to ShareNoteX";
	}
}
