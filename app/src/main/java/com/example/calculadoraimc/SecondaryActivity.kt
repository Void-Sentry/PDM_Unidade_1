package com.example.calculadoraimc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calculadoraimc.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondaryBinding
    lateinit var acaoUsuario: String
    lateinit var receiver: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_secondary)

        acaoUsuario = intent.extras?.getString("acaoUsuario").toString()

        receiver = intent.extras?.getString("data").toString()

        binding.apply {
            textView4.setText(receiver)
            peso.text = acaoUsuario
        }

        binding.alterarPeso.setOnClickListener{
            var intent = Intent()
            var sender = Bundle()
            sender.putString("data", binding.textView4.text.toString())
            sender.putString("acaoUsuario", acaoUsuario)
            intent.putExtras(sender)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.button5.setOnClickListener{
            var intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }

    }
}