package com.moviex.persistence.config;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;


public final class PostgresSpecificUpperCamelCaseNamingStrategy extends SpringNamingStrategy {

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        return convertToUpperCamelCase(super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName));
    }

    @Override
    public String classToTableName(String className) {
        return convertToUpperCamelCase(super.classToTableName(className));
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return convertToUpperCamelCase(super.propertyToColumnName(propertyName));
    }

    @Override
    public String tableName(String tableName) {
        return convertToUpperCamelCase(super.tableName(tableName));
    }

    @Override
    public String columnName(String columnName) {
        return convertToUpperCamelCase(super.columnName(columnName));
    }

    @Override
    public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable, String propertyName) {
        return convertToUpperCamelCase(super.collectionTableName(ownerEntity, ownerEntityTable, associatedEntity, associatedEntityTable, propertyName));
    }

    @Override
    public String joinKeyColumnName(String joinedColumn, String joinedTable) {
        return convertToUpperCamelCase(super.joinKeyColumnName(joinedColumn, joinedTable));
    }

    @Override
    public String logicalColumnName(String columnName, String propertyName) {
        return convertToUpperCamelCase(super.logicalColumnName(columnName, propertyName));
    }

    @Override
    public String logicalCollectionTableName(String tableName, String ownerEntityTable, String associatedEntityTable, String propertyName) {
        return convertToUpperCamelCase(super.logicalCollectionTableName(tableName, ownerEntityTable, associatedEntityTable, propertyName));
    }

    @Override
    public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
        return convertToUpperCamelCase(super.logicalCollectionColumnName(columnName, propertyName, referencedColumn));
    }

    private static String convertToUpperCamelCase(String value) {
        return StringUtils.wrap(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value), '"');
    }
}
