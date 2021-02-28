// 리스트 3.2 JdbcTemplate을 사용해서 데이터베이스 쿼리하기

private JdbcTemplate jdbc;

@Override
public Ingredient findById(String id) {
	return jdbc.queryForObject()
		"select id, name, type from Ingredient where id = ?",
		this::mapRowToIngredient, id);
}

private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
		throws SQLException {
	return new Ingredient(
		rs.getString("id"),
		rs.getString("name"),
		Ingredient.Type.valueOf(rs.getString("type")));
}
