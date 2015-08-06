package com.meschiari.glassup.plugin;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import glassup.service.GlassUpAgent;
import glassup.service.GlassUpAgentInterface.ConnectionListener;


public class GlassUp extends CordovaPlugin {

  	//Used to send the message to the handler class
  	public final int MSG_WHAT_SEND_CONFIG	= 1;
	
  	//Used to get the connection state and the configuration state.
  	public static boolean mServiceConnected;
  	public static boolean mAppConfigured;

  	/*The GlassUp agent and its listners*/
  	public static GlassUpAgent mAgent;
    AgentEventListner eventListener;
    AgentContentListener contentResultListener;
  	ConnectionListener mConnListener;
    ConfigurationHandler mHandler;

  	/*The conter id*/
  	int counter=0;

  	@Override
  	public void onDestroy() {
  		super.onDestroy();
  		/*The agent follows the Activity lifecycle*/
  		mAgent.onDestroy();
  	}

  	@Override
  	public void onPause(boolean multitasking) {
  		// TODO Auto-generated method stub
  		super.onPause(multitasking);
  		/*The agent follows the Activity lifecycle*/
  		mAgent.onPause();
  	}

  	@Override
  	public void onResume(boolean multitasking) {
  		// TODO Auto-generated method stub
  		super.onResume(multitasking);
  		/*The agent follows the Activity lifecycle*/
  		mAgent.onResume();
  	}

    @Override
    protected void pluginInitialize() {
  		/*The listners declariations*/
  		eventListener = new AgentEventListner();
  		contentResultListener = new AgentContentListener();
  		mConnListener = new mConnectionListener();
  		mAgent = new GlassUpAgent();
  		/*The agent follows the Activity lifecycle*/
  		mAgent.onCreate(this.cordova.getActivity().getApplicationContext());
	
  		/*Set the agent's listners*/
  		mAgent.setEventListener(eventListener);
  		mAgent.setContentResultListener(contentResultListener);
  		mAgent.setConnectionListener(mConnListener);
	
  		/*Declare the configuration handler*/
  		mHandler = new ConfigurationHandler();
	
  		mAppConfigured = mAgent.isConfigured(); // the agent is configured?
  		if (!mAppConfigured){		
  			/*Not configured*/
  			Log.d("TAG","App not configured, Scheduling send configure");
  			mAppConfigured = false;
  			/*Send the configuration message*/
  			mHandler.sendEmptyMessage(MSG_WHAT_SEND_CONFIG);
  		}	
  		else {
  			/*Already configured*/
  			Log.d("TAG","App Already configured");
  			mAppConfigured = true;
  		}

    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("echo")) {
            String message = args.getString(0);
            this.echo(message, callbackContext);
            return true;
        }
        return false;
    }

    private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            mAgent.sendContent(counter++, 0, null, new String[]{message});
            callbackContext.success(message);
            Log.d("TAG","Chiamato!");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
            Log.d("TAG","Expected one non-empty string argument.");
        }
    }
}
