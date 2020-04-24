# flutter 工程结构

    flutter sdk 1.12.17版本
    
    flutter 模式下直接编译运行
        注意，如果修改过android gradle配置项，可能会报一些奇怪的错误，这时如果在android下编译正常的话，删除掉build目录后重新编译。
            如果发现flutter代码未生效再点下运行，flutter hot Reload.
    android 模式下在已经配置好环境状态下可以将flutter_enable设置为true，插件会将外部flutter项目进行编译
        如果未配置flutter编译环境，那么需要将flutter_enable设置为false，flutter会以library的形式引入。
        前提条件是：
            1、flutter模式下编译生成flutter编译产物，在build/flutter/intermediates/flutter/debug/flutter_assets下可以看到
            2、build/app/intermediates/merged_native_libs/debug/out/lib 下的libflutter.so copy到android_build下app/libs路径下
                也可以从flutter模式下编译生成的apk解压文件中获取，如果已经存在，跳过
            3、在android_build下通过编译生成aar。（terminal中运行gradlew assembleRelease）
            4、将生成的aar，在build/app/outputs/aar中，copy到android/app/flutterlibs中

