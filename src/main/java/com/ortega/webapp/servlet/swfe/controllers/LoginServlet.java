package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.models.UserEntity;
import com.ortega.webapp.servlet.swfe.services.LoginService;
import com.ortega.webapp.servlet.swfe.services.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String recaptchaToken = request.getParameter("g-recaptcha-response");

//        if (!verifyRecaptcha(recaptchaToken)) {
//            response.sendRedirect(request.getContextPath() + "/login.jsp?error=captcha");
//            return;
//        }

        Connection connection = (Connection) request.getAttribute("connection");
        LoginService loginService = new LoginServiceImpl(connection);
        Optional<UserEntity> userEntity = loginService.login(username, password);

        if (userEntity.isPresent()) {
            try {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("username", username);
                httpSession.setAttribute("usuario", userEntity.get().getUsername());
                httpSession.setAttribute("userRole", userEntity.get().getRoleId());
                response.sendRedirect(request.getContextPath() + "/clientes/seleccionar");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            System.out.println("Login incorrecto");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    private boolean verifyRecaptcha(String recaptchaToken) {
        boolean captchaValido = false;
        try {
            String secretKey = "6Lf74yQrAAAAABzmcf0y--ONGpUJAD-UFrfOjHcl";
            String url = "https://www.google.com/recaptcha/api/siteverify";
            String params = "secret=" + secretKey + "&response=" + recaptchaToken;

            java.net.URL obj = new java.net.URL(url);
            java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            java.io.OutputStream os = con.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();

            java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer responseCaptcha = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseCaptcha.append(inputLine);
            }
            in.close();

            org.json.JSONObject json = new org.json.JSONObject(responseCaptcha.toString());
            captchaValido = json.getBoolean("success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return captchaValido;
    }

}
