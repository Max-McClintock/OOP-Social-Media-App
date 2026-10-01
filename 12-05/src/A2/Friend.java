
package A2;


public class Friend 
{
    private int UserID;
    private int FriendID;
    private String DateTime;
    
    
    public Friend (int UserID, int FriendID, String DateTime)
    {
        this.UserID = UserID;
        this.FriendID = FriendID;
        this.DateTime = DateTime;
    }
    
    public int getUserID()
    {
        return UserID;
    }
    
    public int getFriendID()
    {
        return FriendID;
    }
    
    public String getDateTime()
    {
        return DateTime;
    }
    public String basicInfo()
    {
        String output = "Friend ID " + this.FriendID + " User ID" + this.UserID;
        return output;
    }
}

