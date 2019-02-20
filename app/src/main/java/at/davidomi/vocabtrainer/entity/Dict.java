package at.davidomi.vocabtrainer.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "dict_table")
public class Dict {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "type")
    private String mType;

    public Dict(@NonNull String type) {this.mType = type;}

    @NonNull
    public String getType() {
        return this.mType;
    }
}
