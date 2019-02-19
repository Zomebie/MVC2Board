package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 @WebServlet("*.bo")
 public class BoardFrontController 
 	extends javax.servlet.http.HttpServlet 
 	implements javax.servlet.Servlet {
	 
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		
		 String RequestURI=request.getRequestURI();//request를 통해 URL 받아오기
		 String contextPath=request.getContextPath();
		 String command=RequestURI.substring(contextPath.length()); // 끝부분 URL 파싱
		 
		 System.out.println(command);
		 ActionForward forward=null;
		 Action action=null;
		   
		 // 사용자 요청들을 분기 시킴, 해당하는 action에 따라 객체 생성하여 로직 수행
		   if(command.equals("/BoardWrite.bo")){
			   forward=new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("./board/qna_board_write.jsp");
		   }else if(command.equals("/BoardReplyAction.bo")){
			   action = new BoardReplyView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/BoardDelete.bo")){
			   forward=new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("./board/qna_board_delete.jsp");
		}else if(command.equals("/BoardModify.bo")){
			   action = new BoardModifyView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
	 	   }else if(command.equals("/BoardAddAction.bo")){
			   action  = new BoardAddAction();
			   try {
				   forward=action.execute(request, response );
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
		   }else if(command.equals("/BoardReplyView.bo")){
			   action = new BoardReplyAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/BoardModifyAction.bo")){
			   action = new BoardModifyAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/BoardDeleteAction.bo")){
			   action = new BoardDeleteAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }else if(command.equals("/BoardList.bo")){
			   action = new BoardListAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				  
				   e.printStackTrace();
			   }
		   }else if(command.equals("/BoardDetailAction.bo")){
			   action = new BoardDetailAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }
		   
		   //forward에서 받은 URL 로 페이지 전환 (forwarding)
		   if(forward.isRedirect()){
			   response.sendRedirect(forward.getPath());
		   }else{
			   RequestDispatcher dispatcher=
				   request.getRequestDispatcher(forward.getPath());
			   dispatcher.forward(request, response);
		   }
	 }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		 // doGet든 doPost방식이든 doProcess에서 로직 수행
		doProcess(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request,response);
	}   	  	    
}