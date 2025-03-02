package com.psutools.reminder.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.psutools.reminder.app.navigation.Router
import com.psutools.reminder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.detailsButton.setOnClickListener {
            val intent = router.createSampleDataDetailsIntent(this)
            startActivity(intent)
        }
        viewBinding.listButton.setOnClickListener {
            val intent = router.createSampleDataListIntent(this)
            startActivity(intent)
        }
    }
}
