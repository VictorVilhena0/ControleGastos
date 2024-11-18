package aplicativo.controlegastos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import aplicativo.controlegastos.databinding.ActivityRelatorioBinding

class RelatorioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRelatorioBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelatorioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesHelper = PreferencesHelper(this)

        binding.generateReportButton.setOnClickListener {
            generateReport()
        }

        binding.sendEmailButton.setOnClickListener {
            enviarRelatorioPorEmail() }
    }

    private fun generateReport() {
        val despesas = preferencesHelper.listarDespesas()
        val totalAmount = despesas.sumOf { it.valor }
        binding.totalAmountTextView.text = "Total: R$ %.2f".format(totalAmount)

        val adapter = RelatorioAdapter(despesas)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun enviarRelatorioPorEmail() {
        val despesas = preferencesHelper.listarDespesas()
        val totalAmount = despesas.sumOf { it.valor }
        val relatorio = StringBuilder()
        relatorio.append("Relatório de Despesas\n\n")
        despesas.forEach { despesa -> relatorio.append("Descrição: ${despesa.descricao}\n")
            relatorio.append("Valor: R$ ${despesa.valor}\n")
            relatorio.append("Categoria: ${despesa.categoria}\n\n") }
        relatorio.append("Total: R$ %.2f".format(totalAmount))
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_SUBJECT, "Relatório de Despesas")
            putExtra(Intent.EXTRA_TEXT, relatorio.toString()) }
        startActivity(Intent.createChooser(intent, "Enviar E-mail"))
    }
}
