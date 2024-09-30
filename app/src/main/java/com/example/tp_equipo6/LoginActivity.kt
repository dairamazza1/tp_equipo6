package com.example.tp_equipo6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var etUsuario: EditText
    lateinit var etPassword: EditText
    lateinit var cbRecordarUsuario: CheckBox
    lateinit var btnRegistrarse: Button
    lateinit var btnIniciarSesion: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario),"")
        var passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario),"")

        if(usuarioGuardado!= "" && passwordGuardado!= ""){

            if(usuarioGuardado != null){

                starMainActivity(usuarioGuardado)
            }
        }

        btnRegistrarse.setOnClickListener{

            val intent= Intent(this, registro::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener{

            //Texto que ingresa el usuario.
            var usuario= etUsuario.text.toString()
            var password = etPassword.text.toString()
            //Pregunto si alguna de las dos cosas esta vacia.
            if(usuario.isEmpty() || password.isEmpty() ){

                var mensaje= "Completar Datos"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

            }
            else{
                var usuariosRegistrados: MutableList<Usuario> = ArrayList()
                var bddUsuario = AppDatabase.getDatabase(applicationContext)
                usuariosRegistrados.addAll(bddUsuario.usuarioDao().getAll())
                var uRegistrado: Boolean =false
                var i:Int =0
                while (i<usuariosRegistrados.size && uRegistrado == false){
                    if(usuario == usuariosRegistrados.get(i).userName.toString() && password == usuariosRegistrados.get(i).userPassword.toString()){
                        uRegistrado = true;
                    }
                    i++
                }

                if(uRegistrado == false){
                    Toast.makeText(this, "El usuario o contraseña ingresado no existe", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (cbRecordarUsuario.isChecked) {

                        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                        preferencias.edit().putString(resources.getString(R.string.nombre_usuario),usuario).apply()
                        preferencias.edit().putString(resources.getString(R.string.password_usuario),password).apply()

                    }

                    starMainActivity(usuario)
                }
            }
        }


    }

    private fun starMainActivity(usuario: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.nombre_usuario),usuario)
        startActivity(intent)
        finish()
    }
}