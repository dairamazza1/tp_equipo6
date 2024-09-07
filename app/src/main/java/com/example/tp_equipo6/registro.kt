package com.example.tp_equipo6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registro : AppCompatActivity() {

    lateinit var etName : EditText
    lateinit var etLastName : EditText
    lateinit var etBirthday : EditText
    lateinit var spGenre : Spinner
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText
    lateinit var cbPolicyPrivacy : CheckBox
    lateinit var btnregistro: Button
    lateinit var etPolicyPrivacy: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etName = findViewById(R.id.regUserName)
        etLastName = findViewById(R.id.regUserLastName)
        etEmail = findViewById(R.id.regUserEmail)
        spGenre = findViewById(R.id.regUserGenre)
        etBirthday = findViewById(R.id.regUserBirthday)
        etPassword = findViewById(R.id.regUserPassword)
        cbPolicyPrivacy = findViewById(R.id.cbPrivacyPolicy)
        etPolicyPrivacy = findViewById(R.id.tvPrivacyPolicy)
        btnregistro = findViewById(R.id.btnRegistrar)


        etPolicyPrivacy.setOnClickListener {
            val intent = Intent(this, PrivacyPolicy::class.java)
            startActivity(intent)
        }

        btnregistro.setOnClickListener {
            //validate  values
            var msgToast = ""
            if (etName.text.toString().isEmpty()) msgToast += "Completar nombre\n"
            if (etLastName.text.toString().isEmpty()) msgToast += "Completar apellido\n"
            if (etEmail.text.toString().isEmpty()) msgToast += "Completar email\n"
            if(!isValidEmail(etEmail.text.toString()) && etEmail.text.toString().isNotBlank()) msgToast += "Email inválido\n"
            if (etBirthday.text.toString().isEmpty()) msgToast += "Completar fecha de nacimiento\n"
            if(!isValidDate(etBirthday.text.toString()) && etBirthday.text.toString().isNotBlank()) msgToast += "Fecha de nacimiento inválida\n"
            if (etPassword.text.toString().isEmpty()) msgToast += "Completar contraseña\n"
            if(!isValidPassword(etPassword.text.toString()) && etPassword.text.toString().isNotBlank()) msgToast += "La contraseña debe tener al menos una mayuscula, un caracter especial y 6 o mas caracteres \n"
            if(!cbPolicyPrivacy.isChecked) msgToast += "Debe aceptar las politicas de privacidad\n"
            if (spGenre.selectedItem?.toString().isNullOrEmpty()) msgToast+= "Seleccione un genero válido\n"


            //show message
            if(msgToast.isNotBlank()) Toast.makeText(this,msgToast, Toast.LENGTH_SHORT).show()


            if(msgToast.isBlank() && cbPolicyPrivacy.isChecked){
                Log.i("TODO", "Agregar funcionalidad de recordar usuario y contraseña y agregar en bd")

                //intent to login  -- CAMBIAR A LOGIN/MAIN CUANDO ESTE LISTO (!)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //finish()
            }
        }

    }
    // Regex functions that validate expected string format
    private fun isValidEmail(email: String): Boolean {
        val regex = Regex("^[\\w._%+-]+@[\\w.-]+\\.com$")
        return regex.matches(email)
    }
    private fun isValidDate(date: String): Boolean {
        val regex = Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")
        return regex.matches(date)
    }
    fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Z])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/\\?]).{6,}$")
        return regex.matches(password)
    }
}