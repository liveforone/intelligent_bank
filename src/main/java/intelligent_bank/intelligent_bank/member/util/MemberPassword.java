package intelligent_bank.intelligent_bank.member.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberPassword {
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static boolean isNotMatchingPassword(String inputPassword, String originalPassword) {
        return !passwordEncoder.matches(inputPassword, originalPassword);
    }
    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
