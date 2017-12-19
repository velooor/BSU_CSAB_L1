package by.training.zakharchenya.courseproject.entity;

public class Fieeld {
    private int fieldId;
    private int tableId;
    private String fieldName;
    private String fieldRealName;
    private String fieldType;

    public Fieeld(int fieldId, int tableId, String fieldName, String fieldRealName, String fieldType) {
        this.fieldId = fieldId;
        this.tableId = tableId;
        this.fieldName = fieldName;
        this.fieldRealName = fieldRealName;
        this.fieldType = fieldType;
    }

    public Fieeld(int fieldId, String fieldName, String fieldRealName) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldRealName = fieldRealName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldRealName() {
        return fieldRealName;
    }

    public void setFieldRealName(String fieldRealName) {
        this.fieldRealName = fieldRealName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
