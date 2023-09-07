package mk.ukim.finki.usersmanagement.security;

public class SecurityConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SECRET = "s3cr3tt0k3n";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT = "/logout";
    public static final String REGISTER = "/register";
    public static final String PUBLIC_URLS="/public/**";
    public static final String SWAGGER_UI ="/swagger-ui/**";
    public final static String CLAIM_AUTHORITY = "authority";
    public static final String CLAIM_USER_ID = "userId";
    public final static String CLAIM_PRIVILEGES = "privileges";
    public final static String PRODUCTS = "products";

}
