
package A2;

import java.util.ArrayList;
import java.time.*;
import java.time.format.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppLib 
{
    public static OpeningMenu scrMenu;  //1
    public static frmRegister scrRegister; //2
    public static frmLogin scrLogin;//3
    public static SplashScreen scrSplash; //4
    public static frmMainMenu scrMMenu;  //5
    public static frmAddPost scrAddPost;  //6
    public static frmMyPosts scrMyPosts;  //7
    public static frmAddFriend scrAddFriend; //8
    public static frmPublicPosts scrPublicPosts; // 9
    public static frmFriendsMenu scrFriendsMenu; //10
    public static frmViewFriendsPosts scrViewFriendsPosts; //11
    
    public static ArrayList usersAL = new ArrayList();
    public static ArrayList postsAL = new ArrayList();    
    public static ArrayList friendsAL = new ArrayList();
    
    public static User loggedOn;
    
    
    public static String genDate()
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");       
        
        String dateStr = today.format(formatter);
        return dateStr;
    }    
    
    public static String genDateTime()
    {
        LocalDateTime todayDT = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");       
        
        String dateStr = todayDT.format(formatter);
        return dateStr;
    }    

    public static void backupUsers()
    {
        String filePath = "Users.txt";
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for(Object obj : usersAL)
            {
                //int UserID, String FName, String SName, String DOB, String Email, String PWord, Boolean Locked, String LLogin, String RDate, int Friends
                User user = (User)obj;
                String bigStr = user.getUserID() + "," + 
                                user.getFName() + "," +
                                user.getSName() + "," +
                                user.getDOB() + "," +
                                user.getEmail() + "," +
                                user.getPWord() + "," +
                                user.getLocked() + "," +
                                user.getLLogin() + "," + 
                                user.getRDate() + "," + 
                                user.getFriends() + ",";
                writer.write(bigStr);
                writer.newLine();     
            }
            System.out.println("File written successfully: " + filePath);
            
        }
        catch (IOException e) {
            System.err.println("Error writing to file: "  + e.getMessage());
        }
    }
    
    public static void restoreUsers()
    {
        String filePath = "Users.txt";
        
        if(!Files.exists(Path.of(filePath)))
        {
            System.err.println("Error: File not found -> " + filePath);
            return;                    
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
             
            while ((line = reader.readLine()) != null)
            {
                File file = new File(filePath);
                //System.out.println("DEBUG: Java is reading from: " + file.getAbsolutePath());
                //System.out.println("DEBUG: Raw line content is: [" + line + "]");
                //System.out.println("H");
                String [] userLine = line.split(",");
                //System.out.println("e");
                User tempUser = new User(0,userLine[1], userLine[2], userLine[3], userLine[4], userLine[5], false, userLine[7], userLine[8], 0);
                //System.out.println("R");
                tempUser.setUserID(Integer.parseInt(userLine[0]));
                //System.out.println("2");
                tempUser.setLocked(Boolean.valueOf(userLine[6]));
                //System.out.println("3");
                tempUser.setFriends(Integer.parseInt(userLine[9]));
                //System.out.println("4");
                usersAL.add(tempUser);
            }
        }
        catch (IOException e)
        {
             System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    public static Boolean isUniqueUID(int ID)
    {
        for(int index=0; index < usersAL.size(); index++)
        {
            User auser = (User)usersAL.get(index);
            if(auser.getUserID() == ID)
            {
                return false;
            }      
        }    
        return true;
    }
    
    public static Boolean isUniquePostID(int ID)
    {
        for(int index=0; index < postsAL.size(); index++)
        {
            Posts post = (Posts)postsAL.get(index);
            if(post.getPostID() == ID)
            {
                return false;
            }      
        }    
        return true;
    }   
    public static boolean validLogon(String email, String Pword)
    {
        for(int index=0; index < usersAL.size(); index++)
        {
            User auser = (User)usersAL.get(index);
            if(auser.getEmail().equalsIgnoreCase(email) && auser.getPWord().equals(Pword))
            {
                auser.setLLogin(genDateTime());
                loggedOn = auser;
                return true;
            }      
        }    
        //loggedOn = null;
        return false;        
    }
    
    
    public static Posts findPost(int pid)
    {
        for(int index=0; index < postsAL.size(); index++)
        {
            Posts post = (Posts)postsAL.get(index);
            if(post.getPostID() == pid)
            {
                return post;
            }      
        }    
        return null;        
    }
    
    
    public static User findUser(int uid)
    {
        for(int index=0; index < usersAL.size(); index++)
        {
            User FoundUser = (User)usersAL.get(index);
            if(FoundUser.getUserID() == uid)
            {
                return FoundUser;
            }      
        }    
        return null;        
    }    
    
    public static boolean friendAlready(int uid, int fid)
    {
        for(int index=0; index< friendsAL.size(); index ++ )
        {
            Friend frd = (Friend) friendsAL.get(index);
            if(frd.getUserID() == uid && frd.getFriendID() == fid)
            {
                return true;
            }
        }
        return false;
    }
    
    

    public static void backupFriends()
    {
        String filePath = "Friends.txt";
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for(Object obj : friendsAL)
            {
                //int UserID, String FName, String SName, String DOB, String Email, String PWord, Boolean Locked, String LLogin, String RDate, int Friends
                Friend Friend = (Friend)obj;
                String bigStr = Friend.getUserID() + "," + 
                                Friend.getFriendID()+ "," +
                                Friend.getDateTime();

                writer.write(bigStr);
                writer.newLine();                 
            }
            System.out.println("File written successfully: " + filePath);
            
        }
        catch (IOException e) {
            System.err.println("Error writing to file: "  + e.getMessage());
        }
    }
    
    public static void restoreFriends()
    {
        String filePath = "Friends.txt";
        
        if(!Files.exists(Path.of(filePath)))
        {
            System.err.println("Error: File not found -> " + filePath);
            return;                    
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
             
            while ((line = reader.readLine()) != null)
            {
                File file = new File(filePath);
                //System.out.println("DEBUG: Java is reading from: " + file.getAbsolutePath());
                //System.out.println("DEBUG: Raw line content is: [" + line + "]");
                //System.out.println("H");
                String [] friendLine = line.split(",");
                //System.out.println("e");
                Friend tempFriend = new Friend(Integer.parseInt(friendLine[0]),Integer.parseInt(friendLine[1]), friendLine[2]);
                //System.out.println("R");
                friendsAL.add(tempFriend);
            }
        }
        catch (IOException e)
        {
             System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    
    public static void backupPosts() // Post could be image or text
    {
        String filePath = "Posts.txt";
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for(Object obj : postsAL)
            {
                Posts Posts = (Posts)obj;      
                System.out.println(obj);
//                String bigStr = Posts.getPostID() + "," + 
//                                Posts.getUserID()+ "," +
//                                Posts.getPostSecurity() + "," +
//                                Posts.getDate();
//                writer.write(bigStr);
//                writer.newLine();     
                int numOfThings = obj.getClass().getDeclaredFields().length;
                System.out.println(numOfThings);
                if (numOfThings == 3) // three extra on top of the parent class posts
                {
                    System.out.println("15");
                    Image Image = (Image) Posts;
                    //nt PostID, int UserID, String PostSecurity, String Date, String Name, String Description, String Locati
                    String bigStr2 = Image.getPostID() + "," +
                                     Image.getUserID() + "," +
                                     Image.getPostSecurity() + "," +
                                     Image.getDate() + "," +
                                     Image.getName() + "," +
                                     Image.getDescription() + "," + 
                                     Image.getLocation();
                    writer.write(bigStr2);
                    writer.newLine(); 
                }
                else if (numOfThings == 1)// one extra ontop of the parent class posts
                {
                    System.out.println("16");
                    Text Text = (Text) Posts;
                    String bigStr3 = Text.getPostID() + "," +
                                     Text.getUserID() + "," +
                                     Text.getPostSecurity() + "," +
                                     Text.getDate() + "," +   
                                     Text.getText();
                    writer.write(bigStr3);
                    writer.newLine(); 
                }
                else
                {
                    
                }

         
            }
            System.out.println("File written successfully: " + filePath);
            
        }
        catch (IOException e) {
            System.err.println("Error writing to file: "  + e.getMessage());
        }
    }
    
    public static void restorePosts()
    {
        String filePath = "Posts.txt";
        
        if(!Files.exists(Path.of(filePath)))
        {
            System.err.println("Error: File not found -> " + filePath);
            return;                    
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
             
            while ((line = reader.readLine()) != null)
            {
                System.out.println("THis 1");
                File file = new File(filePath);
//                System.out.println("DEBUG: Java is reading from: " + file.getAbsolutePath());
//                System.out.println("DEBUG: Raw line content is: [" + line + "]");
//                System.out.println("H");
                String [] postLine = line.split(",");
                System.out.println(postLine.length);
//                System.out.println("e");
//                Posts tempPost = new Posts(Integer.parseInt(postLine[0]),Integer.parseInt(postLine[1]), postLine[2], postLine[3]);
//                System.out.println("R");
//                postsAL.add(tempPost);
                if (postLine.length == 7)
                {
                    Image tempImage = new Image(Integer.parseInt(postLine[0]),Integer.parseInt(postLine[1]), postLine[2], postLine[3]
                                                , postLine[4], postLine[5], postLine[6]);
                    postsAL.add(tempImage);
                    System.out.println("Here");
                }
                if (postLine.length == 5)
                {
                    Text tempText = new Text(Integer.parseInt(postLine[0]),Integer.parseInt(postLine[1]), postLine[2], postLine[3]
                                                , postLine[4]);
                    postsAL.add(tempText);
                    System.out.println("Here");
                }
            }
        }
        catch (IOException e)
        {
             System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public static void loadData()
{
//        User tmpUser = new User(3355, "frank", "stein", "02/03/44", "1", "1", false,"09/05/26", "00/05/26", 0);
//        tmpUser.setLocked(false);
//        tmpUser.setFriends(0);
////        tmpUser.setLastLogin("N/A");
//        usersAL.add(tmpUser);
//            
//        tmpUser = new User(4242, "betty", "boop", "08/03/77", "b@b.com", "bye",false, "00/1/25", "09/05/26", 0);
//        tmpUser.setLocked(false);
//        tmpUser.setFriends(0);
////        tmpUser.setLastLogin("N/A");
//        usersAL.add(tmpUser);
//        
//        tmpUser = new User(4944, "jenny", "wot", "20/05/99", "j@wot.com", "what", false, "09/05/26", "05/03/26", 0);
//        tmpUser.setLocked(false);
//        tmpUser.setFriends(0);
////        tmpUser.setLastLogin("N/A");
//        usersAL.add(tmpUser);
//        
//        tmpUser = new User(1007, "james", "bond", "07/06/02", "j@bond.com", "spy", false, "09/05/26", "04/05/26", 0 );
//        tmpUser.setLocked(false);
//        tmpUser.setFriends(0);
////        tmpUser.setLastLogin("N/A");
//        usersAL.add(tmpUser);
//        
//        tmpUser = new User(8888, "Ted", "Bear", "12/10/01", "t@b.com", "pass", false, "03/04/26", "07/05/26", 0);
//        tmpUser.setLocked(false);
//        tmpUser.setFriends(0);
////        tmpUser.setLastLogin("N/A");
//        usersAL.add(tmpUser);
        Image imgPost;
        Text txtPost;
        imgPost = new Image(23456, 3355, "public", "08/05/26 11:54:20", "myfile.txt", "big file", "c:/temp");
        AppLib.postsAL.add(imgPost);
        
        txtPost = new Text(23467, 3355, "private", "08/05/26 11:54:30",
                "mary had dinner");
        AppLib.postsAL.add(txtPost);
        imgPost = new Image(23400, 4242, "public", "08/05/26 11:55:20","myfile.txt", "big file", "c:/temp");
        AppLib.postsAL.add(imgPost);
        txtPost = new Text(23404, 4242, "private", "08/05/26 11:56:30",
                "mary had supper");
        AppLib.postsAL.add(txtPost);
        imgPost = new Image(23401, 4242, "public", "08/05/26 11:55:20",
                "yourfile.txt", "small file", "c:/temp");
        AppLib.postsAL.add(imgPost);
        txtPost = new Text(23444, 3355, "public", "08/05/26 11:56:30",
                "mary had breakfast");
        AppLib.postsAL.add(txtPost);
        
    }//loadData
}
