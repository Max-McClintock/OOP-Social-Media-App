
package A2;


public class Text extends Posts
{
    private String Text;
    
    
    public Text (int postID, int userID, String postSecurity, String Date, String text)
    {
        super(postID, userID, postSecurity, Date);
        
        this.Text = text;
        
    }
        public String getText()
    {
        return this.Text;
    }
    
        
    public String details()
    {
        String info = "";
        info += super.details();
        info += "";
        info+= "<br>Text   " +this.Text;
        info += "</html>";
        return info;
    }
    
}
