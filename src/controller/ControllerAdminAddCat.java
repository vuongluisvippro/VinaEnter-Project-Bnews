package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import jdk.internal.org.objectweb.asm.util.CheckClassAdapter;
import library.LibraryPermission;
import model.ModelCategory;
import utils.CheckCat;
import utils.ValidateCat;

/**
 * Servlet implementation class ControllerPublicIndex
 */
@SuppressWarnings("all")
public class ControllerAdminAddCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminAddCat() {
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
		if(request.getParameter("them")!=null && request.getParameter("ten") != null){
			String name = request.getParameter("ten");
			if(new CheckCat().checkCatName(name)){
				if(!new ValidateCat().isName(name)){
					if(new ModelCategory().addItem(new Category(name)) > 0){
						// thêm thông tin thành công
						response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=add1");
					}else{
						// thêm thông tin thất bại
						response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=add0");
					}
				}else{
					request.setAttribute("messageBudle", new ValidateCat().isName(name));
					RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp");
					rd.forward(request, response);
				}
			}else{
				request.setAttribute("error", "Danh mục đã tồn tại trong hệ thống!");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp");
				rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp");
			rd.forward(request, response);
		}
	}
}
