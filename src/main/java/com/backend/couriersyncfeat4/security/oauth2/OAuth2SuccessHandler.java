package com.backend.couriersyncfeat4.security.oauth2;

import com.backend.couriersyncfeat4.entity.SystemUser;
import com.backend.couriersyncfeat4.interfaces.ISystemUserService;
import com.backend.couriersyncfeat4.security.jwt.JwtTokenProvider;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final ISystemUserService systemUserService;

    @Value("${app.admin.email}")
    private String adminEmail;

    public OAuth2SuccessHandler(JwtTokenProvider jwtTokenProvider, ISystemUserService systemUserService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.systemUserService = systemUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        //TODO: Check this code
        String role;
        String email;
        if(adminEmail!=null){
            role = "ADMIN";
            email = adminEmail;
        } else {
            email = oauthUser.getAttribute("email");
            SystemUser user = systemUserService.findUserByEmail(email);
            if (user != null) {
                role = user.getRole().getName().toUpperCase();
            } else {
                role = "USER";
            }
        }
        // Genera el token JWT
        String token = jwtTokenProvider.generateToken(email, role);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("""
<html>
<head>
    <title>Autenticación Exitosa</title>
    <style>
        html{
            box-sizing: border-box;
            padding: 0;
            margin: 0;
            max-width: 100dvw;
            max-height: 100dvh;
        }
        body {
            box-sizing: border-box;
            padding: 0;
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #121212;
            color: #333;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        h1 {
            color: #c763ff;
        }
        p {
            color: #d264ed
        }
        code {
            display: block;
            background-color: #272822;
            color: #f8f8f2;
            padding: 12px 20px;
            border-radius: 6px;
            margin: 20px auto;
            max-width: 90%%;
            word-wrap: break-word;
            font-size: 1.1em;
        }
        button {
            background-color: #4e176d;
            color: white;
            border: none;
            padding: 12px 24px;
            font-size: 1em;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin: 0 8px;
        }
        button:hover {
            background-color: #45a049;
        }
        #copyMsg {
            color: #4caf50;
            margin-top: 15px;
            font-weight: bold;
            display: none;
        }
        #container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100%%;
        }
    </style>
</head>
<body>
    <div id="container">
        <h1>¡Autenticación Exitosa!</h1>
        <p>Tu token JWT es:</p>
        <code id="token">%s</code>
        <div>
            <button onclick="copyToken()">Copiar Header</button>
            <button onclick="window.location.href='/graphiql'">Ir a Graphiql</button>
        </div>
        <p id="copyMsg">¡Token JWT copiado al portapapeles!</p>
        <p>Puedes copiarlo y pegarlo en tu cliente GraphQL o aplicación.</p>
    </div>

    <script>
        function copyToken() {
            const tokenElement = document.getElementById('token');
            const tokenText = tokenElement.innerText.trim();

            // Aquí corregimos la concatenación para incluir "Bearer "
            const headerObject = JSON.stringify({ Authorization: "Bearer " + tokenText });

            navigator.clipboard.writeText(headerObject).then(() => {
                const msg = document.getElementById('copyMsg');
                msg.style.display = 'block';

                setTimeout(() => {
                    msg.style.display = 'none';
                }, 2000);
            }).catch(err => {
                alert('Error al copiar el token: ' + err);
            });
        }
    </script>
</body>
</html>
""".formatted(token));
        response.getWriter().flush();

    }
}

