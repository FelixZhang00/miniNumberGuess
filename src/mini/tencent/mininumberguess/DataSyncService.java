package mini.tencent.mininumberguess;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import mini.tencent.mininumberguess.util.CommJsonRequest;
import mini.tencent.mininumberguess.util.VolleyUtil;

public class DataSyncService extends Service {
	
	public static String DATA_SYNC_ACTION = "DATA_SYNC_ACTION";
	public static String SYNC_DATA = "SYNC_DATA";
	public static String SYNC_URL = "http://test.tv.video.qq.com/i-tvbin/open/get_sesskey?version=1&format=json";
	private Map<String,String> argsMap = new HashMap<String,String>();
	private DataSyncThread dataSyncThread = null;
	public DataSyncService() {
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.e("laozijinglaile", "laozijinglaile");
//		argsMap.put("roomid","0");
//		argsMap.put("userid","0");
		if(dataSyncThread == null){
			dataSyncThread = new DataSyncThread();
			dataSyncThread.start();
		}
	}
	
	public void sendRequest(){
	        // 发起请求
	        CommJsonRequest jsonRequest = new CommJsonRequest(SYNC_URL, new Response.Listener<JSONObject>()
	        {
	            @Override
	            public void onResponse(JSONObject response)
	            {
	            	Intent brocastIntent = new Intent(DATA_SYNC_ACTION);
	            	brocastIntent.putExtra(SYNC_DATA, response.toString());
	            	Log.i("message back", response.toString());
	            	DataSyncService.this.sendBroadcast(brocastIntent);
	            	try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						sendRequest();
					}
	            }
	        }, new ErrorListener()
	        {
	            @Override
	            public void onErrorResponse(VolleyError arg0)
	            {
						sendRequest();
	            }
	        }, argsMap);
	        // 请求加上Tag,用于取消请求
	        jsonRequest.setTag(this);
	        VolleyUtil.getQueue(this).add(jsonRequest);
	}
	
	private class DataSyncThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			sendRequest();
		}
	}
	
	
}
