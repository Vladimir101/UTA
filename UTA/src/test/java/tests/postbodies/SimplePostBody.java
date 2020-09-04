package tests.postbodies;

public class SimplePostBody
{
	private String orderID;
	
	public SimplePostBody(String id)
	{
		orderID = id;
	}

	public String getOrderID()
	{
		return orderID;
	}

	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}
	
	
}
