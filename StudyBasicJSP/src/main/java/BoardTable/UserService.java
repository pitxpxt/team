package BoardTable;

import java.io.PrintWriter;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;



public class UserService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String viewPath = "/WEB-INF/jsp/model12";
	
	private static Map<String,String> userMap = new HashMap<>();
	static {
		userMap.put("smith", "1111");
		userMap.put("scott", "2222");
	}
	
	public UserService() {}
	
	public UserService(HttpServletRequest request, HttpServletResponse response) {
		
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	public String logon()
	{
		String cmd = request.getParameter("cmd");
		
		if(cmd==null || cmd.equals("loginform"))
		{
			return viewPath+"/loginForm.jsp";
					
		}else if(cmd.equals("login"))
		{
			User u = new User();
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			u.setUid(uid);
			u.setPwd(pwd);
			
			boolean login = login(u);
			Map<String, Object> map = new HashMap<>();
			map.put("login", login);
			sendJSONStr(map);
		}else if(cmd.equals("logout"))
		{
			logout();
			Map<String, Object> map = new HashMap<>();
			map.put("logout", true);
			sendJSONStr(map);
			
			/*JSONObject jsObj = new JSONObject();
			jsObj.put("logout", true);
			return jsObj.toJSONString();*/
		}
		return null;
	}

	
	public void sendJSONStr(Map<String, Object> map)
	{
		JSONObject jsObj = new JSONObject(map);
		String jsStr = jsObj.toJSONString();

		try {
			PrintWriter out = response.getWriter();
			out.print(jsStr);
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean login(User user)
	{
		Set<String> keyset = userMap.keySet();
		Iterator<String> it = keyset.iterator();
		
		while(it.hasNext())
		{
			String uid = it.next();
			String pwd = userMap.get(uid);
			
			if(user.getUid().equals(uid) && user.getPwd().equals(pwd))
			{
				session.setAttribute("uid", user.getUid());
				return true;
			}
		}
		return false;
	}
	
	public void logout()
	{
		session.invalidate();
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}
