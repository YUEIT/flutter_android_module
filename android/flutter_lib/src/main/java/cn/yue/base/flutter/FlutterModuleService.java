package cn.yue.base.flutter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostDelegate;

import java.util.HashMap;
import java.util.Map;

import cn.yue.base.flutter.plugin.CustomPlugin;
import cn.yue.base.flutter.plugin.LogPlugin;
import cn.yue.base.middle.router.INavigation;
import cn.yue.base.middle.router.PlatformRouter;
import cn.yue.base.middle.module.IFlutterModule;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.PluginRegistry;

public class FlutterModuleService implements IFlutterModule {

    @Override
    public void init(Context context) {
        FlutterBoost.instance().setup((Application) context, new FlutterBoostDelegate() {

            @Override
            public void pushNativeRoute(String pageName, HashMap<String, String> arguments) {
                PlatformRouter.getInstance().build(pageName)
                        .withMapString(arguments)
                        .navigation(FlutterBoost.instance().currentActivity());
            }

            @Override
            public void pushFlutterRoute(String pageName, String uniqueId, HashMap<String, String> arguments) {
                if (arguments == null) {
                    arguments = new HashMap<>();
                }
                arguments.put("uniqueId", uniqueId);
                PlatformRouter.getInstance().build(pageName)
                        .withMapString(arguments)
                        .navigation(FlutterBoost.instance().currentActivity());
            }

        },engine->{
            engine.getPlugins();
            PluginRegistry pluginRegistry =  engine.getPlugins();
            pluginRegistry.add(new CustomPlugin());
            pluginRegistry.add(new LogPlugin());
        } );
    }

    @Override
    public INavigation getFlutterRouter() {
        return FlutterRouter.getInstance();
    }
}