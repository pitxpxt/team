package BoardTable;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public class boardService 
{
	
	public boardService() {}
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String ViewPath="/WEB-INF/BoardTable";
	
	public boardService(HttpServletRequest request,HttpServletResponse response)
	{
		this.request=request;
		this.response=response;
		this.session = request.getSession();
	}
	
	public String sub()
	{
		String cmd = request.getParameter("cmd");
		
		if(cmd==null || cmd.equals("list"))
		{
			int pageNum= Integer.valueOf(request.getParameter("pageNum"));
			Map<String,Object> map = getlist(pageNum);
			request.setAttribute("list", map.get("list"));
			request.setAttribute("ttlpages", map.get("ttlpages"));
			return ViewPath+"/boardList.jsp";
		}
		else if(cmd.equals("boardDetail"))
		{
			int boardid = Integer.valueOf(request.getParameter("boardid"));
			boardVO bvo = findBoard(boardid);
			request.setAttribute("bvo", bvo);
			return ViewPath+"/boardDetail.jsp";
				
		}
		else if(cmd.equals("addBoard"))
		{
			request.setAttribute("bvo", new boardVO());
			return ViewPath+"/boardForm.jsp";
		}
		else if(cmd.equals("added"))
		{
			boolean added = addBoard();
			JSONObject jsObj = new JSONObject();
			jsObj.put("added", added);
			responseJSONStr(jsObj.toJSONString());
			//여기다가 제이슨 문자열 전송 넣기
		}
		else if(cmd.equals("updateBoard"))
		{
			int boardid = Integer.valueOf(request.getParameter("boardid"));
			boardVO bvo = findBoard(boardid);
			request.setAttribute("bvo", bvo);
			return ViewPath+"/boardDetailEdit.jsp";
		}
		else if(cmd.equals("updated"))
		{
			boolean updated = updateBoard();
			JSONObject jsObj = new JSONObject();
			jsObj.put("updated", updated);
			responseJSONStr(jsObj.toJSONString());
		}
		else if(cmd.equals("deleteBoard"))
		{
			boolean deleted = deleteBoard();
			JSONObject jsObj = new JSONObject();
			jsObj.put("deleted", deleted);
			responseJSONStr(jsObj.toJSONString());
			//여기다가 제이슨 문자열 전송 넣기
		}
		else if(cmd.equals("rePost"))
		{
			int boardid = Integer.valueOf(request.getParameter("boardid"));
			boardVO bvo = findBoard(boardid);
			request.setAttribute("bvo", bvo);
			return ViewPath+"/boardForm.jsp";
		}
		else if(cmd.equals("reposted"))
		{
			//여기다가 답글 추가 완료 넣기 
			boolean reposted = addBoard();
			JSONObject jsObj = new JSONObject();
			jsObj.put("reposted", reposted);
			responseJSONStr(jsObj.toJSONString());
		}
		
		return null;
	}
	
	private Map<String,Object> getlist(int pageNum)
	{
		boardDAO dao = new boardDAO();
		Map<String,Object> map = dao.getBoardList2(pageNum);
		return map;
	}
	
	private boardVO findBoard(int boardid)
	{
		boardDAO dao = new boardDAO();
		boardVO bvo = dao.findBoard(boardid);
		return bvo;
	}
	
	private boolean addBoard()
	{
		String sTitle = request.getParameter("title");
		String sCon = request.getParameter("contents");
		String sAuthor = request.getParameter("author");
		String sParentid = request.getParameter("parentid");
		
		boardVO bo = new boardVO();
		bo.setTitle(sTitle);
		bo.setContents(sCon);
		bo.setAuthor(sAuthor);
		bo.setParentid(Integer.parseInt(sParentid));
		
		boardDAO dao = new boardDAO();
		boolean bvo = dao.addBoard(bo);
		return bvo;
	}
	
	private void responseJSONStr(String jsonStr)
	{
		try {
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean deleteBoard() {
		
		String sBoardid = request.getParameter("boardid");
		boardVO bvo=new boardVO();
		bvo.setBoardid(Integer.parseInt(sBoardid));
		
		boardDAO dao = new boardDAO();
		return dao.deleteBoard(bvo);
				
		
	}
	private boolean updateBoard() {
		
		String sBoardid = request.getParameter("boardid");
		String sTitle = request.getParameter("title");
		String sCon = request.getParameter("contents");
		
		boardVO bvo = new boardVO();
		bvo.setBoardid(Integer.parseInt(sBoardid));
		bvo.setTitle(sTitle);
		bvo.setContents(sCon);
		
		boardDAO dao = new boardDAO();
		return dao.updateBoard(bvo);
				
		
	}
	
	private boolean repostBoard() {
		
		
		return false;
	}
}
