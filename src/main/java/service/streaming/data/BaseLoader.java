package service.streaming.data;

import com.alibaba.fastjson.JSONArray;

public abstract class BaseLoader {
    String[] headers = null;
    char delimiter = ',';
    boolean hasHeader = false;
    JSONArray array = new JSONArray();

    public BaseLoader() {
    }

    public BaseLoader(char delimiter) {
        this.delimiter = delimiter;
    }

    public abstract boolean read();

    public abstract boolean write(String filePath);

    public JSONArray getResult() {
        return array;
    }

    public String[] getHeaders() {
        return headers;
    }

    public boolean hasHeaders() {
        return hasHeader;
    }
}
