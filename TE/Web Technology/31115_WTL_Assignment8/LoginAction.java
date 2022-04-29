package mypkg;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport
{
	
	private String name;
	private String email;
	private String mobNo;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobNo() {
		return mobNo;
	}


	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String execute()
	{
		return "success";
	}
	
    public void validate() 
    {
        if (getName().length() == 0) 
        {
            addFieldError("name", "Name is required");
        } 
        else if (!getName().equals("Mayur")) 
        {
            addFieldError("name", "Invalid Name!!");
        }
        
        if(getMobNo().length() == 0) 
        {
            addFieldError("mobNo", "Mobile Number is required");
        }
        else if (!getMobNo().equals("9607650950")) 
        {
            addFieldError("mobNo", "Invalid Mobile Number!!");
        }
        
        if(getEmail().length() == 0) 
        {
            addFieldError("email", "Email is required");
        }
        else if (!getEmail().equals("mayurmote8055@gmail.com")) 
        {
            addFieldError("email", "Invalid Email!!");
        }
    }

	

}
