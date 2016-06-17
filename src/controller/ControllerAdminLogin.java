package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import model.ModelUser;

/**
 * Servlet implementation class ControllerPublicIndex
 */
@SuppressWarnings("all")
public class ControllerAdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ModelUser mUser = new ModelUser();
		if(request.getParameter("submit")!=null){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User objUser = mUser.getUser(username,password);
			/**
			 * Lầy ra objUser mục đích không dùng boolean là vì
			 * cần giá trị gán cho session
			 */
			if(objUser!=null){
				//B1. Tạo session
				HttpSession session = request.getSession();
				session.setAttribute("objUser", objUser);
				//B2. Chuyển hướng sang trang admin
				response.sendRedirect(request.getContextPath()+"/admin");
				return;
			}else{
				response.sendRedirect(request.getContextPath()+"/admin/login?msg=0");
				/**
				 * Đảm bảo dừng tại đó không chạy tiếp
				 */
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
		rd.forward(request, response);
	}

}
