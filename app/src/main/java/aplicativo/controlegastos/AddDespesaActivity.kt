package aplicativo.controlegastos

//noinspection SuspiciousImport
import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import aplicativo.controlegastos.databinding.ActivityAdicionarDespesaBinding
import java.util.Date

class AddDespesaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarDespesaBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarDespesaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesHelper = PreferencesHelper(this)

        val categorias = preferencesHelper.listarCategorias()
        val categoriaNomes = categorias.map { it.nome }
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, categoriaNomes)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.categoriaSpinner.adapter = adapter
        binding.saveExpenseButton.setOnClickListener {
            val amount = binding.expenseAmountEditText.text.toString().toDoubleOrNull()
            val description = binding.expenseDescriptionEditText.text.toString()
            val categoriaNome = binding.categoriaSpinner.selectedItem.toString()
            val categoria = categorias.find { it.nome == categoriaNome }

            if (amount != null && description.isNotEmpty() && categoria != null) {
                val despesa = Despesa(0, amount, Date(), categoria.nome, description)
                preferencesHelper.salvarDespesa(despesa)
                Toast.makeText(this, "A sua despesa foi salva!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
