package by.bsuir.task.repository.helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryHelper {

    private static final String INSERT_QUERY = "INSERT INTO ";
    private static final String UPDATE_QUERY = "UPDATE ";
    private static final String SET = " SET ";
    private static final String VALUES = " VALUES";

    public static String makeInsertQuery(Map<String, Object> fields, String table) {
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String column = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                columns.append(" `").append(column).append("`,");
                values.append(" ?,");
            }
        }

        values.deleteCharAt(values.lastIndexOf(","));
        columns.deleteCharAt(columns.lastIndexOf(","));
        values.append(")");
        columns.append(")");

        return INSERT_QUERY + table + columns + VALUES + values;
    }

    public static String makeUpdateQuery(Map<String, Object> fields, String table) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String column = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                if (!column.equals("id")) {
                    query.append(" `").append(column).append("`=? ,");
                } else {
                    query.deleteCharAt(query.lastIndexOf(","));
                    query.append(" WHERE `id` =?");
                }
            }
        }
        return UPDATE_QUERY + table + SET + query;
    }

    public static void prepare(PreparedStatement preparedStatement, Map<String, Object> fields) throws SQLException {
        int i = 1;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                preparedStatement.setString(i++, String.valueOf(value));
            }
        }
    }

    public static void prepare(PreparedStatement preparedStatement, List<Object> params) throws SQLException {
        int length = params.size();
        for (int i = 0; i < length; i++) {
            Object param = params.get(i);
            if (!(param instanceof Integer)) {
                preparedStatement.setString(i + 1, String.valueOf(param));
            } else {
                preparedStatement.setInt(i + 1, (Integer) param);
            }
        }
    }
}
