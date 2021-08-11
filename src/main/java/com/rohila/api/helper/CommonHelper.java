package com.rohila.api.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohila.api.bean.Resource;
import com.rohila.api.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which is used to common method
 *
 * @author Tarun Rohila
 */
public class CommonHelper {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(CommonHelper.class);

    /**
     * Method to map list of object to list of resource of that object type
     *
     * @param type - type
     * @param list - list
     * @param <T>  - type
     * @return list of resource
     */
    public static <T> List<Resource<T>> mapToResourceList(
            String fieldNameForId, String type, List<T> list) {
        List<Resource<T>> resourceList = new ArrayList<>();
        list.forEach(obj -> mapToResource(resourceList, fieldNameForId, type, obj));
        return resourceList;
    }

    /**
     * Method to map list of resources of objet to list of object
     *
     * @param resourceList - resourceList
     * @param <T>          - type
     * @return list of resource
     */
    public static <T> List<T> mapResourceListToObjList(List<Resource<T>> resourceList) {
        List<T> list = new ArrayList<>();
        resourceList.forEach(obj -> mapResourceToObj(list, obj));
        return list;
    }

    /**
     * Method to map resource to object
     *
     * @param list - list
     * @param obj  - obj
     */
    private static <T> void mapResourceToObj(List<T> list, Resource<T> obj) {
        list.add(obj.getAttributes());
    }

    /**
     * Method to map resource to object
     *
     * @param obj - obj
     */
    private static <T> T mapResourceToObj(Resource<T> obj) {
        return obj.getAttributes();
    }

    /**
     * Method to map list of object to list of resource of that object type
     *
     * @param type   - type
     * @param list   - list
     * @param <T>    - type
     * @param pClass - pClass
     * @return list of resource
     */
    public static <T, S> List<Resource<S>> mapToResourceList(
            String fieldNameForId, String type, List<T> list, Class<S> pClass) {
        List<Resource<S>> resourceList = new ArrayList<>();
        list.forEach(obj -> mapToResource(resourceList, fieldNameForId, type, obj, pClass));
        return resourceList;
    }

    /**
     * Method to map object to resource
     *
     * @param resourceList   - resourceList
     * @param fieldNameForId - fieldNameForId
     * @param type           - type
     * @param obj            - object
     * @param <T>            - type
     */
    private static <T> void mapToResource(
            List<Resource<T>> resourceList, String fieldNameForId, String type, T obj) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldNameForId);
            field.setAccessible(true);
            String id = String.valueOf(field.get(obj));
            resourceList.add(new Resource<T>(id, type, obj));
        } catch (Exception e) {
            LOGGER.error("Field to map to id in resource is not present or incorrect");
        }
    }

    /**
     * Method to map object to resource
     *
     * @param resourceList   - resourceList
     * @param fieldNameForId - fieldNameForId
     * @param type           - type
     * @param obj            - object
     * @param <T>            - type
     * @param pClass         - pClass
     */
    private static <T, S> void mapToResource(
            List<Resource<S>> resourceList, String fieldNameForId, String type, T obj, Class<S> pClass) {
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        try {
            Field field = obj.getClass().getDeclaredField(fieldNameForId);
            field.setAccessible(true);
            String id = String.valueOf(field.get(obj));
            S convertedObj = mapper.convertValue(obj, pClass);
            resourceList.add(new Resource<S>(id, type, convertedObj));
        } catch (Exception e) {
            LOGGER.debug("Could not map resource data to data type = [{}]", pClass.getName(), e);
        }
    }

    public static <T, S> S mapToDomain(T obj, Class<S> pClass) {
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        S convertedObj;
        try {
            convertedObj = mapper.convertValue(obj, pClass);
        } catch (Exception e) {
            convertedObj = null;
            LOGGER.debug("Could not map resource data to data type = [{}]", pClass.getName(), e);
        }
        return convertedObj;
    }

    public static <T, S> Resource<S> mapToResource(
            String fieldNameForId, String type, T obj, Class<S> pClass) {
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        S convertedObj;
        Resource<S> resource;
        try {
            Field field = obj.getClass().getDeclaredField(fieldNameForId);
            field.setAccessible(true);
            String id = String.valueOf(field.get(obj));
            convertedObj = mapper.convertValue(obj, pClass);
            resource = new Resource<>(id, type, convertedObj);
        } catch (Exception e) {
            resource = null;
            LOGGER.debug("Could not map resource data to data type = [{}]", pClass.getName(), e);
        }
        return resource;
    }
}
