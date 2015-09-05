package com.brotherhood.wizards.eq.item.dto;

import com.brotherhood.wizards.enums.ItemType;

/**
 * Created by Wojciech Osak on 2015-09-05.
 */
public class ItemDTO {
    private String name;
    private String description;
    private ItemType type,type2,type3;
    private double typeValue,type2Value,type3Value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ItemType getType2() {
        return type2;
    }

    public void setType2(ItemType type2) {
        this.type2 = type2;
    }

    public ItemType getType3() {
        return type3;
    }

    public void setType3(ItemType type3) {
        this.type3 = type3;
    }

    public double getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(double typeValue) {
        this.typeValue = typeValue;
    }

    public double getType2Value() {
        return type2Value;
    }

    public void setType2Value(double type2Value) {
        this.type2Value = type2Value;
    }

    public double getType3Value() {
        return type3Value;
    }

    public void setType3Value(double type3Value) {
        this.type3Value = type3Value;
    }
}
