import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Person{
	public int userId;
	public String username;
	public String fullName;
	public String age;
	public String email;
	public String phone;

	public boolean isAdmin;

	public Person(int userId, String username, String fullName, String age, String email, String phone, boolean isAdmin) {
		this.userId = userId;
		this.username = username;
		this.fullName = fullName;
		this.age = age;
		this.email = email;
		this.phone = phone;

		this.isAdmin = isAdmin;
	}

	public void setFullName(String fullName) {this.fullName = fullName;}
	public void setAge(String age) {this.age = age;}
	public void setEmail(String email) { this.email = email;}
	public void setPhone(String phone) {this.phone = phone;}

	public static void signUp(){
		Scanner sc = new Scanner(System.in);
		String _userName = " ";
		String _password = " ";
		String _fullName = " ";
		String _age = " ";
		String _email = " ";
		String _phoneNumber = " ";
		while(true){
			System.out.print("Enter Username: ");
			String userName = sc.nextLine().trim();
			if(userNameValidation(userName)){
				_userName = userName;
				break;
			} else {
				System.out.println("Invalid username input, please enter again!!!");
				continue;
			}
		}
		while(true){
			System.out.print("Enter Password: ");
			String password = sc.nextLine().trim();
			if(passwordValidation(password)){
				_password = password;
				break;
			} else{
				System.out.println("Invalid password input, please enter again!!!");
				continue;
			}
		}
		while(true){
			System.out.print("Enter full name: ");
			String fullName = sc.nextLine().trim();
			if(nameValidation(fullName)){
				_fullName = fullName;
				break;
			}
		}
		while(true){
			System.out.print("Enter age: ");
			String age = sc.nextLine().trim();
			if(ageValidation(age)){
				_age = age;
				break;
			} else {
				System.out.println("Invalid age input, please enter again!!!");
				continue;
			}
		}
		while(true){
			System.out.print("Enter Email: ");
			String email = sc.nextLine().trim();
			if(emailValidation(email)){
				_email = email;
				break;
			} else {
				System.out.println("Invalid email input, please enter again!!!");
				continue;
			}
		}
		while(true){
			System.out.print("Enter Phonenumber: ");
			String phoneNumber = sc.nextLine().trim();
			if(phoneNumberValidation(phoneNumber)){
				_phoneNumber = phoneNumber;
				break;
			} else {
				System.out.println("Invalid phone-number input, please enter again!!!");
				continue;
			}
		}
		// System.out.print("Enter password: ");
		// String password = sc.nextLine().trim();
		// System.out.print("Enter Full name: ");
		// String fullName = sc.nextLine().trim();
		// System.out.print("Enter age: ");
		// String age = sc.nextLine();
		// System.out.print("Enter email: ");
		// String email = sc.nextLine().trim();
		// System.out.print("Enter phone number: ");
		// String phoneNumber = sc.nextLine().trim();
		int isAdmin = 0;

		
		String query = String.format("INSERT INTO person(f_name,age,username,password,phone,email,isAdmin) values('%s',%d,'%s','%s','%s','%s',%d)"
										,_fullName,Integer.parseInt(_age),_userName,_password,_phoneNumber,_email,isAdmin);
		Database.updateQuery(query);

		System.out.print("Sign Up successfully!!!\n"
						+"-----------------------------\n"
						+"-----------------------------\n"
						+"-----------------------------\n"
						);		
	}

	public static Map login() {
		Map user = new HashMap();

		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------");
		System.out.print("Enter username: ");
		String username = scanner.nextLine().trim();
		System.out.println("");
		System.out.print("Enter password: ");
		String password = scanner.nextLine().trim();

		try {
			String query = String.format("select * from person where username='%s' and password='%s'", username, password);
			ResultSet rs = Database.runQuery(query);
			// check if query returns anything
			if (isResultSetEmpty(rs)) {
				System.out.println("Wrong username or password, please try again!");
				return null;
			}
			while (rs.next()) {
				user.put("userID", rs.getInt("userID"));
				user.put("username", rs.getString("username"));
				user.put("password", rs.getString("password"));
				user.put("email", rs.getString("email"));
				user.put("name", rs.getString("f_Name"));
				user.put("age", rs.getString("age"));
				user.put("email", rs.getString("email"));
				user.put("phone", rs.getString("phone"));
				user.put("totalSpending", rs.getDouble("totalSpending"));
				user.put("membership", rs.getString("membership"));
				user.put("isAdmin", rs.getBoolean("isAdmin"));
			}
		} catch (SQLException e) {
			System.out.println("Login error");
		}

		return user;
	}

	public static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		// from https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results/15750832#15750832
		return (!rs.isBeforeFirst() && rs.getRow() == 0);
	}

	public static void showActionMenu() {
		
	}

	public static boolean userNameValidation(String userName){
		String pattern = "^[a-zA-Z][a-zA-z0-9_]{6,19}$";
		
        return userName.matches(pattern);

	}

    public static boolean passwordValidation(String password){
        String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{8,20}$";

        return password.matches(pattern);
    }

    public static boolean phoneNumberValidation(String phoneNumber){
        String pattern = "^\\d{10,12}$";

        return phoneNumber.matches(pattern);
    }

    public static boolean nameValidation(String fullName){
        String pattern = "^[a-zA-Z\\s]+";

        return fullName.matches(pattern);
    }

    public static boolean ageValidation(String age){
        String pattern = "^\\d{1,3}$";

        return age.matches(pattern);
    }

    public static boolean emailValidation(String email){
        String pattern = "^(.+)@(.+)$";

        return email.matches(pattern);
    }
}