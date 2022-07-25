package cn.jane.xjutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.jane.xjutils.android.app.AppUtils;
import cn.jane.xjutils.android.log.LogUtils;
import cn.jane.xjutils.android.permission.PermissionUtils;
import cn.jane.xjutils.android.textview.EditUtils;
import cn.jane.xjutils.android.toast.ToastUtils;
import cn.jane.xjutils.android.version.VersionUtils;
import cn.jane.xjutils.time.TimeCountDownUtils;

public class MainActivity extends AppCompatActivity {

    String json = "{\"id\":\"4534\",\"name\":\"Jane\",\"age\":1,\"class\":[{\"id\":\"1\",\"className\":\"中二班\",\"studentNum\":35},{\"id\":\"2\",\"className\":\"英语补习班\",\"studentNum\":5},{\"id\":\"10\",\"className\":\"运动课\",\"studentNum\":100}],\"sex\":\"男\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textview);
        EditText editText = findViewById(R.id.edittext);
        // TODO TEST
//        LogUtils.e(json);
//        VersionUtils.getVersionName();
//        VersionUtils.getVersionCode();

        ToastUtils.setGravity(Gravity.TOP);
        ToastUtils.ignoreIcLimit(true);
        ToastUtils.ignoreTextLimit(false);
        ToastUtils.setOrientation(LinearLayout.HORIZONTAL);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditUtils.copy(textView.getText().toString());
                ToastUtils.showSuc(json);
            }
        });

        editText.setText(EditUtils.getTextFromClip());

        TimeCountDownUtils.start(10, new TimeCountDownUtils.TimeCountDownListener() {
            @Override
            public void countDown(long newTime) {
                LogUtils.e("----countDown" + newTime + "");
            }

            @Override
            public void complete() {
                LogUtils.e("----complete");
            }
        });
    }
}