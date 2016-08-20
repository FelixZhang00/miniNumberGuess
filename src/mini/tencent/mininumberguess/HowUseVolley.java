package mini.tencent.mininumberguess;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import mini.tencent.mininumberguess.util.CommJsonRequest;
import mini.tencent.mininumberguess.util.Constants;
import mini.tencent.mininumberguess.util.StringUtil;
import mini.tencent.mininumberguess.util.ToastUtil;
import mini.tencent.mininumberguess.util.VolleyUtil;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


/**
 * 娴嬭瘯椤碉紝浣跨敤volley鍙戣捣璇锋眰缃戠粶
 * 
 * @author felix
 *
 */
public class HowUseVolley extends Activity
{

    protected static final String TAG = "HowUseVolley";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        String url = "http://test.tv.video.qq.com/i-tvbin/open/get_sesskey?version=1&format=json";
        // 鍙戣捣璇锋眰
        Intent intent = new Intent();
        intent.setClass(this, WaitingActivity.class);
        startActivity(intent);
//        CommJsonRequest jsonRequest = new CommJsonRequest(url, new Response.Listener<JSONObject>()
//        {
//            @Override
//            public void onResponse(JSONObject response)
//            {
//                Log.d(TAG, "response -> " + response.toString());
//            }
//        }, new ErrorListener()
//        {
//            @Override
//            public void onErrorResponse(VolleyError arg0)
//            {
//            	
//                Log.d(TAG, "volley error: " + arg0.toString());
//            }
//        }, null);
//
//        // 璇锋眰鍔犱笂Tag,鐢ㄤ簬鍙栨秷璇锋眰
//        jsonRequest.setTag(this);
//
//        VolleyUtil.getQueue(this).add(jsonRequest);
    }
}
