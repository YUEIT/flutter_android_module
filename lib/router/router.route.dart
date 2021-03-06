
import 'package:flutter/widgets.dart';

import 'route.dart';
import 'router_impl.dart';

@ARouteRoot()
class AppRoute {

  static Widget getPage(String urlString, Map<dynamic, dynamic> params, String uniqueId) {
    Map<String, dynamic> query = Map();
    if (params != null) {
      params.forEach((key, value) {
        query[key] = value;
      });
    }
    return _getPage(urlString, query, uniqueId);
  }

  static Widget _getPage(String urlString, Map<String, dynamic> query, String uniqueId) {
    ARouterInternalImpl internal = ARouterInternalImpl();
    ARouterResult routeResult = internal.findPage(ARouteOption(urlString, query, uniqueId), ARouteOption(urlString, query, uniqueId));
    if(routeResult.state == ARouterResultState.FOUND) {
      return routeResult.widget;
    }
    return Text('NOT FOUND');
  }
}