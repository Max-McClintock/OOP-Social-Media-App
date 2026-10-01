
package A2;


public class User
{
    private int UserID;
    private String FName;
    private String SName;
    private String DOB;
    private String Email;
    private String PWord;
    private Boolean Locked;
    private String LLogin;
    private String RDate;
    private int Friends;
    
    public User(int UserID, String FName, String SName, String DOB, String Email, String PWord, Boolean Locked, String LLogin, String RDate, int Friends)
    {
        this.UserID = UserID;
        this.FName = FName;
        this.SName = SName;
        this.DOB = DOB;
        this.Email = Email;
        this.PWord = PWord;
        this.Locked = Locked;
        this.LLogin = LLogin;
        this.RDate = RDate;
        this.Friends = Friends;
    }
    
    public int getUserID()
    {
        return UserID;
    }
    public String getFName()
    {
        return FName;
    }    
    public String getSName()
    {
        return SName;
    }
    public String getDOB()
    {
        return DOB;
    }    
    public String getEmail()
    {
        return Email;
    }
    public String getPWord()
    {
        return PWord;
    }
    public Boolean getLocked()
    {
        return Locked;
    }
    public String getLLogin()
    {
        return LLogin;
    }
    public String getRDate()
    {
        return RDate;
    }
    public int getFriends()
    {
        return Friends;
    }
    public String basicDetails()
    {
        String Output =  UserID +"     " + FName + "      " + SName + "  ";
        return Output;
    }
    public String outMyDetails() // for my posts page
    {
        String hiddenPWord = "";
        for (int index = 0; index < PWord.length(); index++)
        {
            hiddenPWord += "*";
        }
        String output = "<html>User Info<br>";
        output += "User ID " + UserID + "<br>";
        output += "Name " +FName + " " + SName + "<br>";
        output += "Email " + Email + " Password " + hiddenPWord;
        output += "<br>DOB " + DOB;
        return output;
    }
    public String fullDetails()
    {
        String hiddenPWord = "";
        for (int index = 0; index < PWord.length(); index++)
        {
            hiddenPWord += "*";
        }
        String output = "<html>User Info<br>";
        output += "User ID " + UserID + "<br>";
        output += "FName "+ FName +"<br>";
        output += "SName " + SName + "<br>";
        output += "DOB " + DOB + "<br>";
        output += "Email " + Email + "<br>";
        //output += "PWord " + hiddenPWord + "<br>";
        output += "RDate " + RDate + "<br>";     
        
        // TO DO -- To display in labels use <html> formatting so "<html> User Info<br>" 
        return output;
    }
    // set LLogin, Locked, Friends (everything else is just getters)
    
    public void setUserID (int UserID)
    {
        this.UserID = UserID;
    }
    
    public void setLLogin (String LLogin)
    {
        this.LLogin = LLogin;
    }
    
    public void setLocked(boolean newLocked)
    {
        this.Locked = newLocked;
    }
    public void setFriends (int friends)
    {
        this.Friends = friends;
    }
}
