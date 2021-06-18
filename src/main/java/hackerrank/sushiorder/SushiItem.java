package hackerrank.sushiorder;

public class SushiItem {
	private int seatId;
	private String menuItemId;
	
	
	public SushiItem(int seatId, String menuItemId) {
		this.seatId = seatId;
		this.menuItemId = menuItemId;
	}
	
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	
    public int getSeatId() {
    	return this.seatId;
    }
    
    public void setMenuItemId(String menuItemId) {
    	this.menuItemId = menuItemId;
    }
    
    public String getMenuItemId() {
    	return this.menuItemId;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuItemId == null) ? 0 : menuItemId.hashCode());
		result = prime * result + seatId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SushiItem other = (SushiItem) obj;
		if (menuItemId == null) {
			if (other.menuItemId != null)
				return false;
		} else if (!menuItemId.equals(other.menuItemId))
			return false;
		if (seatId != other.seatId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SushiItem [seatId=" + seatId + ", menuItemId=" + menuItemId + "]";
	}
}
