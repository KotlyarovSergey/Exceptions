// Задача 1: Проверка пароля (Основы языка Java, операторы, ветвления)

// Создайте класс PasswordVerifier. Этот класс должен содержать метод, который принимает строку 
// пароля и проверяет его на соответствие следующим правилам:

// Пароль должен быть не менее 8 символов.
// Пароль должен содержать хотя бы одну цифру.
// Пароль должен содержать хотя бы одну заглавную букву.
// Метод должен выбрасывать исключение, если пароль не соответствует какому-либо из этих правил.


public class HW1 {
	public static void main(String[] args) {
		String[] passwords = new String[] { "asfg", "asdfghjk", "asdfghjk9", "asdfghjK9" };
		PasswordVerifier passwordverifier = new PasswordVerifier();
		for (String password : passwords) {
			try {
				passwordverifier.check(password);
				System.out.println("password: \"" + password + "\" is OK!");
			} catch (PasswordLengthException e) {
				System.out.println("password: \"" + password + "\" is not valid! " + e.getMessage());
			} catch (PasswordDigitException e) {
				System.out.println("password: \"" + password + "\" is not valid! " + e.getMessage());
			} catch (PasswordLetterException e) {
				System.out.println("password: \"" + password + "\" is not valid! " + e.getMessage());
			}
		}
	}
}

class PasswordVerifier {
	public boolean check(String password)
			throws PasswordLengthException, PasswordDigitException, PasswordLetterException {

		return checkLength(password) &&
				checkDigit(password) &&
				checkLetter(password);

	}

	private boolean checkLength(String password) throws PasswordLengthException {
		if (password.length() < 8)
			throw new PasswordLengthException("Длинна меньше 8 символов");
		return true;
	}

	private boolean checkDigit(String password) throws PasswordDigitException {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i)))
				return true;
		}

		throw new PasswordDigitException("Не содержит цифр");
	}

	private boolean checkLetter(String password) throws PasswordLetterException {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i)))
				return true;
		}

		throw new PasswordLetterException("Нет заглавной буквы");
	}

}

class PasswordLengthException extends Exception {
	public PasswordLengthException(String message) {
		super(message);
	}
}

class PasswordDigitException extends Exception {
	public PasswordDigitException(String message) {
		super(message);
	}
}

class PasswordLetterException extends Exception {
	public PasswordLetterException(String message) {
		super(message);
	}
}