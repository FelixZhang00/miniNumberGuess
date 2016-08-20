package mini.tencent.mininumberguess;

import mini.tencent.mininumberguess.util.StringUtil;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification.Action;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 游戏中页面
 * 
 * @author felix
 *
 */
public class GamingActivity extends Activity
{
    private TextView mTvUserName;
    private TextView mTvRoomId;

    // TODO 数值范围控件
    private TextView mTvLeftBound;
    private TextView mTvRightBound;

    private TextView mTvLogMsg;

    private EditText mEtGuess;
    private ImageButton mBtnGuess;

    // TODO 用户列表

    //
    private int mLeftBound = 0, mRightBound = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming);

        initUI();
        initData();
    }

    private void initUI()
    {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_gaming);
        
        mTvRoomId=(TextView) findViewById(R.id.room_id);
        mTvUserName=(TextView) findViewById(R.id.user_name);
        
        mEtGuess = (EditText) findViewById(R.id.et_guess);
        mBtnGuess = (ImageButton) findViewById(R.id.btn_guess);
        mBtnGuess.setOnClickListener(new GuessBtnListener());

    }
    

    private void initData()
    {
        
    }


    /**
     * 判断数字是否在范围内
     */
    private boolean isBound(int numberInt)
    {
        return numberInt >= mLeftBound && numberInt <= mRightBound;
    }

    private class GuessBtnListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            String number = mEtGuess.getText().toString().trim();
            if (TextUtils.isEmpty(number))
            {
                mEtGuess.setError("输入不能为空"); // 给出错误提示
                mEtGuess.requestFocus();
                return;
            }
            if (!StringUtil.isNumeric(number))
            {
                mEtGuess.setError("请输入数字"); // 给出错误提示
                mEtGuess.requestFocus();
                return;
            }
            int numberInt = Integer.parseInt(number);
            if (!isBound(numberInt))
            {
                String msg = "请输入 " + mLeftBound + "-" + mRightBound + " 内的数字";
                mEtGuess.setError(msg); // 给出错误提示
                mEtGuess.requestFocus();
                return;
            }

            // TODO 发送数字并清空
            mEtGuess.setText("");

        }
    }

}
