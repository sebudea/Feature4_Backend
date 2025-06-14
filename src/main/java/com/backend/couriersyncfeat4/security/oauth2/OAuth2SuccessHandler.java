package com.backend.couriersyncfeat4.security.oauth2;

import com.backend.couriersyncfeat4.entity.UserEntity;
import com.backend.couriersyncfeat4.interfaces.IUserService;
import com.backend.couriersyncfeat4.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final IUserService systemUserService;

    @Value("${app.frontend.redirect-uri}")
    private String redirectUri;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        System.out.println("### DEBUG: El redirectUri es: " + redirectUri);

        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");

        // Determinar el rol del usuario
        String role = determineUserRole(email);

        // Generar el token JWT
        String token = jwtTokenProvider.generateToken(email, role);

        // Construir la URI de redirección con el token
        String targetUrl = UriComponentsBuilder.fromUriString(redirectUri)
                .queryParam("token", token)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineUserRole(String email) {
        if (adminEmail != null && adminEmail.equals(email)) {
            return "ADMIN";
        }
        UserEntity user = systemUserService.findUserByEmail(email);
        if (user != null && user.getRoleEntity() != null) {
            return user.getRoleEntity().getName().toUpperCase();
        }
        // Rol por defecto si el usuario no existe en la BD o no tiene rol
        return "CUSTOMER";
    }
}