

public class Task {
	private String description = null;
	private boolean isComplete = false;
	public String tag = null;

	
	public Task(String description) {
		super();
		this.description = description;
	}
	
	public Task(String description, boolean isComplete, String tag) {
		super();
		this.description = description;
		this.isComplete = isComplete;
		this.tag = tag;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	
	
	

}
