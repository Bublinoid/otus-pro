package cz.bublinoid.processor;

import cz.bublinoid.annotations.MyField;
import cz.bublinoid.annotations.MyTable;
import java.lang.reflect.Field;
import java.util.StringJoiner;

public class AnnotationProcessor {

    public static String createTable(Class<?> cls) {
        if (!cls.isAnnotationPresent(MyTable.class)) {
            throw new RuntimeException("No MyTable annotation present");
        }

        MyTable table = cls.getAnnotation(MyTable.class);
        StringBuilder createStatement = new StringBuilder("CREATE TABLE " + table.name() + " (");

        Field[] fields = cls.getDeclaredFields();
        String prefix = "";
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyField.class)) {
                MyField myField = field.getAnnotation(MyField.class);
                createStatement.append(prefix);
                createStatement.append(myField.columnName() + " " + myField.type());
                prefix = ", ";
            }
        }
        createStatement.append(");");
        return createStatement.toString();
    }

    public static String dropTable(Class<?> cls) {
        MyTable table = cls.getAnnotation(MyTable.class);
        return "DROP TABLE IF EXISTS " + table.name() + ";";
    }

    public static String insertData(Class<?> cls) {
        MyTable table = cls.getAnnotation(MyTable.class);
        StringJoiner columns = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ");

        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(MyField.class)) {
                MyField myField = field.getAnnotation(MyField.class);
                columns.add(myField.columnName());
                values.add("?");
            }
        }
        return String.format("INSERT INTO %s (%s) VALUES (%s);", table.name(), columns, values);
    }

    public static String updateData(Class<?> cls) {
        MyTable table = cls.getAnnotation(MyTable.class);
        StringBuilder updateStatement = new StringBuilder("UPDATE " + table.name() + " SET ");
        String prefix = "";

        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(MyField.class)) {
                MyField myField = field.getAnnotation(MyField.class);
                updateStatement.append(prefix).append(myField.columnName() + " = ?");
                prefix = ", ";
            }
        }
        updateStatement.append(" WHERE id = ?;");
        return updateStatement.toString();
    }

    public static String selectData(Class<?> cls) {
        MyTable table = cls.getAnnotation(MyTable.class);
        return "SELECT * FROM " + table.name() + ";";
    }

    public static String deleteData(Class<?> cls) {
        MyTable table = cls.getAnnotation(MyTable.class);
        return "DELETE FROM " + table.name() + " WHERE id = ?;";
    }
}
