package ru.gb.online.store.config;

import ru.gb.online.store.entities.User;
import ru.gb.online.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
    private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication)
			throws IOException, ServletException {
		String userName = authentication.getName();
		User theUser = userService.findByUserName(userName);

		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);

		String referrer = (String) session.getAttribute ("current_referer");
		if (referrer == null){
			referrer = request.getContextPath();
		}

		response.sendRedirect(referrer);
	}

}
