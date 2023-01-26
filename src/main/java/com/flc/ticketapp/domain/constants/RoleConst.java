package com.flc.ticketapp.domain.constants;

import com.flc.ticketapp.domain.entities.concretes.Role;
import com.flc.ticketapp.domain.helper.ReflectionHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@UtilityClass
public class RoleConst {

    private final Map<Integer, Role> CACHE;
    public final String HIERARCHY_STRING;

    static {
        CACHE = new LinkedHashMap<>();
        Map<String, Integer> orders = new LinkedHashMap<>();
        try {
            for (Field idField : Id.class.getDeclaredFields()) {
                int id = idField.getInt(null);
                String name = ReflectionHelper.getStaticFieldValueByName(idField.getName(), String.class, Name.class).orElse(null);
                CACHE.put(id, new Role(id, name));

                Integer order = ReflectionHelper.getStaticFieldValueByName(idField.getName(), Integer.class, HierarchicalOrder.class).orElse(null);
                orders.put(name, order);
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }

        StringBuilder hierarchyBuilder = new StringBuilder();
        List<Map.Entry<String, Integer>> sortedOrders = orders.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).toList();
        String separator = "\n";
        for (int i = 0; i < sortedOrders.size(); i++)
            for (int j = i + 1; j < sortedOrders.size(); j++) {
                Map.Entry<String, Integer> orderI = sortedOrders.get(i);
                Map.Entry<String, Integer> orderJ = sortedOrders.get(j);
                if (orderI.getValue() < orderJ.getValue())
                    hierarchyBuilder.append(separator).append(orderI.getKey()).append(" > ").append(orderJ.getKey());
            }
        HIERARCHY_STRING = hierarchyBuilder.length() > separator.length() ? hierarchyBuilder.substring(separator.length()) : "";

    }

    public Collection<Role> roles() {
        return CACHE.values();
    }

    public Role getInstanceById(Integer id) {
        Role cache = CACHE.get(id);
        return new Role(cache.getId(), cache.getName());
    }

    @UtilityClass
    public class Id {
        public final int USER = 1;
        public final int ADMIN = 2;
    }

    @UtilityClass
    public class Name {
        public final String USER = "ROLE_USER";
        public final String ADMIN = "ROLE_ADMIN";
    }

    @UtilityClass
    public class HierarchicalOrder {
        public final int USER = Integer.MAX_VALUE;
        public final int ADMIN = Integer.MIN_VALUE;
    }

}
