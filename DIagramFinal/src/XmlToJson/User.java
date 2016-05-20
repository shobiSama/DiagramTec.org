package XmlToJson;

import java.util.List;

public class User {
	private String gender;
	private Name name;
	private boolean verified;
	
	public String getGender(){
		return this.gender;
	}
	public void setGender(String gender){
		this.gender=gender;
	}
	public Name getName(){
		return this.name;
	}
	public void setName(Name name){
		this.name=name;
	}
	public boolean isVerified(){
		return this.verified;
	}
	public void setVerified(boolean verified){
		this.verified=verified;
	}

}
