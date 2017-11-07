package Mess;

import java.util.Date;



public interface Employee {
	//public String responsibility();
	//public boolean markAttendance();
	//public boolean recSalary();
	//public String getName();
	//public boolean setName(String name);
	//public String getMob();
	//public boolean setMob(String mob);
	public void updateName(String name,String id);
	public void updateMobile(String mob,String id);
	public void updateAddress(String address,String id);
	public void applyForLeave(Date start,Date end,String id);
	public void leaveStatus(String id);
	public void updateEmail(String email, String id);
	public void updatePassword(String password, String id);
}
