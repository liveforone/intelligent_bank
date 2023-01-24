package intelligent_bank.intelligent_bank.utility;

import intelligent_bank.intelligent_bank.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;

import java.net.URI;
import java.util.List;

public class CommonUtils {

    public static boolean isNull(Object obj) {

        //일반 객체 체크
        if(obj == null) {
            return true;
        }

        //문자열 체크
        if ((obj instanceof String) && (((String)obj).trim().length() == 0)) {
            return true;
        }

        //리스트 체크
        if (obj instanceof List) {
            return ((List<?>)obj).isEmpty();
        }

        return false;
    }

    public static ResponseEntity<String> makeResponseEntityForRedirect(
            String inputUrl,
            HttpServletRequest request
    ) {
        String url = "http://localhost:8080" + inputUrl;
        String token = JwtAuthenticationFilter.resolveToken(request);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.setLocation(URI.create(url));
        httpHeaders.setAccessControlRequestMethod(HttpMethod.GET);

        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .headers(httpHeaders)
                .build();
    }
}
