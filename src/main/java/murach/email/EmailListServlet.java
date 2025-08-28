package murach.email;

import murach.business.User;
import murach.data.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EmailListServlet", urlPatterns = { "/emailList" })
public class EmailListServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		UserDB.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + "/index.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		if (action == null)
			action = "add";

		if ("add".equals(action)) {
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");
			String dateOfBirth = req.getParameter("dateOfBirth");
			String ref = req.getParameter("ref");
			boolean wantOffers = "yes".equalsIgnoreCase(req.getParameter("wantOffers"));
			boolean wantEmail = "yes".equalsIgnoreCase(req.getParameter("wantEmail"));
			String contact = req.getParameter("contact");

			User user = new User(firstName, lastName, email, dateOfBirth, ref, wantOffers, wantEmail, contact);
			UserDB.upsert(user);

			req.setAttribute("user", user);
			req.getRequestDispatcher("/thanks.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/index.html");
		}
	}
}
