package mini.tencent.mininumberguess;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class WaitingActivity extends Activity {

	private TextView test = null;
	private int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiting);
		test = (TextView)findViewById(R.id.waitingText);
		Intent intent = new Intent();
		intent.setClass(this, DataSyncService.class);
		startService(intent);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		IntentFilter filter = new IntentFilter(DataSyncService.DATA_SYNC_ACTION);
		registerReceiver(SyncDataReceiver, filter);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		unregisterReceiver(SyncDataReceiver);
	}
	
	
	private BroadcastReceiver SyncDataReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String str = intent.getStringExtra(DataSyncService.SYNC_DATA);
			count++;
			test.setText("这是第" + count + "次受到了广播消息: " + str);
		}
	};
	

}
