package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import library.LibraryPermission;
import model.ModelCategory;
import utils.CheckCat;
import utils.ValidateCat;

/**
 * Servlet implementation class ControllerPublicIndex
 */
@SuppressWarnings("all")
public class ControllerAdminEditCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminEditCat() {
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
			request.setAttribute("cat", new ModelCategory().getItem(id));
		}
		if(request.getParameter("sua")!=null){
			String name = request.getParameter("ten");
				/**
				 * Khắc phục lỗi tiếng việt vì jsp mặc định iSO-8859-1
				 * name = new String(name.getBytes(),"utf-8"); 
				 */
				if(!new CheckCat().checkCatNameVer(name, id)){
					if(!new ValidateCat().isName(name)){
						if(new ModelCategory().editItem(new Category(id,name)) > 0){
							// cập nhật thành công
							response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=edit1");
						}else{
							// cập nhật thất bại
							response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=edit0");
						}
					}else{
						request.setAttribute("messageBudle", new ValidateCat().isName(name));
						RequestDispatcher rd = request.getRequestDispatcher("/admin/editCat.jsp");
						rd.forward(request, response);
					}
				}else{
					request.setAttribute("error", "Tên danh mục không được trùng với nhau!");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/editCat.jsp");
					rd.forward(request, response);
				}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editCat.jsp");
			rd.forward(request, response);
		}
	}
}
