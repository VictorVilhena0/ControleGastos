package aplicativo.controlegastos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "despesas")
data class Despesa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val valor: Double,
    val data: Date,
    val categoria: String,
    val descricao: String
)


