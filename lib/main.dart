import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
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
  @override
  void initState() {
    super.initState();
    Map<String, PageBuilder> builders = Map();
    ARouterMap.innerRouterMap.forEach((String key, List<Map<String, dynamic>> page) {
      builders[key] = (pageName, params, _)=> AppRoute.getPage(pageName, params);
    });
    FlutterBoost.singleton.registerPageBuilders(builders);

//    FlutterBoost.singleton.addBoostNavigatorObserver(TestBoostNavigatorObserver());
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Boost example',
        builder: FlutterBoost.init(postPush: _onRoutePushed),
        home: Container(
            color:Colors.white
        ));
  }

  void _onRoutePushed(
      String pageName, String uniqueId, Map params, Route route, Future _) {
  }
}
class TestBoostNavigatorObserver extends NavigatorObserver{
  void didPush(Route<dynamic> route, Route<dynamic> previousRoute) {

    print("flutterboost#didPush");
  }

  void didPop(Route<dynamic> route, Route<dynamic> previousRoute) {
    print("flutterboost#didPop");
  }

  void didRemove(Route<dynamic> route, Route<dynamic> previousRoute) {
    print("flutterboost#didRemove");
  }

  void didReplace({Route<dynamic> newRoute, Route<dynamic> oldRoute}) {
    print("flutterboost#didReplace");
  }
}
