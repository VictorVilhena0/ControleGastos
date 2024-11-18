package aplicativo.controlegastos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RelatorioAdapter(private val despesas: List<Despesa>) : RecyclerView.Adapter<RelatorioAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descricaoTextView: TextView = itemView.findViewById(R.id.descricaoTextView)
        val valorTextView: TextView = itemView.findViewById(R.id.valorTextView)
        val categoriaTextView: TextView = itemView.findViewById(R.id.categoriaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_relatorio, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val despesa = despesas[position]
        holder.descricaoTextView.text = despesa.descricao
        holder.valorTextView.text = "R$ ${despesa.valor}"
        holder.categoriaTextView.text = despesa.categoria
    }

    override fun getItemCount(): Int {
        return despesas.size
    }
}
