package dotstar.picksforkicks.API.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benjamin on 2016-12-28.
 */

public class Json_Data{
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}