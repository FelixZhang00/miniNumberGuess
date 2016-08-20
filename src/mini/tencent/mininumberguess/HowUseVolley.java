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
import android.os.Bundle;
import android.util.Log;


/**
 * 测试页，使用volley发起请求网络
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
        // 发起请求
        CommJsonRequest jsonRequest = new CommJsonRequest(url, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d(TAG, "response -> " + response.toString());
            }
        }, new ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError arg0)
            {
                Log.d(TAG, "volley error");
            }
        }, null);

        // 请求加上Tag,用于取消请求
        jsonRequest.setTag(this);

        VolleyUtil.getQueue(this).add(jsonRequest);
    }
}
