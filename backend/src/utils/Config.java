package utils;

//配置
public class Config {

    //主题
    public static final String THEME_PRIMARY = "mdui-theme-primary-teal";
    public static final String THEME_ACCENT = "mdui-theme-accent-pink";

    //数据库配置
    public static final String MYSQL_URL = "jdbc:mysql://45.76.79.85:3306/covid19";
    public static final String MYSQL_USER = "covid19";
    public static final String MYSQL_PASS = "Database4.0!";

    // 上传配置
    // 上传文件存储目录
    public static final String UPLOAD_DIRECTORY = "/assets/";
    //最大内存
    public static final int MEMORY_THRESHOLD = 1024 * 1024 * 32;  // 32MB
    //最大请求大小
    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 128; // 128MB
    //最大文件大小
    public static final int MAX_FILE_SIZE = 1024 * 1024 * 128;

}
