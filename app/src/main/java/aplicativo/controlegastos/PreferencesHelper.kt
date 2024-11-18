package aplicativo.controlegastos

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("despesas_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    private val gson = Gson()

    fun salvarDespesa(despesa: Despesa) {
        val despesas = listarDespesas().toMutableList()
        despesas.add(despesa)
        val json = gson.toJson(despesas)
        editor.putString("despesas", json)
        editor.apply()
    }

    fun listarDespesas(): List<Despesa> {
        val json = sharedPreferences.getString("despesas", null)
        return if (json != null) {
            val type = object : TypeToken<List<Despesa>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun salvarUsuario(usuario: Usuario) {
        val usuarios = listarUsuarios().toMutableList()
        usuarios.add(usuario)
        val json = gson.toJson(usuarios)
        editor.putString("usuarios", json)
        editor.apply()
    }

    fun listarUsuarios(): List<Usuario>{
        val json = sharedPreferences.getString("usuarios", null)
        return if (json != null) {
            val type = object : TypeToken<List<Usuario>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun salvarCategoria(categoria: Categoria) {
        val categorias = listarCategorias().toMutableList()
        categorias.add(categoria)
        val json = gson.toJson(categorias)
        editor.putString("categorias", json)
        editor.apply()
    }

    fun listarCategorias(): List<Categoria> {
        val json = sharedPreferences.getString("categorias", null)
        return if (json != null) {
            val type = object : TypeToken<List<Categoria>>(){}.type
            gson.fromJson(json, type) }
        else {
            emptyList() }
    }

    fun limparDespesas() {
        editor.remove("despesas")
        editor.apply()
    }

    fun inicializarCategorias() {
        val categoriasExistentes = listarCategorias()
        if (categoriasExistentes.isEmpty()) {
            val categoriasIniciais = listOf(
                Categoria(1, "Alimentação"),
                Categoria(2, "Transporte"),
                Categoria(3, "Lazer"),
                Categoria(4, "Saúde"),
                Categoria(5, "Educação")
            )
            categoriasIniciais.forEach {salvarCategoria(it)}
        }
    }
}