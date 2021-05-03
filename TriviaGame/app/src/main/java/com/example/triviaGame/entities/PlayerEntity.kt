import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia_game")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "username")
    val username: String = "",

    @ColumnInfo(name = "password")
    val password: String = "",

    @ColumnInfo(name = "health_score")
    val healthScore:Int = 0,

    @ColumnInfo(name = "financial_score")
    val financialScore:Int = 0,

    @ColumnInfo(name = "security_score")
    val securityScore:Int = 0,

    @ColumnInfo(name = "current_account")
    var currentAccount:Boolean = false
    )