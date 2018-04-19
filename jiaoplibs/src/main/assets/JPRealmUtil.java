package com.jiaop.https;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Realm数据库帮助类
 * 使用：
 * 在项目的build.gradle文件的buildscript -> dependencies下加入
 * classpath "io.realm:realm-gradle-plugin:5.0.0"
 * 在开发的Module的build.gradle文件头部加入
 * apply plugin: 'realm-android'
 * android 同级加入
 * realm {
 * syncEnabled = true
 * }
 * 其次，在Application中配置：
 * Realm.init(this);
 * 注意：不能在Application中设置key，否则会访问不到文件，因为每次都会生成一个新的key
 * //byte[] key = new byte[64];
 * //new SecureRandom().nextBytes(key);
 * RealmConfiguration config = new RealmConfiguration.Builder()
 * //文件名
 * .name("realm.realm")
 * //版本号
 * .schemaVersion(1)
 * //.encryptionKey(key)
 * //声明版本冲突时自动删除原数据库
 * .deleteRealmIfMigrationNeeded()
 * .build();
 * realm = Realm.getInstance(config);
 * 注意：声明realm静态变量，并设置get静态方法
 * 本Util不包含修改函数，可以按照示例代码实现
 */
public class JPRealmUtil {

    private static com.jiaop.https.JPRealmUtil realmUtil;
    private Realm mRealm;
    public static final int FIRST = 0;
    public static final int LAST = 1;
    public static final int ALL = 2;

    public JPRealmUtil() {
        mRealm = MyApp.getRealm();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static com.jiaop.https.JPRealmUtil getInstance() {
        if (realmUtil == null) {
            synchronized (SPUtil.class) {
                if (realmUtil == null) {
                    realmUtil = new com.jiaop.https.JPRealmUtil();
                }
            }
        }
        return realmUtil;
    }

    /**
     * 增
     *
     * @param obj
     * @param <E>
     */
    public <E extends RealmModel> void insert(final E obj) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(obj);
            }
        });
    }

    /**
     * 删
     *
     * @param results 查询得到的数据
     * @param type    JPRealmUtil.FIRST：删除表的第一条数据
     *                JPRealmUtil.LAST：删除表的最后一条数据
     *                JPRealmUtil.ALL：删除表的全部数据
     */
    public void delete(final RealmResults results, final int type) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                switch (type) {
                    case com.jiaop.https.JPRealmUtil.FIRST:
                        results.deleteFirstFromRealm();
                        break;
                    case com.jiaop.https.JPRealmUtil.LAST:
                        results.deleteLastFromRealm();
                        break;
                    case com.jiaop.https.JPRealmUtil.ALL:
                        results.deleteAllFromRealm();
                        break;
                }
            }
        });
    }

    /**
     * 查询所有
     *
     * @param cls
     * @param <E>
     * @return
     */
    public <E extends RealmModel> RealmResults<E> select(Class cls) {
        return mRealm.where(cls).findAll();
    }

    /**
     * 改（示例代码）
     */
//    public void update(){
//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                User user = mRealm.where(User.class).findFirst();
//                user.setAge(26);
//            }
//        });
//    }

}
