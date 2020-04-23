package cn.yue.base.flutter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.Platform;
import com.idlefish.flutterboost.Utils;
import com.idlefish.flutterboost.interfaces.INativeRouter;

import java.util.Map;

import cn.yue.base.middle.router.INavigation;
import cn.yue.base.middle.router.PlatformRouter;
import cn.yue.base.middle.module.IFlutterModule;
import io.flutter.embedding.android.FlutterView;

public class FlutterModuleService implements IFlutterModule {

    @Override
    public void init(Context context) {
        INativeRouter router = new INativeRouter() {
            @Override
            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {
                String  assembleUrl=Utils.assembleUrl(url,urlParams);
                Log.d("luobiao", "openContainer: " + urlParams);
                PlatformRouter.getInstance().build(url)
                        .withMap(urlParams)
                        .navigation((Activity) context, requestCode);
            }
        };

        FlutterBoost.BoostLifecycleListener boostLifecycleListener= new FlutterBoost.BoostLifecycleListener(){

            @Override
            public void beforeCreateEngine() {

            }

            @Override
            public void onEngineCreated() {

            }

            @Override
            public void onPluginsRegistered() {

            }

            @Override
            public void onEngineDestroy() {

            }

        };

        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //

        Platform platform= new FlutterBoost
                .ConfigBuilder((Application) context,router)
                .isDebug(true)
                .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(boostLifecycleListener)
                .build();
        FlutterBoost.instance().init(platform);

    }

    @Override
    public INavigation getFlutterRouter() {
        return FlutterRouter.getInstance();
    }
}