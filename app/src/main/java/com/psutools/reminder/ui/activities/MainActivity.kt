package com.psutools.reminder.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.psutools.reminder.R
import com.psutools.reminder.app.navigation.Router
import com.psutools.reminder.databinding.ActivityMainBinding
import com.psutools.reminder.ui.sample.details.DetailsActivity
import com.psutools.reminder.utils.ui.isDarkMode
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

        window?.statusBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            if (!isDarkMode(this)) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decorView.setSystemUiVisibility(0);
            }
        }



        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        val bottomNavView = viewBinding.bottomNav
        bottomNavView.setupWithNavController(navController)
    }
    //работа бездушной машины
    // Константа для идентификации запроса
    private companion object {
        const val DELETE_TRIP_REQUEST_CODE = 1001
    }

    // Запуск DetailsActivity с ожиданием результата
    private fun openDetailsActivity(tripId: String) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("TRIP_ID", tripId)
        }
        startActivityForResult(intent, DELETE_TRIP_REQUEST_CODE)
    }

    // Обработка результата
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            DELETE_TRIP_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    // Поездка была удалена - обновляем список
                    refreshTripList()

                    // Можно прочитать дополнительные данные, если нужно
                    val tripDeletedId = data?.getStringExtra("DELETED_TRIP_ID")
                    tripDeletedId?.let { showDeleteSuccessMessage(it) }
                }
            }
        }
    }

    private fun refreshTripList() {
        // Ваша логика обновления списка поездок
    }

    private fun showDeleteSuccessMessage(tripId: String) {
        Toast.makeText(this, "Поездка $tripId удалена", Toast.LENGTH_SHORT).show()
    }
}