package com.wavemaker.employee.manager;
import com.wavemaker.employee.UserManager;
import com.wavemaker.employee.constants.PersistenceType;
import com.wavemaker.employee.implementation.UserDatabaseManagerImpl;
import com.wavemaker.employee.implementation.UserMemoryManagerImpl;

import java.util.*;

import static com.wavemaker.employee.constants.PersistenceType.DB;
import  static com.wavemaker.employee.constants.PersistenceType.IN_MEMORY;

public class UserInstanceFactory{
    private static Map<PersistenceType, UserManager> instanceMap = new HashMap<>();

    public static UserManager getUserManager(PersistenceType persistenceType) {
        UserManager userManager = instanceMap.get(persistenceType);
        if(userManager == null) {
            synchronized (UserInstanceFactory.class) {
                userManager = instanceMap.get(persistenceType);
                if(userManager == null) {
                    switch (persistenceType) {
                        case IN_MEMORY:
                            instanceMap.put(persistenceType, new UserMemoryManagerImpl());
                            break;
                        case DB:
                            instanceMap.put(persistenceType, new UserDatabaseManagerImpl());
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + persistenceType);
                    }
                }
            }
        }
        return userManager;
    }
}
