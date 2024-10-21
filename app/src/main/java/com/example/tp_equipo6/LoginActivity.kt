package com.example.tp_equipo6

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.disklrucache.DiskLruCache.Value

class LoginActivity : AppCompatActivity() {

    lateinit var etUsuario: EditText
    lateinit var etPassword: EditText
    lateinit var cbRecordarUsuario: CheckBox
    lateinit var btnRegistrarse: Button
    lateinit var btnIniciarSesion: Button

    //notificacion
    private val CHANNEL_ID : String = "Recordar Usuario"

    fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Fragmento
        val fragmentoInfo = FragmentoInfo()
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_fragmento_info, fragmentoInfo)
            .commit()

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        val usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario),"")
        val passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario),"")

        if(usuarioGuardado!= "" && passwordGuardado!= ""){

            if(usuarioGuardado != null){

                starMainActivity(usuarioGuardado, cbRecordarUsuario.isChecked)
            }
        }

        btnRegistrarse.setOnClickListener{

            val intent= Intent(this, registro::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener{

            //Texto que ingresa el usuario.
            val usuario= etUsuario.text.toString()
            val password = etPassword.text.toString()
            //Pregunto si alguna de las dos cosas esta vacia.
            if(usuario.isEmpty() || password.isEmpty() ){

                var mensaje= "Completar Datos"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

            }
            else{
                val usuariosRegistrados: MutableList<Usuario> = ArrayList()
                val bddUsuario = AppDatabase.getDatabase(applicationContext)
                usuariosRegistrados.addAll(bddUsuario.usuarioDao().getAll())
                var uRegistrado: Boolean =false
                var i:Int =0
                while (i<usuariosRegistrados.size && uRegistrado == false){
                    if(usuario == usuariosRegistrados.get(i).userName.toString() && password == usuariosRegistrados.get(i).userPassword.toString()){
                        uRegistrado = true
                    }
                    i++
                }

                if(uRegistrado == false){
                    Toast.makeText(this, "El usuario ingresado no existe", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (cbRecordarUsuario.isChecked) {

                        val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                        preferencias.edit().putString(resources.getString(R.string.nombre_usuario),usuario).apply()
                        preferencias.edit().putString(resources.getString(R.string.password_usuario),password).apply()
                    }

                    starMainActivity(usuario, cbRecordarUsuario.isChecked)
                }
            }
        }


    }

    private fun starMainActivity(usuario: String, isUserCheck : Boolean) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.nombre_usuario),usuario)

        if(isUserCheck) {
            // Crear canal de notificaciones si es Android Oreo o superior
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                crearChannelId()  // Crear el canal de notificación
            }

            // Crear la notificación
            val notification = crearNotificacion(usuario)

            // Mostrar la notificación
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notification)


        }

        startActivity(intent)
        finish()

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun crearChannelId() {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, "Recordar Usuario", importance)
        channel.description = "Se mostrara al recordar usuario"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun crearNotificacion(usuario: String): Notification {
        val intentReminderUser = Intent(this, LoginActivity::class.java )
        intentReminderUser.putExtra("remind user", true)

        val piReminderUser = PendingIntent
            .getActivity(this, 1, intentReminderUser, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val loginAction = NotificationCompat.Action.Builder(
            R.drawable.notification_access_on, "Accede al catalogo", piReminderUser
        ).build()


        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Bienvenido/a $usuario!")
            .addAction(loginAction)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.notification_access_on)
            .setAutoCancel(true)
            .build()
    }



}