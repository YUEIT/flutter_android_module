import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost_app.dart';
import 'router/router.route.dart';
import 'router/router.route.internal.dart';


void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  Map<String, FlutterBoostRouteFactory> builders = Map();
  @override
  void initState() {
    super.initState();
    //别删！坑点
    builders['/'] = (settings, uniqueId) {
      return PageRouteBuilder<dynamic>(
          settings: settings, pageBuilder: (_, __, ___) => Container());
    };
    ARouterMap.innerRouterMap
        .forEach((String key, List<Map<String, dynamic>> page) {
      builders[key] = (settings, uniqueId) {
        return PageRouteBuilder<dynamic>(
            settings: settings,
            pageBuilder: (_, __, ___) =>
                AppRoute.getPage(key, settings.arguments, uniqueId));
      };
    });
  }

  Route<dynamic> routeFactory(RouteSettings settings, String uniqueId) {
    FlutterBoostRouteFactory func = builders[settings.name];
    if (func == null) {
      return null;
    }
    return func(settings, uniqueId);
  }

  @override
  Widget build(BuildContext context) {
    return FlutterBoostApp(routeFactory);
  }
}
