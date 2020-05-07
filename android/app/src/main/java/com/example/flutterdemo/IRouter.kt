package com.example.flutterdemo

/**
 * Description :
 * Created by yue on 2020/4/14
 */

object IRouter {

    const val FLUTTER = "flutter://"
    const val NATIVE = "native://"
    object Flutter {
        private const val FLUTTER_LIB = "lib"
        const val SECOND_PAGE = "$FLUTTER$FLUTTER_LIB/secondPage"
    }
}