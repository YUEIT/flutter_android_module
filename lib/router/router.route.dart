
import 'package:flutter/widgets.dart';

import 'route.dart';
import 'router_impl.dart';

@ARouteRoot()
class AppRoute {
  static Widget getPage(String urlString, Map<dynamic, dynamic> params) {
    Map<String, dynamic> query = Map();
    params.forEach((key, value){
      print(key + " , " + value);
      query[key] = value;
    });
    ARouterInternalImpl internal = ARouterInternalImpl();
    ARouterResult routeResult = internal.findPage(ARouteOption(urlString, query), ARouteOption(urlString, query));
    if(routeResult.state == ARouterResultState.FOUND) {
      return routeResult.widget;
    }
    return Text('NOT FOUND');
  }
}

//class MyRouteOption {
//  String urlpattern;
//  Map<String, dynamic> query;
//  MyRouteOption(this.urlpattern, this.query );
//}