package com.software2.software.features.show

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.software2.software.R
import com.thejuki.kformmaster.helper.form
import com.thejuki.kformmaster.helper.text
import kotlinx.android.synthetic.main.activity_show.*

/**
 * Created by chofo2003 on 28/03/18.
 */
class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        setupForm()
    }

    fun setupForm() {
        val formBuilder = form(this, show_form) {
            text {
                title = "Hola"
                value = "Probando"
            }

        }
    }
}