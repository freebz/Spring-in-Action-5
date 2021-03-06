// 리스트 3.5 JdbcTemplate을 사용해서 데이터베이스 쿼리하기

...
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient",
			this::mapRowToIngredient);
	}
	
	@Override
	public Ingredient findById(String id) {
		return jdbc.queryForObject(
			"select id, name, type from Ingredient where id=?",
			this::mapRowToIngredient, id);
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
	throws SQLException {
		return new Ingredient(
			rs.getString("id"),
			rs.getString("name"),
			Ingredient.Type.valueOf(rs.getString("type")));
	}
}
