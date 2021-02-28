// 리스트 3.1 JdbcTemplate을 사용하지 않고 데이터베이스 쿼리하기

@Override
public Ingredient findById(String id) {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
		connection = dataSource.getConnection();
		statement = connection.prepareStatement(
			"select id, name, type from Ingredient where id = ?");
		statement.setString(1, id);
		resultSet = statement.executeQuery();
		Ingredient ingredient = null;
		if (resultSet.next()) {
			ingredient = new Ingredient(
				resultSet.getString("id"),
				resultSet.getString("name"),
				Ingredient.Type.valueOf(resultSet.getString("type")));
		}
		return ingredient;
	} catch (SQLException e) {
		// 여기서는 무엇을 해야 할까?
	} finally {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {}
		}
	}
	return null;
}
