package BoardTable;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class boardDAO 
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn()
	{
		 try {
	         Class.forName("oracle.jdbc.OracleDriver"); 
	         conn = DriverManager.getConnection(
	                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER"); 
	       
	         this.conn=conn;
	         
	         return conn;
	         
	        } catch (Exception e) {
	            e.printStackTrace();;
	        }
		return null;
	}
	
	public List<boardVO> getBoardList()
	{
		getConn();
		
		
			try {
								
				String sql="SELECT * FROM board ORDER BY boardid";
				this.pstmt=conn.prepareStatement(sql);
				this.rs=pstmt.executeQuery();
				
				List<boardVO> list = new ArrayList();
				while(rs.next())
				{
					int boardid = rs.getInt("boardid");
					String title = rs.getString("title");
					String author = rs.getString("author");
					int hitcnt = rs.getInt("hitcnt");
					Date regdate = rs.getDate("regdate");
					
					boardVO bvo = new boardVO();
					bvo.setBoardid(boardid);
					bvo.setTitle(title);
					bvo.setAuthor(author);
					bvo.setHitcnt(hitcnt);
					bvo.setRegdate(regdate);
					
					list.add(bvo);
					
				}
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll();
			}
			
		
		return null;
	}
	
	public Map<String,Object> getBoardList2(int pageNum)
	{
		getConn();
		
		
			try {
								
				String sql="SELECT * FROM get_board"
						+ "CROSS JOIN (SELECT CEIL(COUNT(*)/5) ttlpages FROM board)"
						+ "WHERE page=?";
				
				this.pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,pageNum);
				this.rs=pstmt.executeQuery();

				Map<String, Object> map = new HashMap<>();
				while(rs.next())
				{
					
					List<boardVO> list = new ArrayList<>();
				
					
					int boardid = rs.getInt("boardid");
					String title = rs.getString("title");
					String author = rs.getString("author");
					int hitcnt = rs.getInt("hitcnt");
					Date regdate = rs.getDate("regdate");
					int parentid = rs.getInt("parentid");
					
					boardVO bvo = new boardVO();
					bvo.setBoardid(boardid);
					bvo.setTitle(title);
					bvo.setAuthor(author);
					bvo.setHitcnt(hitcnt);
					bvo.setRegdate(regdate);
					bvo.setParentid(parentid);
					
					list.add(bvo);
					
					
					int totlpages = rs.getInt("ttlpages");
					
					map.put("list", list);
					map.put("ttlpages", totlpages);
					
				}
				return map;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll();
			}
			
		
		return null;
	}
	
	public boardVO findBoard(int boardid)
	{
		getConn();
		
		try {
			String sql="UPDATE board SET hitcnt=hitcnt+1 WHERE boardid=?";
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardid);
			pstmt.executeUpdate();
			
			String sql1="SELECT * FROM board WHERE boardid=?";
			this.pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, boardid);
			this.rs=pstmt.executeQuery();
	
			
			while(rs.next())
			{
				int brdid = rs.getInt("boardid");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String author = rs.getString("author");
				int hitcnt = rs.getInt("hitcnt");
				Date regdate = rs.getDate("regdate");
				int parentid = rs.getInt("parentid");
				
				boardVO bvo = new boardVO();
				bvo.setBoardid(brdid);
				bvo.setTitle(title);
				bvo.setContents(contents);
				bvo.setAuthor(author);
				bvo.setHitcnt(hitcnt);
				bvo.setRegdate(regdate);
				bvo.setParentid(parentid);
				
				return bvo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public boolean addBoard(boardVO bvo)
	{
		getConn();
		
		try {
			String sql="INSERT INTO board (boardid,title,contents,author,parentid) values(BD_ID_SEQ.NEXTVAL,?,?,?,?)";
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContents());
			pstmt.setString(3, bvo.getAuthor());
			pstmt.setInt(4, bvo.getParentid());
			
			int rows = pstmt.executeUpdate();
			return rows>0? true : false;

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	

	public boolean deleteBoard(boardVO bvo)
	{
		getConn();
		 try {
		
			String sql="DELETE FROM board WHERE boardid=?";
			this.pstmt= conn.prepareStatement(sql);

			pstmt.setInt(1,bvo.getBoardid());
			
			int rows=pstmt.executeUpdate(); 
		    return rows>0? true : false;
	        	  
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		closeAll(); //에러가 나든 안나든 돌아가는 블럭
	}
   return false;
	}
	
	public boolean updateBoard(boardVO bvo)
	{
		getConn();
		 try {
		
			String sql="UPDATE board SET title=? , contents=? WHERE boardid=?";
			this.pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,bvo.getTitle());
			pstmt.setString(2, bvo.getContents());
			pstmt.setInt(3,bvo.getBoardid());
			
			int rows=pstmt.executeUpdate(); 
		    return rows>0? true : false;
	        	  
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		closeAll(); //에러가 나든 안나든 돌아가는 블럭
	}
   return false;
	}
	
	
	private void closeAll()
	{
	
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
