package com.onar.eymen.userservice.service;

import static com.onar.eymen.common.core.response.builder.ResponseBuilder.createSuccessResponse;

import com.onar.eymen.common.core.advice.exception.user.UserNotFoundException;
import com.onar.eymen.common.core.constant.Messages;
import com.onar.eymen.common.core.response.success.SuccessResponse;
import com.onar.eymen.userservice.model.dto.request.RegisterRequest;
import com.onar.eymen.userservice.model.dto.request.UpdateProfileRequest;
import com.onar.eymen.userservice.model.dto.response.UserResponse;
import com.onar.eymen.userservice.model.entity.User;
import com.onar.eymen.userservice.repository.UserRepository;
import com.onar.eymen.userservice.service.domain.UserDomainService;
import com.onar.eymen.userservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
  private final RoleService roleService;
  private final UserValidator validator;
  private final UserRepository repository;
  private final UserDomainService domainService;

  @Transactional
  public SuccessResponse<UserResponse> registerUser(RegisterRequest request) {
    validator.validatePasswordDataBreachStatus(request.password());
    validator.validateUniqueEmail(request.email());
    var roles = roleService.assignDefaultRoles();
    var user = domainService.buildUser(request, roles);
    var newUser = repository.save(user);
    var response = UserResponse.from(newUser);

    return createSuccessResponse(response, Messages.User.SAVED);
  }

  @Transactional
  public SuccessResponse<UserResponse> updateProfile(Long id, UpdateProfileRequest request) {
    var user = findById(id);
    user.setFirstName(request.newFirstName());
    user.setLastName(request.newLastName());
    validator.validateEmailUpdate(user, request);
    user.setEmail(request.newEmail());
    var newUser = repository.save(user);
    var response = UserResponse.from(newUser);

    return createSuccessResponse(response, Messages.User.UPDATED);
  }

  private User findById(Long id) {
    return repository.findById(id).orElseThrow(UserNotFoundException::new);
  }
}
