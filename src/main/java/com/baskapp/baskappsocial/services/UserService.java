package com.baskapp.baskappsocial.services;

import com.baskapp.baskappsocial.dtos.request.CreateUserDto;
import com.baskapp.baskappsocial.dtos.request.LoginDto;
import com.baskapp.baskappsocial.dtos.response.LoggedDto;
import com.baskapp.baskappsocial.models.User;
import com.baskapp.baskappsocial.repositories.UserRepository;
import com.baskapp.baskappsocial.utils.BaskappAuthUtil;
import com.baskapp.baskappsocial.utils.BaskappPasswordUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BaskappAuthUtil authUtil;

    public UserService(UserRepository userRepository, BaskappAuthUtil authUtil) {
        this.userRepository = userRepository;
        this.authUtil = authUtil;
    }

    public LoggedDto createUser(CreateUserDto dto) {
        if (this.userRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        final String encryptedPassword = BaskappPasswordUtil.encryptPassword(dto.getPassword());

        User user = new User();
        user.setPassword(encryptedPassword);
        user.setEmail(dto.getEmail());
        user = this.userRepository.save(user);

        return new LoggedDto(
                this.authUtil.generateJwt(
                        user.getId().toString()
                ),
                this.authUtil.generateRefreshToken()
        );
    }

    public LoggedDto doLogin(LoginDto loginDto) {
        User user = this.userRepository.findByEmail(loginDto.getEmail());

        if (user != null) {
            Boolean isCorrectPassword = BaskappPasswordUtil.validatePassword(loginDto.getPassword(), user.getPassword());

            if (isCorrectPassword) {
                user.setRefreshToken(authUtil.generateRefreshToken());
                this.userRepository.save(user);

                return new LoggedDto(
                        authUtil.generateJwt(user.getId().toString()),
                        user.getRefreshToken()
                );
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email ou senha incorretos");
    }
}
