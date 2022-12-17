package by.bsuir.task.specification;

import java.util.List;

public interface Specification {
    String toSql();

    List<Object> getParameters();
}
