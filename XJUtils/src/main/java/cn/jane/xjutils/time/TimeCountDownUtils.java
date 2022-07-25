package cn.jane.xjutils.time;

import android.os.CountDownTimer;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/22
 * @describe 描述：倒计时
 */
public class TimeCountDownUtils {


    /**
     * 开始倒计时
     *
     * @param second 计时时间（单位：秒）
     */
    public static void start(int second, TimeCountDownListener listener) {
        start(second * 1000, 1000, listener);
    }

    /**
     * 开始倒计时
     * @param millis 计时时间（单位：毫秒）
     * @param interval 计时间隔时间（单位：毫秒）
     * @param listener 计时监听
     */
    public static void start(int millis, int interval, TimeCountDownListener listener) {
        countDown(millis, interval, listener);
    }

    public static void countDown(int millis, int interval, TimeCountDownListener listener) {
        if (millis > 0) {
            CountDownTimer countDownTimer = new CountDownTimer(millis, interval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    listener.countDown(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    listener.complete();
                }
            };
            countDownTimer.start();
        } else {
            listener.complete();
        }

    }



    public interface TimeCountDownListener {
        void countDown(long newTime);

        void complete();
    }
}
