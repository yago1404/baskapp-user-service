package com.baskapp.baskappsocial.infra.middlewares;

import com.baskapp.baskappsocial.application.utils.BaskappAuthUtil;
import com.baskapp.baskappsocial.data.models.User;
import com.baskapp.baskappsocial.data.repositories.UserRepository;
import com.baskapp.baskappsocial.infra.notations.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Aspect
@Component
public class AuthMiddleware {
    @Autowired
    private BaskappAuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    @Around("@annotation(authenticated)")
    public Object validateToken(ProceedingJoinPoint joinPoint, Authenticated authenticated) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Faça login para continuar");
        }

        String jwt = authorizationHeader.substring(7);

        Optional<User> user;
        try {
            Map<String, Object> jwtData = authUtil.validateJwt(jwt);
            String userId = (String) jwtData.get("sub");
            user = this.userRepository.findById(UUID.fromString(userId));

            if (user.isEmpty()) {
                throw new Exception("Not found user");
            }

            request.setAttribute("authenticatedUser", user.get());
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token JWT ausente ou inválido");
        }

        return joinPoint.proceed();
    }
}
