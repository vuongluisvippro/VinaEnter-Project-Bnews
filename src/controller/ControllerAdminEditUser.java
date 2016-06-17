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
import model.ModelCategory;
import model.ModelUser;
import utils.CheckUser;
import utils.ValidateUser;

/**
 * Servlet implementation class ControllerPublicIndex
 */
@SuppressWarnings("all")
public class ControllerAdminEditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminEditUser() {
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
		int id = 0;
		if(request.getParameter("cid")!=null){
			id = Integer.parseInt(request.getParameter("cid"));
			request.setAttribute("user", new ModelUser().getItem(id));
		}
		if(request.getParameter("sua")!=null){
			int id_user = Integer.parseInt(request.getParameter("cid"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
				/**
				 * Khắc phục lỗi tiếng việt vì jsp mặc định iSO-8859-1
				 * name = new String(name.getBytes(),"utf-8"); 
				 */
				if(!new ValidateUser().isFullName(fullname)){
					if(new ModelUser().editItem(new User(id_user,username,password,fullname)) > 0){
						// cập nhật thành công
						response.sendRedirect(request.getContextPath()+"/admin/indexUser?msg=edit1");
					}else{
						// cập nhật thất bại
						response.sendRedirect(request.getContextPath()+"/admin/indexUser?msg=edit0");
					}
				}else{
					request.setAttribute("messageBudle", new ValidateUser().isFullName(fullname));
					RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp");
					rd.forward(request, response);
				}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp");
			rd.forward(request, response);
		}
	}
}
