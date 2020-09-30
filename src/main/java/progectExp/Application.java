package progectExp;

import java.util.Date;

public class Application {
    protected double id;
    protected String data;
    protected String lvl1,lvl2,lvl3,lvl4;
    protected String type;
    protected String text;
    protected String status;
    protected String initiator;
    protected int numberObject;
    protected String address;
    protected String engineer;
    protected String contractor;
    protected String ChangeDate;
    protected String dataSLA;



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
