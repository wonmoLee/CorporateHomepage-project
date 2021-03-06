package com.kakao.corp.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	//히스토리 백
	public static void back(String msg,HttpServletResponse response) { //돌아가는
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("history.back();");
			out.print("</script>");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//해당주소로 이동
		public static void href(String msg,String uri, HttpServletResponse response) {
			try {
				
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('"+msg+"');");
				out.print("location.href='"+uri+"';");
				out.print("</script>");	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static void outJson(String msg,HttpServletResponse response) {
			try {
				
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/Json; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void outText(String msg,HttpServletResponse response) {
			try {
				
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/plain; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//메세지 
		public static void getMessage(String msg,HttpServletResponse response) {
			try {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print("<h1>"+msg+"</h1>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
