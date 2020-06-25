package com.kakao.corp.action.nav.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kakao.corp.action.Action;
import com.kakao.corp.model.Users;
import com.kakao.corp.model.VoiceOfCustBoard;
import com.kakao.corp.repository.BoardRepository;
import com.kakao.corp.util.Script;

public class VoiceOfCustUserWriteProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0번 인증 확인
				HttpSession session = request.getSession();
				if(session.getAttribute("principal") == null) {
					Script.getMessage("잘못된 접근입니다.", response);
					return;  //여기서 return이 아니면 코드를 아래를 타고 내려간다.
				}
				Users principal = (Users)session.getAttribute("principal");
				
				// 1. request에 title값과 content값 null인지 공백인지 확인
				if
				(
				request.getParameter("title").equals("") ||
				request.getParameter("title") == null ||
				request.getParameter("content").equals("") ||
				request.getParameter("content") == null
				) {
					return;
				}
				
				// 2. request에 title값과 content값 받기
				String title = request.getParameter("title");
				String content = request.getParameter("content");

				// 3. title값과 content, principal.getId()값을 Board 오브젝트에 담기
				VoiceOfCustBoard board = VoiceOfCustBoard.builder()
						.userId(principal.getId())
						.title(title)
						.content(content)
						.readCount(0)
						.build();
				
				// 4. BoardRepository연결해서 save(board)함수 호출
				BoardRepository boardRepository = BoardRepository.getInstance();
				int result = boardRepository.vocSave(board);
				
				// 5. result == 1이면 성공로직(index.jsp로 이동)
				if(result == 1) {
					
					Script.href("게시글이 등록되었습니다.", "/corp/support/question.jsp", response);
					
				} else {
					
					Script.back("게시글 등록에 실패하였습니다.", response);
					
				}
		}
}
