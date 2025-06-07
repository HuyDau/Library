package com.example.BE_Library.common.util;

import com.example.BE_Library.common.util.log.SimpleLogger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtils {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.getConfiguration()
                .setCollectionsMergeEnabled(false)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ModelMapperUtils() {
    }

    /**
     * Return new object of given class with attribute value copy from input object
     *
     * @param obj  input object which attributes are copied from
     * @param type type of output object
     * @return new object of given class with attribute value copy from input object
     */
    public static <T> T toObject(Object obj, Class<T> type) {
        if (obj == null) {
            return null;
        }
        T t = null;
        try {
            t = MODEL_MAPPER.map(obj, type);
        } catch (Exception ex) {
            SimpleLogger.error(ex);
        }
        return t;
    }

    /**
     * Copy attribute value from source object to destination object
     */
    public static void map(Object source, Object destination) {
        try {
            MODEL_MAPPER.map(source, destination);
        } catch (Exception ex) {
            SimpleLogger.error(ex);
        }
    }
}
