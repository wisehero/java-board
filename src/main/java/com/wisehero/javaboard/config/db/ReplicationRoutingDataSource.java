package com.wisehero.javaboard.config.db;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected @Nullable Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        String dataSourceType = isReadOnly ? "slave" : "master";
        log.info("DataSource Routing -> [{}] (ReadOnly: {})", dataSourceType, isReadOnly);
        return dataSourceType;
    }
}
