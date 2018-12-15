package util;

import java.util.Map;

public class Option {
    private Pagination pagination;
    private Map<String, Object> queryString;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Map<String, Object> getQueryString() {
        return queryString;
    }

    public void setQueryString(Map<String, Object> queryString) {
        this.queryString = queryString;
    }
}
