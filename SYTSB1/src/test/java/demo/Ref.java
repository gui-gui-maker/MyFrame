package demo;

public class Ref {
	public static void main(String[] args) {
		Cls1 c1 = Factory.getInstance("demo.Cls1");
		c1.h();
		Cls2 c2 = Factory.getInstance("demo.Cls2");
		c2.h();
	}
}

class Cls1 {
	public void h() {
		System.out.println("Hi! I'm Cls1");
	}
}

class Cls2 {
	public void h() {
		System.out.println("Hi! I'm Cls2");
	}
}

class Factory {
	public static <U extends Object> U getInstance(String clsName) {
		try {
			Class<?> cls = Class.forName(clsName);
			return (U) cls.newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}