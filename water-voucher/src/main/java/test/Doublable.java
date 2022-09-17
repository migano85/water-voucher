package test;

public interface Doublable {

	double getResult(Double x, Double y);
	default void printMyname() {System.out.println("my name is doublable");}
}
