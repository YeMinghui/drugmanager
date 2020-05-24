package top.codermhc.drugmanager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Ye Minghui
 */
@Slf4j
@SpringBootTest
public class DruidDataSourceTest {

    @Resource
    DataSource dataSource;

    @Test
    public void testConnect() throws SQLException {
        final ResultSet describe_user = dataSource.getConnection().prepareStatement("describe user")
            .executeQuery();
        if (describe_user.next()) {
            StringBuilder stringBuilder = new StringBuilder("\n");
            final ResultSetMetaData metaData = describe_user.getMetaData();
            final int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append(metaData.getColumnLabel(i)).append("\t");
            }
            stringBuilder.append("\n");
            do {
                for (int i = 1; i <= columnCount; i++) {
                    stringBuilder.append(describe_user.getString(i)).append("\t");
                }
                stringBuilder.append("\n");
            } while (describe_user.next());
            log.info(stringBuilder.toString());
        }
    }

}
