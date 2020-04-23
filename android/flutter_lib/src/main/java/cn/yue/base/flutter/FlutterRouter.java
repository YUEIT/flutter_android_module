package cn.yue.base.flutter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.util.HashMap;
import java.util.Map;

import cn.yue.base.middle.router.INavigation;
import cn.yue.base.middle.router.RouterCard;

public class FlutterRouter implements INavigation {

    private static class FlutterRouterHolder {
        private static FlutterRouter instance = new FlutterRouter();
    }

    public static FlutterRouter getInstance() {
        return FlutterRouterHolder.instance;
    }

    private RouterCard routerCard;

    @Override
    public void bindRouterCard(RouterCard routerCard) {
        this.routerCard = routerCard;
    }

    @Override
    public void navigation(Context context) {
        navigation((Activity)context, null, -1);
    }

    @Override
    public void navigation(@NonNull Context context, Class toActivity) {
        navigation((Activity) context, toActivity, -1);
    }

    @Override
    public void navigation(Activity context, int requestCode) {
        navigation(context, null, requestCode);
    }

    @Override
    public void navigation(@NonNull Activity context, Class toActivity, int requestCode) {
        if (routerCard == null) {
            return;
        }
        Intent intent = BoostFlutterActivity.withNewEngine().url(routerCard.getPactUrl()).params(getParams())
                .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context);
        if (requestCode > 0) {
            context.startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    private Map getParams() {
        Map<String, Object> map = new HashMap<>();
        for (String key : routerCard.getExtras().keySet()) {
            Object object = routerCard.getExtras().get(key);
            map.put(key, object);
        }
        return map;
    }
}