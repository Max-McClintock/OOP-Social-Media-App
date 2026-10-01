
package A2;


public class Posts 
{
    public int PostID;
    public int UserID;
    public String PostSecurity;
    public String Date;
    
    
    
    public Posts(int PostID, int UserID, String PostSecurity, String Date)
    {
        this.PostID =PostID;
        this.UserID = UserID;
        this.PostSecurity = PostSecurity;
        this.Date = Date;
    }
    
    public int getPostID()
    {
        return PostID;
    }
    public int getUserID()
    {
        return UserID;
    }
    public String getPostSecurity()
    {
        return PostSecurity;
    }
    public String getDate()
    {
        return Date;
    }
    
    public String details()
    {
        String info = "<html>";
        info += "Post Info ";
        info += PostID + "<br>Posted by " + UserID + "<br>"; 
        info += "<br>On " + Date;
        info += "";
        return info;
    }
}
