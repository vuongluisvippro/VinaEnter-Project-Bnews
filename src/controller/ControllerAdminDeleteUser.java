package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryPermission;
import model.ModelUser;

/**
 * Servlet implementation class ControllerPublicIndex
 */
public class ControllerAdminDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminDeleteUser() {
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
		 * form web.xml => deleteCat 
		 */
		if(!LibraryPermission.isLogin(request, response)){
			return;
		}
		if(request.getParameter("cid")!=null){
			int id = Integer.parseInt(request.getParameter("cid"));
			if(new ModelUser().delItem(id) > 0){
				// Xóa thành công
				response.sendRedirect(request.getContextPath()+"/admin/indexUser?msg=del1");
			}else{
				// Xóa thất bại
				response.sendRedirect(request.getContextPath()+"/admin/indexUser?msg=del0");
			}
		}
	}
}
