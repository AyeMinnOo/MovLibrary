package com.amo.movlibrary.ui.test

import android.os.Bundle
import com.amo.movlibrary.BR
import com.amo.movlibrary.R
import com.amo.movlibrary.databinding.ActivityTestBinding
import com.amo.movlibrary.ui.base.BaseActivity
import javax.inject.Inject

class TestActivity : BaseActivity<ActivityTestBinding, TestViewModel>(), TestNavigator {

    @Inject
    lateinit var vm: TestViewModel

    override val layoutId: Int
        get() = R.layout.activity_test

    override val viewModel: TestViewModel
        get() = vm

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.navigator = this
    }


}