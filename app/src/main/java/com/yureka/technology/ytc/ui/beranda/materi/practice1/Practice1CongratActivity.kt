package com.yureka.technology.ytc.ui.beranda.materi.practice1

import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.databinding.LayoutSelamatV2Binding
import com.yureka.technology.ytc.ui.beranda.materi.MateriActivity
import com.yureka.technology.ytc.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

@AndroidEntryPoint
class Practice1CongratActivity : BaseActivity<LayoutSelamatV2Binding>(){

//    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.layout_selamat_v2

    override fun initViews() {

        binding.btnUlangi.setOnClickListener {
//            startActivity(Intent(this, SignInActivity::class.java))
            startActivity(
                intentFor<Practice1Fragment>().clearTask().newTask()
            )
        }

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(
                intentFor<MateriActivity>().clearTask().newTask()
            )
        }
    }

    override fun initObservers() {
    }

    override fun initData() = Unit

}
