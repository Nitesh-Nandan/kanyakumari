package org.kanyakumari.dao.db1.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DBConstants {

    @UtilityClass
    public static class MySQLConfig {

        public static final String PERSISTENCE_UNIT = "supplier-referrals-mysqlDB";

        @UtilityClass
        public static class Beans {
            public static final String CONFIG = "mysqlHikariConfig";
            public static final String ENTITY_MANAGER_FACTORY_REF = "mysqlEntityManagerFactory";
            public static final String TRANSACTION_MANAGER_REF = "mysqlTransactionManager";
            public static final String DATA_SOURCE = "mysqlDataSource";
        }
    }

    @UtilityClass
    public static class Modifier {
        public static final String SIGNUP_EVENT_WORKER = "signup_event_worker";
        public static final String DEFAULT_APP = "supplier-referrals";
    }

    @UtilityClass
    public static class Tables {
        public static final String REFERRAL_USERS = "referral_users";
    }
}
