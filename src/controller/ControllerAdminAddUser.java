package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.User;
import library.LibraryPermission;
import library.LibraryString;
import model.ModelCategory;
import model.ModelUser;
import utils.CheckUser;
import utils.ValidateUser;

/**
 * Servlet implementation class ControllerPublicIndex
 */
@SuppressWarnings("all")
public class ControllerAdminAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminAddUser() {
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
		/**
		 * Viết phương thức get data from form and insert database
		 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		if(!LibraryPermission.isLogin(request, response)){
			return;
		}
		if(request.getParameter("them")!=null){
			String username = request.getParameter("username");
			if(new CheckUser().checkUserNameVer(username)){
				String password = request.getParameter("password");
				String fullname = request.getParameter("fullname");
				/**
				 * Khắc phục lỗi tiếng việt vì jsp mặc định iSO-8859-1
				 * name = new String(name.getBytes(),"utf-8"); 
				 */
				/**
				 * Catch validate username, password and fullname
				 */
				if(!new ValidateUser().isFullName(fullname)){
					if(new ModelUser().addItem(new User(username,password,fullname)) > 0){
						// thêm thành công
						response.sendRedirect(request.getContextPath()+"/admin/indexUser?msg=add1");
					}else{
						// thêm thất bại
						response.sendRedirect(request.getContextPath()+"/admin/indexUser?msg=add0");
					}
				}else{
					request.setAttribute("messageBudle", new ValidateUser().isFullName(fullname));
					RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp");
					rd.forward(request, response);
				}
			}else{
				request.setAttribute("error", "User tồn tại trong hệ thống");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp");
				rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp");
			rd.forward(request, response);
		}
	}
}
