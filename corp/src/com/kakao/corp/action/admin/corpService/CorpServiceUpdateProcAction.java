package com.kakao.corp.action.admin.corpService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kakao.corp.action.Action;
import com.kakao.corp.model.CorpServiceContent;
import com.kakao.corp.model.CorpServiceTitle;
import com.kakao.corp.repository.CorpServiceRepository;
import com.kakao.corp.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CorpServiceUpdateProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mainTitle = "";
		String realPath = request.getServletContext().getRealPath("/static/img");
		String fileName1 = "";
		String fileName2 = "";
		String contextPath = request.getServletContext().getContextPath();
		String titleImg = "";
		String contentImg = "";
		String mainContent = "";
		String category = "";
		String name = "";
		String text = "";
		int id;
		
		CorpServiceRepository corpServiceRepository = CorpServiceRepository.getInstance();
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					realPath,
					1024*1024*2,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			if(multi.getFilesystemName("titleImg") != null) {
			fileName1 = multi.getFilesystemName("titleImg");
			titleImg = contextPath + "/static/img/" + fileName1;
			mainTitle = multi.getParameter("mainTitle");
			mainContent = multi.getParameter("mainContent");
			
			CorpServiceTitle corpServiceTitle = CorpServiceTitle.builder()
					.img(titleImg)
					.title(mainTitle)
					.content(mainContent)
					.build();			

			
			int result1 = corpServiceRepository.serviceUpdate1(corpServiceTitle);
			
			if (result1 != 1) {
				Script.back("수정에 실패하였습니다.", response);
			}
			
			}
			
			
			
			for (int i=1;i<=35;i++) {
				
				int result2 = -1;
				
				if(multi.getFilesystemName("contentImg"+i) != null) {
				id = Integer.parseInt(multi.getParameter("id"+i));
				fileName2 = multi.getFilesystemName("contentImg"+i);
				contentImg = contextPath + "/static/img/" + fileName2;
				category = multi.getParameter("category"+i);
				name = multi.getParameter("name"+i);
				text = multi.getParameter("text"+i);

				CorpServiceContent corpServiceContent = CorpServiceContent.builder()
						.id(id)
						.img(contentImg)
						.category(category)
						.name(name)
						.text(text)
						.build();
				result2 = corpServiceRepository.serviceUpdate2(corpServiceContent);
				} else {
					id = Integer.parseInt(multi.getParameter("id"+i));
					category = multi.getParameter("category"+i);
					name = multi.getParameter("name"+i);
					text = multi.getParameter("text"+i);
//				String test = realPath+ "//" +fileName2;
					CorpServiceContent corpServiceContent = CorpServiceContent.builder()
							.id(id)
							.category(category)
							.name(name)
							.text(text)
							.build();
					
					
					result2 = corpServiceRepository.serviceUpdate3(corpServiceContent);
				}

				
				if (result2 != 1) {
					Script.back("수정에 실패하였습니다.", response);
				}
			}
			
			Script.href("수정에 성공하셨습니다.","/corp/service?cmd=serviceMain" , response);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
