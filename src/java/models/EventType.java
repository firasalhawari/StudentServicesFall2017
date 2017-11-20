package models;

import java.io.Serializable;

/**
 *
 * @author Firas.Alhawari
 * 
 */
public class EventType implements Serializable{
    private int typeId;
    private String nameEn;
    private String nameAr;

    public EventType(){}
    
    public EventType(int typeId, String nameEn, String nameAr) {
        this.typeId = typeId;
        this.nameEn = nameEn;
        this.nameAr = nameAr;
    }        

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }            
}
