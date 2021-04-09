package com.exdriving.school.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// обрабатывае страницу ошибки
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    // создаем logger и записываем в него действия пользователя
    private static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        // записываем действия пользователя в Logger. Admin пытался зайти в панель клиента - пишем, админ в панель админа - не пишем
        if (auth != null) {
            logger.info("Пользователь '" + auth.getName()
                    + "' пытался попасть на страницу "
                    + httpServletRequest.getRequestURI());
        }
        // при ошибке перенаправляем на адрес /403
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");

    }
}
