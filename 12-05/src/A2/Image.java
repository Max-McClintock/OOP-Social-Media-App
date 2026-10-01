
package A2;


public class Image extends Posts
{
    private String Name;
    private String Description;
    private String Location;
    
    public Image(int PostID, int UserID, String PostSecurity, String Date, String Name, String Description, String Location)
    {
        super(PostID, UserID, PostSecurity, Date);
        this.Name = Name;
        this.Description = Description;
        this.Location = Location;
        
    }
    
    public String getName()
    {
        return this.Name;
    }
    
    public String getDescription()
    {
        return this.Description;
    }
    
    public String getLocation()
    {
        return this.Location;
    }
    
    public String details()
    {
        String info = "";
        info += super.details();
        info += "";
        info+= "<br>Name " +this.Name + "<br> ";
        info += "Description " + this.Description+ "<br>";
        info += "Location " + this.Location;
        info += "</html>";
        return info;
    }
   }
