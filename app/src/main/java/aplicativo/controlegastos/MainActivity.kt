package aplicativo.controlegastos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import aplicativo.controlegastos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesHelper = PreferencesHelper(this)
        preferencesHelper.inicializarCategorias()

        binding.addExpenseButton.setOnClickListener {
            val intent = Intent(this, AddDespesaActivity::class.java)
            startActivity(intent)
        }

        binding.viewReportsButton.setOnClickListener {
            val intent = Intent(this, RelatorioActivity::class.java)
            startActivity(intent)
        }

        binding.clearDataButton.setOnClickListener {
            preferencesHelper.limparDespesas()
            Toast.makeText(this, "Dados excluídos com êxito!", Toast.LENGTH_SHORT).show()
        }
    }
}
