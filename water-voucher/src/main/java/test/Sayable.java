package test;

public interface Sayable {
	void say();
	default void sayMyName(String name) {
		System.out.println("sayable says: hello " + name);
	}
}