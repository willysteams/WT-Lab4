package by.bsuir.task.specification.room;

import by.bsuir.task.specification.Specification;

import java.util.Collections;
import java.util.List;

public class FindFree implements Specification {

    public FindFree() {
    }

    @Override
    public String toSql() {
        return "WHERE occupied = 'false'";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.emptyList();
    }
}
