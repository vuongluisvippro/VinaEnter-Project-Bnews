package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelCategory;
import model.ModelNew;

/**
 * Servlet implementation class ControllerPublicIndex
 */
@SuppressWarnings("all")
public class ControllerPublicDanhMuc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerPublicDanhMuc() {
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
		if(request.getParameter("cid")!=null){
			String id = request.getParameter("cid");
			request.setAttribute("objCat", new ModelCategory().getItem(Integer.parseInt(id)));
			/**
			 * Xử lý phân trang
			 */
			
			request.setAttribute("listNews", new ModelNew().getList(id));
			RequestDispatcher rd = request.getRequestDispatcher("/danhmuc.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		}
	}
}
