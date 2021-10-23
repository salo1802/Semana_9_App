package events;

public interface OnMessageListener {

	public void onOrderReceived();
	public void  onOrderDenied();
	public void onOrderReady();
	public int getPort();
	
	
}
