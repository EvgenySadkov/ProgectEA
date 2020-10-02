package progectExp;

import java.util.Date;

public class Application {
    public double id;
    public Date data;
    public String lvl1, lvl2, lvl3, lvl4;
    public String type;
    public String text;
    public String status;
    public String initiator;
    public int numberObject;
    public String address;
    public String engineer;
    public String contractor;
    public Date ChangeDate;
    public Date dataSLA;


    public Application() {
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", data=" + data +
                ", lvl1='" + lvl1 + '\'' +
                ", lvl2='" + lvl2 + '\'' +
                ", lvl3='" + lvl3 + '\'' +
                ", lvl4='" + lvl4 + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", initiator='" + initiator + '\'' +
                ", numberObject=" + numberObject +
                ", address='" + address + '\'' +
                ", engineer='" + engineer + '\'' +
                ", contractor='" + contractor + '\'' +
                ", ChangeDate=" + ChangeDate +
                ", dataSLA=" + dataSLA +
                '}';
    }
}
