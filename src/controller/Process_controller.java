package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Dto;

@WebServlet("*.do")
public class Process_controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = uri.substring(contextPath.length());
		RequestDispatcher dis=null;
		System.out.println(com);

		if (com.equals("/join.do")) {

			Dao dao = new Dao();
			Dto dto = new Dto();

			dto.setId((String) request.getParameter("id"));
			dto.setPw((String) request.getParameter("pass"));
			dto.setEmail((String) request.getParameter("email"));
			dto.setName((String) request.getParameter("name"));
			dto.setAddress((String) request.getParameter("sample4_detailAddress"));
			dto.setRrd((String) request.getParameter("citizenNum1"));

			dao.join(dto);

			 dis= request.getRequestDispatcher("./Login/login.jsp");
			dis.forward(request, response);

		} 
		
		
		 else if (com.equals("/first.do")) {
		 dis = request.getRequestDispatcher("./Login/login.jsp");
		dis.forward(request, response);

		} else if (com.equals("/joinWrite.do")) {

			dis = request.getRequestDispatcher("./Login/join.jsp");
			dis.forward(request, response);

		} 
		
		else if (com.equals("/logout.do")) {

			dis = request.getRequestDispatcher("./Login/login.jsp");
			dis.forward(request, response);

		} else if (com.equals("/login.do")) {

			Dao dao = new Dao();

			String username = null;

			String id = (String) request.getParameter("id");
			String pw = (String) request.getParameter("pw");

			System.out.println(id);
			System.out.println(pw);

			username = dao.login(id, pw);

			System.out.println("username : " + username);

			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			if (username != null) {
				 dis = request.getRequestDispatcher("./Login/main.jsp");
				dis.forward(request, response);
			} else {
					
				
				 dis = request.getRequestDispatcher("Login/login.jsp");
				dis.forward(request, response);
				

			}

		}

		else if (com.equals("/list.do")) {
			ArrayList<Dto> list = null;
			Dao dao = new Dao();
			list = dao.list();

			request.setAttribute("list", list);

			 dis = request.getRequestDispatcher("./Login/admin.jsp");
			dis.forward(request, response);

		}

		else if (com.equals("/info.do")) {
			Dao dao = new Dao();
			Dto dto = null;

			String name = (String) request.getParameter("name");

			System.out.println("info name : " + name);

			dto = dao.info(name);

			request.setAttribute("dto", dto);

			 dis = request.getRequestDispatcher("./Login/info.jsp");
			dis.forward(request, response);

		}

		else if (com.equals("/delete.do")) {
			Dao dao = new Dao();

			String name = (String) request.getParameter("name");

			System.out.println("delete name : " + name);

			dao.delete(name);

			dis = request.getRequestDispatcher("./Login/main.jsp");
			dis.forward(request, response);

		}

	}

}
