package com.ari.crypto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ari.coins.ui.views.CoinsActivity

/**
 * @author Ari Valencia
 * @file LauncherActivity
 * @description Entry point activity for this app
 */

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        startActivity(Intent(this, CoinsActivity::class.java))
        finish()
    }
}
