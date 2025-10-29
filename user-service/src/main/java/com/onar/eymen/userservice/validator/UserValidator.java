package com.onar.eymen.userservice.validator;

import com.onar.eymen.common.core.advice.exception.user.BreachedPasswordException;
import com.onar.eymen.common.core.advice.exception.user.EmailAlreadyExistsException;
import com.onar.eymen.common.core.advice.exception.user.OldPasswordMismatchException;
import com.onar.eymen.userservice.model.dto.request.ChangePasswordRequest;
import com.onar.eymen.userservice.model.dto.request.UpdateProfileRequest;
import com.onar.eymen.userservice.model.entity.User;
import com.onar.eymen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final CompromisedPasswordChecker passwordChecker;

  public void validatePasswordDataBreachStatus(String password) {
    if (passwordChecker.check(password).isCompromised()) throw new BreachedPasswordException();
  }

  public void validateOldPassword(User user, ChangePasswordRequest request) {
    boolean isOldPasswordIncorrect =
        !passwordEncoder.matches(request.oldPassword(), user.getPassword());
    if (isOldPasswordIncorrect) throw new OldPasswordMismatchException();
  }

  public void validateEmailUpdate(User user, UpdateProfileRequest request) {
    if (hasEmailChanged(user, request)) validateUniqueEmail(request.newEmail());
  }

  public void validateUniqueEmail(String email) {
    if (repository.existsByEmail(email)) throw new EmailAlreadyExistsException();
  }

  private boolean hasEmailChanged(User user, UpdateProfileRequest request) {
    return !user.getEmail().equalsIgnoreCase(request.newEmail());
  }
}
