package com.binhui.example.mvc.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        SessionFlashMapManager flashMapManager = new SessionFlashMapManager();

        FlashMap flashMap = new FlashMap();

        flashMap.put("success", "Welcome " +authentication.getName());

        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
