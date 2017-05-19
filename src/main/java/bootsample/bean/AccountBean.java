package bootsample.bean;

/**
 * Created by adarsh on 05/04/17.
 */
public class AccountBean {
	
	public AccountBean() {
	
	}
	
    public AccountBean(String string) {
		name=string;
	}
	public String owner_key;
	public Long acountId;
    public String name;
    public String active_key;
    public String referrer;
    public String refcode;
    public String memo_key;
	public String getOwnerKey() {
		return owner_key;
	}
	
	
}
