package aplicativo.controlegastos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import aplicativo.controlegastos.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesHelper = PreferencesHelper(this)

        binding.registerButton.setOnClickListener {
            val nome = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val senha = binding.passwordEditText.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()) {
                val usuario = Usuario(0, nome, email, senha)
                preferencesHelper.salvarUsuario(usuario)
                Toast.makeText(this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
