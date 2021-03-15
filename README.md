# flutter 工程结构

    flutter sdk 1.12.17版本

##### flutter 模式下
    直接编译运行
    注意，如果修改过android gradle配置项，可能会报一些奇怪的错误，这时如果在android下编译正常的话，删除掉build目录后重新编译。
    如果发现flutter代码未生效再点下运行，flutter hot Reload.

##### android 模式下
    在已经配置好环境状态下可以将会使用root目录下local.properties的flutter_enable，默认true，插件会将外部flutter项目进行编译
    如果未配置flutter编译环境，那么会使用android目录下local.properties的flutter_enable，默认false，flutter会以library的形式引入。
    前提需要将编译参数提取：
        1、flutter模式下编译生成flutter编译产物，在build/flutter/intermediates/flutter/debug/flutter_assets下可以看到
        2、build/app/intermediates/merged_native_libs/debug/out/lib 下的libflutter.so copy到android_build下app/libs路径下
            也可以从flutter模式下编译生成的apk解压文件中获取，如果已经存在，跳过
        3、在android_build下通过编译生成aar。（terminal中运行gradlew buildFlutter）
        4、将生成的aar，在build/app/outputs/aar中，copy到android/app/flutter_lib的libs中

    路由生成命令
        flutter packages pub run build_runner build --delete-conflicting-outputs
    建议先执行
        flutter packages pub run build_runner clean

##### 各种适配问题
    1、build.gradle productFlavors outputFileName 都会修改apk生成路径或apk名，从而导致flutter打包时找不到对应文件而出错
        解决方式需要配置Edit Configuations；在Additional arguments:输入“--flavor=test”，Build flavor:输入“test”；其中test为配置渠道
    2、ndk 配置，不能通过abiFilters设置仅使用部分硬件架构，libflutter.so 需要全架构支持（有时配置了也能编译，那么就不需要处理了）
    3、app 路径下GeneratedPluginRegistrant 不能删除，且build.gradle中 引用的"flutter.gradle" 不能删除
        AndroidManifest中flutterEmbedding 不能删除
    4、app AndroidMainfest 必须包含启动页LAUNCHER
    5、gradle版本，不支持高版本的，具体需要对应flutter版本
    6、flutter 工程目录下编译对native修改内容有时不会编译，需要重新clean后编译或者在native下编译后再编译

##### flutter 编译控制
    目录下local.properties定义flutter.enable=true