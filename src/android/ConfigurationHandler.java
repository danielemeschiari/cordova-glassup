package com.meschiari.glassup.plugin;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

// import com.meschiari.glassup.plugin.R;

/**
 * 
 * @author Blescia Antonio
 * This class send a message to the GlassUpService and set its configuration.
 */
public class ConfigurationHandler extends Handler{
	public static final int	MSG_WHAT_SEND_CONFIG	= 1;
	
	@Override
	public void handleMessage(Message msg)
	{
		super.handleMessage(msg);
		if (msg.what == MSG_WHAT_SEND_CONFIG)
		{
			removeMessages(MSG_WHAT_SEND_CONFIG);

			GlassUp.mAppConfigured = GlassUp.mAgent.isConfigured();
			
      if (!GlassUp.mAppConfigured)
      {
        if (GlassUp.mServiceConnected){
          if (GlassUp.mAgent.isConnected())
          {
            Log.d("TAG","App sendConfigure()");
            GlassUp.mAgent.sendConfigure(new int[]{android.R.drawable.sym_def_app_icon});
          }
        }
        sendEmptyMessageDelayed(MSG_WHAT_SEND_CONFIG, 10000);
      }
      else
      {
  			Log.d("TAG","App configured");
        // Toast.makeText(getApplicationContext(), "App configured", Toast.LENGTH_LONG).show();
      }
		}

	}
}
