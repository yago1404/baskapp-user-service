package com.baskapp.baskappsocial.infra.middlewares;

import com.baskapp.baskappsocial.infra.notations.InternalRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class InternalRequestMiddleware {
    @Value("${internal.token}")
    private String token;

    @Around("@annotation(internalRequest)")
    public Object validateIsInternalRequest(ProceedingJoinPoint joinPoint, InternalRequest internalRequest) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String internalAuthorization = request.getHeader("X-Internal-Request");
        if (internalAuthorization == null || !internalAuthorization.startsWith("Internal ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Recurso acessável apenas internamente");
        }

        String internalToken = internalAuthorization.substring(7);
        if (!internalToken.equals(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Basic Token invalido");
        }

        return joinPoint.proceed();
    }
}
