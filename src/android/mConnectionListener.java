package com.meschiari.glassup.plugin;


import glassup.service.GlassUpServiceInterface;
import glassup.service.GlassUpAgentInterface.ConnectionListener;

/**
 * 
 * @author Blescia Antonio
 *This mConnectionListner class gets the connection status from the GlassUpService between the smartphone and the GlassUp Device
 */
public class mConnectionListener implements ConnectionListener{

	//Called every time the connection state changes
	@Override
	public void onConnectionChanged(int connectionStatus) {
		if (connectionStatus == GlassUpServiceInterface.CONNECTION_STATUS_CONNECTED)
		{
			GlassUp.mServiceConnected = true;
			
		}	
		else
		{
			GlassUp.mServiceConnected= false;
			
			
		}
		
	}

}
