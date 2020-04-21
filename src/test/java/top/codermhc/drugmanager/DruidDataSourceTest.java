package top.codermhc.drugmanager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Ye Minghui
 * @date 2020/3/27 下午6:24
 */
@Slf4j
@SpringBootTest
public class DruidDataSourceTest {

    @Autowired
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
