package intelligent_bank.intelligent_bank.member.validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberPasswordValidator {
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static boolean isNotMatchingPassword(String inputPassword, String originalPassword) {
        return !passwordEncoder.matches(inputPassword, originalPassword);
    }

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
