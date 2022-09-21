package test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class UserTest {

	private Long userId;
	private Integer age;
	private String email;
	
	public UserTest(Long userId, Integer age) {
		this.userId = userId;
		this.age = age;
	}
}
