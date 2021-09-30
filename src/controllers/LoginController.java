package controllers;




public class LoginController {


    //Method declaration - return type User - returns a user object
    //Requires two string values (un & pw) to run.
//    public User validateLogin(String un, String pw) {
//        User u = InitDB.checkLoginDetails(un, pw);
//        if(u != null){
//            return u;
//        }
//        else {
//            return null;
//        }
//    }
}





//    private String username;
//    private Character password;
//    private String bank;
//    private Calendar welcome;
//
//
//    public LoginController(String username, Character password, String bank, Calendar welcome) {
//        this.username = username;
//        this.password = password;
//        this.bank = bank;
//        this.welcome = welcome;
//    }
//   / * ***** REQUIREMENTS ******
//1. User needs to have a username and password to log into banking app
//method: private String userLogin();
//sout: Enter Username = new instance of userName
//      Enter password = new instance of password
//2. private boolean userRegisterValidate();
//If True = username && password - need to query user input from database; displayAccountMenu if ==1 account, else >1 account
//displayMainMenu; else if no account, call createAccount();
//False = createUsername and createPassword method - Q: are we calling User model class setters i.e. 'setAccount' to do this, or
//are we using registerUser(); to call this?
//3. User can be welcomed depending on time and day of log-in
//If time = 00:00-11:59 "Good Morning" + name + "Welcome to the " + bank + " app.",
//else if time = 12:00-17:30 "Good Afternoon " + name + "Welcome to the " + bank + " app.",
//else if time 17:31-23:59 "Good Evening " + name + "Welcome to the " + bank + " app." - check in case this is too complicated.
//     */

