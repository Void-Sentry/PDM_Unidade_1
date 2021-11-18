package com.example.calculadoraimc

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.hardware.biometrics.BiometricManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var alturaPessoa : Float = 180F
    var pesoPessoa : Float = 105F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val ResultadoRequisicao = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){

                RESULT_OK->{

                    val param = it.data?.extras
                    when(it.data?.extras?.getString("acaoUsuario")){
                        "peso" -> { binding.textView8.text = param?.getString("data").toString() }
                        "altura" -> { binding.textView9.text = param?.getString("data").toString() }
                    }
                    //binding.textView9.text = param?.getString("data1").toString()
                    //binding.textView8.text = param?.getString("data").toString()

                    //val texto = param?.getString("Dados")
                    //Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                }

                RESULT_CANCELED -> {
                    Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.apply {
            textView8.text = pesoPessoa.toString()
            textView9.text = alturaPessoa.toString()


        }

        binding.button.setOnClickListener{
            var Heigth = binding.textView9.text.toString().toFloat()
            var weigth = binding.textView8.text.toString().toFloat()
            var result = (weigth) / (Heigth * Heigth) * 10000
            binding.textView.text = result.toInt().toString()

            if(result < 16F){
                binding.textView10?.text = "Magreza grave"
            }
            if(result > 16F && result < 17F){
                binding.textView10?.text = "Magreza moderada"
            }
            if(result > 17F && result < 18.5F){
                binding.textView10?.text = "Magreza leve"
            }
            if(result > 18.5F && result < 25F){
                binding.textView10?.text = "Saudável"
            }
            if(result > 25F && result < 30F){
                binding.textView10?.text = "Sobrepeso"
            }
            if(result > 30F && result < 35F){
                binding.textView10?.text = "Obesidade Grau I"
            }
            if(result > 35F && result < 40F){
                binding.textView10?.text = "Obesidade Grau II (severa)"
            }
            if(result > 40F){
                binding.textView10?.text = "Obesidade Grau III (mórbida)"
            }

        }

        binding.button2.setOnClickListener{
            val intent = Intent(this, SecondaryActivity::class.java)
            var sender = Bundle()
            sender.putString("data", binding.textView8.text.toString())
            sender.putString("acaoUsuario", "peso")
            intent.putExtras(sender)
            ResultadoRequisicao.launch(intent)
        }

        binding.button3.setOnClickListener{
            val intent = Intent(this, SecondaryActivity::class.java)
            var sender = Bundle()
            sender.putString("data", binding.textView9.text.toString())
            sender.putString("acaoUsuario", "altura")
            intent.putExtras(sender)
            ResultadoRequisicao.launch(intent)
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.textView.text = savedInstanceState.getString("valor_text")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("valor_textview", binding.textView.text.toString())
    }

}