package mini.tencent.mininumberguess;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import mini.tencent.mininumberguess.util.Constants;
import mini.tencent.mininumberguess.util.StringUtil;
import mini.tencent.mininumberguess.util.ToastUtil;
import mini.tencent.mininumberguess.util.VolleyUtil;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


/**
 * 测试页，使用volley发起请求网络
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
        // 发起请求
        JsonObjectRequest request = new JsonObjectRequest(StringUtil.preUrl(Constants.DEFAULT_JSON_REQUEST_URL), null,
                new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (!response.has("result")) {
                                return;
                            }

                            JSONObject result = response.getJSONObject("result");

                            if (!result.has("fctlist")) {
                                return;
                            }

                            JSONArray factoryArray = result.getJSONArray("fctlist");

                            if (factoryArray.length() == 0) {
                                return;
                            }

                            JSONObject factory = factoryArray.getJSONObject(0);

                            if (!factory.has("serieslist")) {
                                return;
                            }

                            JSONArray seriesArray = factory.getJSONArray("serieslist");


                            for (int i = 0; i < seriesArray.length(); i++) {
                                JSONObject series = seriesArray.getJSONObject(i);
                                Map<String, String> seriesMap = new HashMap<String, String>();

                                seriesMap.put("name", series.getString("name"));
                                seriesMap.put("level", "级别："+series.getString("levelname"));
                                seriesMap.put("price", "价格："+series.getString("price"));
                                
                                Log.d(TAG, "GET:"+series.getString("name"));
                            }
                        } catch (Exception e) {
                        }

                    }
                }, new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        Log.d(TAG,"volley error");
                    }
                });
        // 请求加上Tag,用于取消请求
        request.setTag(this);

        VolleyUtil.getQueue(this).add(request);
    }
}
