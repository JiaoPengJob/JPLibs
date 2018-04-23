package com.jiaop.libs.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * <pre>
 *     author : jiaop
 *     time   : 2018/04/23
 *     desc   : SD卡帮助类
 *     version: 1.0
 * </pre>
 */
public class JPSDUtil {

    /**
     * 得到SD卡根目录
     */
    public static File getRootPath() {
        File path = null;
        if (sdCardIsAvailable()) {
            path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
        } else {
            path = Environment.getDataDirectory();
        }
        return path;
    }

    /**
     * 获取的目录默认没有最后的”/”,需要自己加上
     * 获取本应用图片缓存目录
     *
     * @return
     */
    public static File getCecheFolder(Context context) {
        File folder = new File(context.getCacheDir(), "IMAGECACHE");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡路径
     * <p>一般是/storage/emulated/0/</p>
     *
     * @return SD卡路径
     */
    public static String getSDCardPath() {
        if (!isSDCardEnable()) return "sdcard unable!";
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }

    /**
     * 获取SD卡Data路径
     *
     * @return SD卡Data路径
     */
    public static String getDataPath() {
        if (!isSDCardEnable()) return "sdcard unable!";
        return Environment.getDataDirectory().getPath();
    }

    /**
     * 获取SD卡剩余空间
     *
     * @return SD卡剩余空间
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getFreeSpace() {
        if (!isSDCardEnable()) return "sdcard unable!";
        StatFs stat = new StatFs(getSDCardPath());
        long blockSize, availableBlocks;
        availableBlocks = stat.getAvailableBlocksLong();
        blockSize = stat.getBlockSizeLong();
        return JPStringUtil.byte2FitSize(availableBlocks * blockSize);
    }

    /**
     * SD卡是否可用
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else
            return false;
    }

}
