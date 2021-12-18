package cn.ljpc.lockscreen

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.os.Vibrator
import android.util.Log

object ToolUtils {

    private val TAG = ToolUtils::class.simpleName

    var vibrator: Vibrator? = null
    private var wakeLock: PowerManager.WakeLock? = null

    @SuppressLint("InvalidWakeLockTag")
    fun acquire(context: Context) {
        try {
            //获取电源管理器对象
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            //获取PowerManager.WakeLock对象
            wakeLock =
                pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_DIM_WAKE_LOCK,
                    "bright"
                )
            //点亮屏幕30秒
            wakeLock?.acquire(30 * 1000);
            //灭屏（释放锁）
            if (null != wakeLock) {
                wakeLock?.release();
            }
            val km: KeyguardManager =
                context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            //这里参数”unLock”作为调试时LogCat中的Tag
            val kl = km.newKeyguardLock("unLock") as KeyguardManager.KeyguardLock
            //解锁
            kl.disableKeyguard()
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
        }
    }

    fun playVibrate(context: Context, isRepeat: Boolean) {

        /*
         * 设置震动，用一个long的数组来表示震动状态（以毫秒为单位）
         * 如果要设置先震动1秒，然后停止0.5秒，再震动2秒则可设置数组为：long[]{1000, 500, 2000}。
         * 别忘了在AndroidManifest配置文件中申请震动的权限
         */
        try {
            vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val pattern = longArrayOf(1000, 500, 2000)
            var audioAttributes: AudioAttributes? = null;
            /**
             * 适配android7.0以上版本的震动
             * 说明：如果发现5.0或6.0版本在app退到后台之后也无法震动，那么只需要改下方的Build.VERSION_CODES.N版本号即可
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM) //key
                    .build()
                vibrator!!.vibrate(pattern, if (isRepeat) 1 else -1, audioAttributes);
            } else {
                vibrator!!.vibrate(pattern, if (isRepeat) 1 else -1)
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
        }
    }

    /**
     * 关闭震动
     */
    fun closeVibrate() {
        if (vibrator != null) {
            vibrator!!.cancel()
            vibrator = null
        }
    }

    /**
     * 播放系统默认提示音
     *
     * @return MediaPlayer对象
     * @throws Exception
     */
    fun defaultMediaPlayer(mContext: Context) {
        try {
            val notification: Uri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r: Ringtone = RingtoneManager.getRingtone(mContext, notification)
            r.play()
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
        }
    }

    /**
     * 播放系统默认来电铃声
     *
     * @return MediaPlayer对象
     * @throws Exception
     */
    fun defaultCallMediaPlayer(mContext: Context) {
        try {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            val r: Ringtone = RingtoneManager.getRingtone(mContext, notification)
            r.play()
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
        }
    }

    /**
     * 播放系统默认闹钟铃声
     *
     * @return MediaPlayer对象
     * @throws Exception
     */
    fun defaultAlarmMediaPlayer(mContext: Context) {
        try {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            val r: Ringtone = RingtoneManager.getRingtone(mContext, notification)
            r.play()
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
        }
    }
}