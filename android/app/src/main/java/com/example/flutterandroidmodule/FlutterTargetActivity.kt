package com.example.flutterandroidmodule

import android.os.Bundle
import cn.yue.base.common.activity.BaseActivity
import cn.yue.base.middle.router.BundleMap
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.target_layout.*
import java.lang.StringBuilder

/**
 * Description :
 * Created by yue on 2020/4/22
 */
@Route(path = "/app/flutterTarget")
class FlutterTargetActivity: BaseActivity(){

    private lateinit var query: BundleMap
    private lateinit var param1: String
    private lateinit var param2: MutableList<Int>
    override fun initBundle(bundle: Bundle) {
        super.initBundle(bundle)
        query = bundle.getSerializable("query") as BundleMap
        param1 = bundle.getString("param1")!!
        param2 = bundle.getIntegerArrayList("param2") as MutableList<Int>
    }

    override fun initView() {
        val string = StringBuilder()
        string.append(query.toString())
        string.append(param1.toString())
        string.append(param2.toString())
        target.text = string.toString()
    }

    override fun getLayoutId(): Int {
        return R.layout.target_layout
    }
}