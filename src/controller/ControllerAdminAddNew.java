package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import bean.Category;
import bean.New;
import jdk.internal.org.objectweb.asm.util.CheckClassAdapter;
import library.LibraryPermission;
import model.ModelCategory;
import model.ModelNew;
import utils.CheckCat;

/**
 * Servlet implementation class ControllerPublicIndex
 */
@SuppressWarnings("all")
public class ControllerAdminAddNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminAddNew() {
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
		if(!LibraryPermission.isLogin(request, response)){
			return;
		}
		if("load".equals(request.getParameter("type"))){ // ban đầu cần load form
			request.setAttribute("alCat", new ModelCategory().getList());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addNew.jsp");
			rd.forward(request, response);
		}else{
			/**
			 * @author Nguyễn Văn Vương
			 */
			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				System.out.println(e.getMessage());
			}
			String name = "";
			String preview_text = "";
			String detail_text = "";
			int id_cat = 0;
			String picture = "";
			String picture_new = "";
			for(FileItem fileItem : fileItems){
				if(fileItem.isFormField()){
					// File common
					String fieldName = fileItem.getFieldName();
					String fieldValue = new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8");
					switch(fieldName){
						case "tentin":
							name = fieldValue;
							break;
						case "danhmuc":
							id_cat = Integer.parseInt(fieldValue);
							break;
						case "mota":
							preview_text = fieldValue;
							break;
						case "chitiet":
							detail_text = fieldValue;
							break;
						default:
							break;
					}
				}else{
					// File special
					if(!fileItem.getName().isEmpty()){
						/**
						 * Nếu file trường upload ảnh khác null thì tiến hành upload ảnh và đưa vào trong hệ
						 * thống.
						 * XULYNGHIEPVU: Nếu như file ảnh đưa vào chuyển đổi tên tránh trùng với tên khác trong
						 * hệ thống.
						 */
						picture = fileItem.getName();
						System.out.println(picture);
						picture_new = FilenameUtils.getBaseName(picture)+"-"+System.nanoTime()+"."+FilenameUtils.getExtension(picture);
						String filePath = request.getServletContext().getRealPath("")+File.separator+"files"+File.separator+picture_new;
						File file = new File(filePath);
						try {
							fileItem.write(file);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}
			}// End for loop
			New objectNews = new New(name, preview_text, detail_text, id_cat, picture_new);
			if(new ModelNew().addItem(objectNews) > 0){
				// thêm thành công
				response.sendRedirect(request.getContextPath()+"/admin/indexNew?msg=add1");
			}else{
				// thêm thất bại
				response.sendRedirect(request.getContextPath()+"/admin/indexNew?msg=add0");
			}
		}// End If
	}
}
