package com.overcom.bananaapp9.ui.view


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.core.ApiAdapter
import com.overcom.bananaapp9.core.Utils.MessageDigestUtil
import com.overcom.bananaapp9.data.network.ApiService
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication.Companion.preferences
import com.overcom.bananaapp9.databinding.ActivityMainBinding
import com.overcom.bananaapp9.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import com.overcom.bananaapp9.ui.view.modules.HomeActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.workspace.setText(preferences.getWorkspace())
        binding.email.setText(preferences.getEmail())

        initUI()
    }

    private fun initUI(){
        textChangedListener(binding.workspace)
        binding.login.setOnClickListener { accesToDetail()
            callServiceGetUsers() }
        binding.forgetpass.setOnClickListener { recoverPassword()  }
    }

    private fun accesToDetail() {
        preferences.saveWorkspace(binding.workspace.text.toString().trim())
        preferences.saveEmail(binding.email.text.toString().trim())
        preferences.saveFirstRun(true)

    }

    private fun textChangedListener(workspace: EditText){
        workspace.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!binding.workspace.text.isNullOrEmpty()){

                    Handler().postDelayed({
                        validateWorkspace()
                    }, 3000)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun validateWorkspace(){

        CoroutineScope(Dispatchers.IO).launch {

            val apiService: ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)
            val call: Response<ValidateWorkspace> = apiService
                .validateWorkspace(Workspace(binding.workspace.text.toString().trim()))

            if (call.isSuccessful && call.body()?.id == null){
                runOnUiThread{
                    binding.workspace.error = "Espacio de Trabajo no Existe"
                }
            }
        }
    }

    private fun callServiceGetUsers() {
        CoroutineScope(Dispatchers.IO).launch {

            val email = preferences.getEmail()
            val password = MessageDigestUtil.md5(binding.password.text.toString())
            val apiService: ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)
            val call: Response<UserDataItem> = apiService.listUsers(
                email, password.trim()
            )
            val users = call.body()

            if (call.isSuccessful) {

                preferences.saveToken(users?.token.toString())
                preferences.saveUserID(users?.user_id.toString())
                preferences.saveUserName(users?.user?.contact?.name.toString())
                preferences.saveUserImage(users?.user?.image_name.toString())
                preferences.saveClient(users?.third_id.toString())

                intentActivity()
            } else {

                val view = layoutInflater.inflate(R.layout.toast_error, null)
                val text: TextView = view.findViewById(R.id.toastMessage)

                runOnUiThread{
                    Toast(this@MainActivity).apply {
                        setGravity(Gravity.BOTTOM, 0, 0)
                        setView(view)
                        text.text = call.errorBody()?.string()
                    }.show()
                }
            }
        }
    }

    /*fun callServiceGetUsers() {
        repository.getUsers(binding.password)

            if (repository.call.isSuccessful) {

                preferences.saveToken(repository.users.token)
                preferences.saveUserID(repository.users.toString())
                preferences.saveClient(repository.users.third_id)

                intentActivity()
            } else {

                val view = layoutInflater.inflate(R.layout.toast_error, null)
                val text: TextView = view.findViewById(R.id.toastMessage)

                runOnUiThread{
                    Toast(this@MainActivity).apply {
                        setGravity(Gravity.BOTTOM, 0, 0)
                        setView(view)
                        text.text = repository.call.errorBody()?.string()
                    }.show()
                }
            }

    }*/

    private fun forgotPassword() {
        CoroutineScope(Dispatchers.IO).launch {

            val email = preferences.getEmail()
            val apiService: ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)
            val call: Response<ForgotPassword> = apiService.ForgetPassword(email)

            if (call.isSuccessful){

                val view = layoutInflater.inflate(R.layout.toast_ok, null)
                val text: TextView = view.findViewById(R.id.toastMessage)

                runOnUiThread {
                    Toast(this@MainActivity).apply {
                        setGravity(Gravity.BOTTOM, 0, 0)
                        setView(view)
                        text.text = "Se le ha enviado un correo con la contraseña." +
                                "No olvide Revisar la Carpeta Spam"
                        Toast.LENGTH_LONG
                    }.show()
                }
            }else{

                val view = layoutInflater.inflate(R.layout.toast_error, null)
                val text: TextView = view.findViewById(R.id.toastMessage)

                Toast(this@MainActivity).apply {
                    setGravity(Gravity.CENTER, 0, 0)
                    setView(view)
                    text.text = call.errorBody()?.string()
                }.show()
            }
        }
    }

    private fun recoverPassword() {
        val builder = AlertDialog.Builder(this, R.style.CustomDialogTheme)
        val view = layoutInflater.inflate(R.layout.recover_password, null)
        val email = view.findViewById<TextView>(R.id.emailRP)
        val workspace = view.findViewById<EditText>(R.id.workspaceRP)
        textChangedListener(workspace)

        builder.setView(view).setIcon(R.drawable.logo_banana)
            .setTitle(Html.fromHtml(
                "<font color='#000000'><b>Reestablecer Contraseña</b></font>"))
            .setMessage(R.string.Email_RP)
            .setPositiveButton(
                R.string.OK,
                DialogInterface.OnClickListener { dialog, id ->

                    preferences.saveEmail(email.text.toString())

                    forgotPassword()
                    dialog.cancel()
                })
            .setNegativeButton(
                R.string.Cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

        builder.show()
        builder.create()
    }

    private fun intentActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}