// 리스트 3.6 JdbcTemplate을 사용해서 데이터베이스에 데이터 추가하기

...
@Override
public Ingredient save(Ingredient ingredient) {
	jdbc.update(
		"insert into Ingredient (id, name, type) values (?, ?, ?)",
		ingredient.getId(),
		ingredient.getName(),
		ingredient.getType().toString());
	return ingredient;
}
...
